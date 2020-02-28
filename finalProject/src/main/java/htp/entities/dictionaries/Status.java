package htp.entities.dictionaries;

public enum Status {
    ACCEPT("Accept"),
    ISSUED("Issued"),
    FAILURE("Failure"),
    CUSTOMER_FAILURE("Customer_failure"),
    PROCEED("Proceed");
    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
