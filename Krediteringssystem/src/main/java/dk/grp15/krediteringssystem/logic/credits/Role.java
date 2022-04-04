package dk.grp15.krediteringssystem.logic.credits;

public enum Role {
    DEFAULT_ROLE(""),
    PRODUCER("Producent"),
    ACTOR("Skuespiller"),
    CAMERAMAN("Kameramand"),
    SOUNDTECHNICIAN("Lydtekniker");

    private String label;

    Role(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
