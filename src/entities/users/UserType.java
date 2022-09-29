package entities.users;

public enum UserType{
    ADMIN,
    BUYER,
    SELLER;

    /**
     * Checks whether input text is an existing UserType Enum class
     *
     * @param text to be checked
     * @return whether (case-insensitive) text is a valid Enum class
     */
    public static UserType fromString(String text) {
        for (UserType u : UserType.values()) {
            if (u.toString().equals(text.toUpperCase())) {
                return u;
            }
        }
        throw new IllegalArgumentException();
    }
}
