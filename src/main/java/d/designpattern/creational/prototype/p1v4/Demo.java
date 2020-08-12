package d.designpattern.creational.prototype.p1v4;


import d.designpattern.creational.prototype.SearchWord;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class Demo {
    private HashMap<String, SearchWord> currentWords    = new HashMap<>();
    private long                        lastUpdatedTime = -1;

    public void refresh() throws IOException, ClassNotFoundException {
        List<SearchWord> needToBeUpdatedWords = getWordNewToTime(lastUpdatedTime);

        HashMap<String, SearchWord> copyOfCurrentWords = (HashMap<String, SearchWord>) deepCopy(currentWords);

        for (SearchWord word : needToBeUpdatedWords) {
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

    private Object deepCopy(Object object) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        return objectInputStream.readObject();
    }

    private List<SearchWord> getWordNewToTime(long time) {
        return null;
    }


}
