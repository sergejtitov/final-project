package htp.entities.dictionaries;

public enum MyCurrency {
    USD("USD"),
    EUR("EUR"),
    BYN("BYN");
    private String myCurrency;

    MyCurrency(String myCurrency) {
        this.myCurrency = myCurrency;
    }

    public void setMyCurrency(String myCurrency) {
        this.myCurrency = myCurrency;
    }

    public String getMyCurrency() {
        return myCurrency;
    }

    @Override
    public String toString() {
        return myCurrency;
    }
}
