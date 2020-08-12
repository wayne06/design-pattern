package d.designpattern.creational.prototype.p1v2;


import d.designpattern.creational.prototype.SearchWord;

import java.util.HashMap;
import java.util.List;

public class Demo {

    private HashMap<String, SearchWord> currentWordsMap = new HashMap<>();

    private long lastUpdatedTime = -1;

    public void refresh() {
        HashMap<String, SearchWord> copyOfCurrentWordsMap = (HashMap<String, SearchWord>) currentWordsMap.clone();

        List<SearchWord> latestSearchWords = getSeachWordsNewToTime(lastUpdatedTime);
        for (SearchWord latestSearchWord : latestSearchWords) {
            if (latestSearchWord.getLastUpdatedTime() > lastUpdatedTime) {
                lastUpdatedTime = latestSearchWord.getLastUpdatedTime();
            }
            if (copyOfCurrentWordsMap.containsKey(latestSearchWord.getKeyword())) {
                copyOfCurrentWordsMap.replace(latestSearchWord.getKeyword(), latestSearchWord);
            } else {
                copyOfCurrentWordsMap.put(latestSearchWord.getKeyword(), latestSearchWord);
            }
        }
        currentWordsMap = copyOfCurrentWordsMap;
    }

    private List<SearchWord> getSeachWordsNewToTime(long time) {
        // TODO: 查询更新时间 > time 的所有数据
        return null;
    }

}
