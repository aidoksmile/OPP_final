package views;

public enum Commands {
    NONE,
    READ,
    CREATE,
    UPDATE,
    LIST,
    DELETE,
    EXIT;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}