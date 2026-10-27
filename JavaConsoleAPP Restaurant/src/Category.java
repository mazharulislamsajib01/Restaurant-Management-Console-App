public enum Category {
    STARTER(1, "Starter"),
    MAIN_COURSE(2, "Main Course"),
    DRINKS(3, "Drinks");

    private final int code;
    private final String displayName;

    Category(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    // code (1,2,3) diye Category khuje ber kore, na pele null dey
    public static Category fromCode(int code) {
        for (Category c : values()) {
            if (c.code == code) {
                return c;
            }
        }
        return null;
    }
}