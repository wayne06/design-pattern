package designpattern.creational.prototype;

public class SearchWord {

    private String keyword;
    private Integer count;
    private Long lastUpdatedTime;

    public SearchWord(String keyword, Integer count, Long lastUpdatedTime) {
        this.keyword = keyword;
        this.count = count;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
