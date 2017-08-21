package uk.co.daviddunphy.pwgen.model;

public class CharacterSets {

    private CharacterSets() {
        throw new IllegalAccessError("Utility class");
    }

    public static final CharacterSet UPPER_CASE = new CharacterSetImpl("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    public static final CharacterSet LOWER_CASE = new CharacterSetImpl("abcdefghijklmnopqrstuvwxyz");
    public static final CharacterSet NUMERIC = new CharacterSetImpl("0123456789");
    public static final CharacterSet BASIC_SPECIAL = new CharacterSetImpl("!$&*()-=+;:@#?<>");
    public static final CharacterSet ALPHABETIC = new CharacterSetImpl(UPPER_CASE, LOWER_CASE);
    public static final CharacterSet ALPHANUMERIC = new CharacterSetImpl(ALPHABETIC, NUMERIC);

}
