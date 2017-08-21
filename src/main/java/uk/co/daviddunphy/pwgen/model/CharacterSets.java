package uk.co.daviddunphy.pwgen.model;

import java.util.Collection;

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

    public static boolean containsAlphabeticCharacter(Collection<Character> characters) {
        for (char ch : characters) {
            if (isAlphabeticCharacter(ch)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAlphabeticCharacter(char ch) {
        return ALPHABETIC.contains(ch);
    }

}
