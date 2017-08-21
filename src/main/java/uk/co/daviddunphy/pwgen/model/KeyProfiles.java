package uk.co.daviddunphy.pwgen.model;

import static uk.co.daviddunphy.pwgen.model.CharacterSets.LOWER_CASE;
import static uk.co.daviddunphy.pwgen.model.CharacterSets.NUMERIC;
import static uk.co.daviddunphy.pwgen.model.CharacterSets.UPPER_CASE;
import static uk.co.daviddunphy.pwgen.model.USAGE.REQUIRED;

import java.util.HashMap;
import java.util.Map;

public class KeyProfiles {

    private KeyProfiles() {
        throw new IllegalAccessError("Utility class");
    }

    private static Map<CharacterSet, USAGE> defaultUsages = new HashMap<>();

    static {
        defaultUsages.put(LOWER_CASE, REQUIRED);
        defaultUsages.put(UPPER_CASE, REQUIRED);
        defaultUsages.put(NUMERIC, REQUIRED);
    }

    public static final KeyProfile DEFAULT = new KeyProfile(defaultUsages, 10, 12, true);

}
