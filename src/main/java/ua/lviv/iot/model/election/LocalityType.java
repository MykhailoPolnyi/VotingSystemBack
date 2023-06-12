package ua.lviv.iot.model.election;

public enum LocalityType {
    DISTRICT,
    CITY,
    STATE,
    NATIONAL;

    public static LocalityType valueOf(Integer value) {
        if (value > values().length) {
            return null;
        } else {
            return values()[value];
        }
    }
}
