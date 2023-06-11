package ua.lviv.iot.model.user;

public enum Sex {
    MALE,
    FEMALE;

    public static Sex valueOf(Integer value) {
        if (value > values().length) {
            return null;
        } else {
            return values()[value];
        }
    }
}