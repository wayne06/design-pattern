package d.designpattern.creational.prototype.p1v3;


import d.designpattern.creational.prototype.SearchWord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {

    private HashMap<String, SearchWord> currentWords = new HashMap<>();

    private long lastUpdatedTime = -1;

    public void refresh() {
        HashMap<String, SearchWord> copyOfCurrentWords = new HashMap<>();
        for (Map.Entry<String, SearchWord> entry : currentWords.entrySet()) {
            SearchWord entryValue = entry.getValue();
            SearchWord searchWord = new SearchWord(entryValue.getKeyword(), entryValue.getCount(), entryValue.getLastUpdatedTime());
            copyOfCurrentWords.put(entry.getKey(), searchWord);
        }

        List<SearchWord> toBeUpdateWords = getSearchWordsNewToTime(lastUpdatedTime);
        for (SearchWord word : toBeUpdateWords) {
            if (word.getLastUpdatedTime() > lastUpdatedTime) {
                lastUpdatedTime = word.getLastUpdatedTime();
            }
            if (copyOfCurrentWords.containsKey(word.getKeyword())) {
                copyOfCurrentWords.replace(word.getKeyword(), word);
            } else {
                copyOfCurrentWords.put(word.getKeyword(), word);
            }
        }

        currentWords = copyOfCurrentWords;

    }

    private List<SearchWord> getSearchWordsNewToTime(long time) {
        // TODO: 从数据库获取更新时间 > time 的所有数据
        return null;
    }

}
