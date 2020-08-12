package f.inaction.darklaunch.v1;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import org.apache.commons.lang3.StringUtils;

/**
 * DarkFeature 类表示每个要灰度的业务功能的灰度规则
 *
 * DarkFeature 将配置文件中灰度规则，解析成一定的结构（比如 RangeSet），方便快速判定某个灰度对象是否落在灰度规则范围内
 */
public class DarkFeature {

    private String key;
    private boolean enabled;
    private int percentage;
    private RangeSet<Long> rangeSet = TreeRangeSet.create();

    public DarkFeature(DarkRuleConfig.DarkFeatureConfig darkFeatureConfig) {
        this.key = darkFeatureConfig.getKey();
        this.enabled = darkFeatureConfig.getEnabled();
        String darkRule = darkFeatureConfig.getRule().trim();
        parseDarkRule(darkRule);
    }

    private void parseDarkRule(String darkRule) {
        if (!darkRule.startsWith("{") || !darkRule.endsWith("}")) {
            throw new RuntimeException("Failed to parse dark rule: " + darkRule);
        }
        String[] rules = darkRule.substring(1, darkRule.length() - 1).split(",");
        this.rangeSet.clear();
        this.percentage = 0;
        for (String rule : rules) {
            rule = rule.trim();
            if (StringUtils.isEmpty(rule)) {
                continue;
            }
            if (rule.startsWith("%")) {
                int newPercentage = Integer.parseInt(rule.substring(1));
                if (newPercentage > this.percentage) {
                    this.percentage = newPercentage;
                }
            } else if (rule.contains("-")) {
                String[] parts = rule.split("-");
                if (parts.length != 2) {
                    throw new RuntimeException("Failed to parse dark rule: " + darkRule);
                }
                long start = Long.parseLong(parts[0]);
                Long end = Long.parseLong(parts[1]);
                if (start > end) {
                    throw new RuntimeException("Failed to parse dark rule: " + darkRule);
                }
                this.rangeSet.add(Range.closed(start, end));
            } else {
                long val = Long.parseLong(rule);
                this.rangeSet.add(Range.closed(val, val));
            }
        }
    }

    public boolean enabled() {
        return this.enabled;
    }

    public boolean dark(long darkTarget) {
        boolean selected = this.rangeSet.contains(darkTarget);
        if (selected) {
            return true;
        }
        long reminder = darkTarget % 100;
        if (reminder >= 0 && reminder < this.percentage) {
            return true;
        }
        return false;
    }

    public boolean dark(String darkTarget) {
        long target = Long.parseLong(darkTarget);
        return dark(target);
    }

}
