package htp.entities.dictionaries;

public enum Decision {
    ACCEPT("Accept"),
    DECLINE("Decline"),
    PROCEED("Proceed");
    private String decision;

    Decision(String decision) {
        this.decision = decision;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

}
