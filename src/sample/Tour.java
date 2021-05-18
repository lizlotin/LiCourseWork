package sample;

public class Tour {
    private String name;
    private String fromDate;
    private String toDate;
    private int price;
    private int id;
    private String url;

    public Tour(String name, String fromDate, String toDate, int price, int id, String url) {
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.price = price;
        this.id = id;
        this.url = url;
    }

    public Tour(String name, String fromDate, String toDate, int price, String url) {
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.price = price;
        this.url = url;

    }

    public Tour() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "name='" + name + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
