package uk.co.daviddunphy.pwgen.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CharacterSets {

    private CharacterSets() {
        throw new IllegalAccessError("Utility class");
    }

    public static final CharacterSet UPPER_CASE = new CharacterSetImpl("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    public static final CharacterSet LOWER_CASE = new CharacterSetImpl("abcdefghijklmnopqrstuvwxyz");
    public static final CharacterSet NUMERIC = new CharacterSetImpl("0123456789");
    public static final CharacterSet BASIC_SPECIAL = new CharacterSetImpl("!$&*()-=+;:@#?<>");

    public static CharacterSet combine(Set<CharacterSet> characterSets) {
        List<Character> chars = new ArrayList<>();
        for (CharacterSet set : characterSets) {
            for (Character ch : set.getCharacters()) {
                if (!chars.contains(ch)) {
                    chars.add(ch);
                }
            }
        }
        return new CharacterSetImpl(chars);
    }

}
