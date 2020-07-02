package ubb.exam.model;

public class WebsiteKeywords {
    private String url;
    private Integer noKeyWords;

    public WebsiteKeywords(String url, Integer noKeyWords) {
        this.url = url;
        this.noKeyWords = noKeyWords;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getNoKeyWords() {
        return noKeyWords;
    }

    public void setNoKeyWords(Integer noKeyWords) {
        this.noKeyWords = noKeyWords;
    }
}
