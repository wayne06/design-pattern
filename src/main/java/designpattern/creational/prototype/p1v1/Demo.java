package designpattern.creational.prototype.p1v1;


import designpattern.creational.prototype.SearchWord;

import java.util.HashMap;
import java.util.List;

public class Demo {

    private HashMap<String, SearchWord> currentWordsMap = new HashMap<>();

    private long lastUpdatedTime = -1;

    public void refresh() {
        List<SearchWord> latestSearchWords = getSeachWordsNewToTime(lastUpdatedTime);

        for (SearchWord latestSearchWord : latestSearchWords) {
            if (latestSearchWord.getLastUpdatedTime() > lastUpdatedTime) {
                lastUpdatedTime = latestSearchWord.getLastUpdatedTime();
            }
            if (!currentWordsMap.containsKey(latestSearchWord.getKeyword())) {
                currentWordsMap.put(latestSearchWord.getKeyword(), latestSearchWord);
            } else {
                currentWordsMap.replace(latestSearchWord.getKeyword(), latestSearchWord);
            }
        }
    }

    private List<SearchWord> getSeachWordsNewToTime(long time) {
        // TODO: 从数据库中取出更新时间 > time的所有数据
        return null;
    }

}
