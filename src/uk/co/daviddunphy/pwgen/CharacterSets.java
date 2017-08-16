package uk.co.daviddunphy.pwgen;

public class CharacterSets {

    private CharacterSets() {
        throw new IllegalAccessError("Utility class");
    }

    public static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMERIC = "0123456789";
    public static final String BASIC_SPECIAL = "!$&*()-=+;:@#?<>";

}
