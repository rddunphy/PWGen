package uk.co.daviddunphy.pwgen.model;

import static uk.co.daviddunphy.pwgen.model.USAGE.EXCLUDED;

import java.util.Map;
import java.util.Set;

public class KeyProfileImpl implements KeyProfile {

    private Map<CharacterSet, USAGE> characterSetUsages;
    private int minLength;
    private int maxLength;
    private boolean alphabeticStart;

    public KeyProfileImpl(Map<CharacterSet, USAGE> characterSetUsages, int minLength, int maxLength, boolean alphabeticStart) {
        this.characterSetUsages = characterSetUsages;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.alphabeticStart = alphabeticStart;
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

    public CharacterSet getCombinedCharacterSet() {
        Set<CharacterSet> sets = getCharacterSets();
        return new CharacterSetImpl(sets.toArray(new CharacterSet[sets.size()]));
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public boolean isAlphabeticStart() {
        return alphabeticStart;
    }

}
