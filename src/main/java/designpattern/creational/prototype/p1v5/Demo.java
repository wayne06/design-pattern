package designpattern.creational.prototype.p1v5;


import designpattern.creational.prototype.SearchWord;

import java.util.HashMap;
import java.util.List;

public class Demo {

    private long                        lastUpdatedTime = -1;
    private HashMap<String, SearchWord> currentWords    = new HashMap<>();

    public void refresh() {
        HashMap<String, SearchWord> copyOfCurrentWords = (HashMap<String, SearchWord>) currentWords.clone();

        List<SearchWord> needToBeUpdatedWords = getWordsNewToTime(lastUpdatedTime);

        for (SearchWord word : needToBeUpdatedWords) {
            if (word.getLastUpdatedTime() > lastUpdatedTime) {
                lastUpdatedTime = word.getLastUpdatedTime();
            }
            if (copyOfCurrentWords.containsKey(word.getKeyword())) {
                copyOfCurrentWords.remove(word.getKeyword());
            }
            copyOfCurrentWords.put(word.getKeyword(), word);
        }
        currentWords = copyOfCurrentWords;
    }

    private List<SearchWord> getWordsNewToTime(long time) {
        return null;
    }
}
