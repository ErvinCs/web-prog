package ubb.exam.model;

public class Website extends BaseEntity {
    private String url;

    public Website(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
