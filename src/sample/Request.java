package sample;

public class Request {
    private String nameTour;
    private int price;
    private int numOfAdult;
    private int numOfChildren;
    private String clientName;
    private int phoneNumber;
    private int requestId;
    private int tourId;
    private String status;

    public Request(String nameTour, int price, int numOfAdult, int numOfChildren,  String clientName, int phoneNumber, int tourId, String status) {
        this.nameTour = nameTour;
        this.price = price;
        this.numOfAdult = numOfAdult;
        this.numOfChildren = numOfChildren;
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.tourId = tourId;
        this.status = status;
    }

    public Request(String nameTour, int price, int numOfAdult, int numOfChildren, String clientName, int phoneNumber, int requestId, int tourId, String status) {
        this.nameTour = nameTour;
        this.price = price;
        this.numOfAdult = numOfAdult;
        this.numOfChildren = numOfChildren;
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.requestId = requestId;
        this.tourId = tourId;
        this.status = status;
    }

    public Request(){}
    public String getNameTour() {
        return nameTour;
    }

    public void setNameTour(String nameTour) {
        this.nameTour = nameTour;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumOfAdult() {
        return numOfAdult;
    }

    public void setNumOfAdult(int numOfAdult) {
        this.numOfAdult = numOfAdult;
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request{" +
                "nameTour='" + nameTour + '\'' +
                ", price=" + price +
                ", numOfAdult=" + numOfAdult +
                ", numOfChildren=" + numOfChildren +
                ", clientName='" + clientName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", requestId=" + requestId +
                ", tourId=" + tourId +
                ", status='" + status + '\'' +
                '}';
    }
}
