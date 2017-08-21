package uk.co.daviddunphy.pwgen.model;

import static uk.co.daviddunphy.pwgen.model.USAGE.EXCLUDED;

import java.util.Map;
import java.util.Set;

public class KeyProfile {

    private Map<CharacterSet, USAGE> characterSetUsages;
    private int minLength;
    private int maxLength;

    public KeyProfile(Map<CharacterSet, USAGE> characterSetUsages, int minLength, int maxLength) {
        this.characterSetUsages = characterSetUsages;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public USAGE getCharacterSetUsage(CharacterSet characterSet) {
        if (characterSetUsages.containsKey(characterSet)) {
            return characterSetUsages.get(characterSet);
        } else {
            return EXCLUDED;
        }
    }

    public Set<CharacterSet> getCharacterSets() {
        return characterSetUsages.keySet();
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public boolean isStartAlpha() {
        return false; // TODO
    }

}
