package uk.co.daviddunphy.pwgen.model;

import static uk.co.daviddunphy.pwgen.model.USAGE.EXCLUDED;
import static uk.co.daviddunphy.pwgen.model.USAGE.OPTIONAL;
import static uk.co.daviddunphy.pwgen.model.USAGE.REQUIRED;

class KeyProfileImpl implements KeyProfile {

    private CharacterSet requiredCharacters;
    private CharacterSet optionalCharacters;
    private int minLength;
    private int maxLength;
    private boolean alphabeticStart;

    public KeyProfileImpl(int minLength, int maxLength, boolean alphabeticStart, CharacterSet optionalCharacters,
            CharacterSet requiredCharacters, CharacterSet excludedCharacters) {
        this.requiredCharacters = new CharacterSetImpl();
        this.optionalCharacters = new CharacterSetImpl();
        // REQUIRED supersedes OPTIONAL, EXCLUDED supersedes REQUIRED
        setUsage(optionalCharacters, OPTIONAL);
        setUsage(requiredCharacters, REQUIRED);
        setUsage(excludedCharacters, EXCLUDED);
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.alphabeticStart = alphabeticStart;
    }

    public KeyProfileImpl(int minLength, int maxLength, boolean alphabeticStart, CharacterSet optionalCharacters,
            CharacterSet requiredCharacters) {
        this(minLength, maxLength, alphabeticStart, optionalCharacters, requiredCharacters, null);
    }

    public KeyProfileImpl(int minLength, int maxLength, boolean alphabeticStart, CharacterSet optionalCharacters) {
        this(minLength, maxLength, alphabeticStart, optionalCharacters, null, null);
    }

    @Override
    public void setUsage(CharacterSet charSet, USAGE usage) {
        if (charSet == null) {
            return;
        }
        switch (usage) {
        case REQUIRED:
            requiredCharacters.addAll(charSet);
            optionalCharacters.removeAll(charSet);
            break;
        case OPTIONAL:
            optionalCharacters.addAll(charSet);
            requiredCharacters.removeAll(charSet);
            break;
        case EXCLUDED:
            requiredCharacters.removeAll(charSet);
            optionalCharacters.removeAll(charSet);
            break;
        }
    }

    @Override
    public USAGE getUsage(CharacterSet charSet) {
        if (this.requiredCharacters.containsAll(charSet)) {
            return REQUIRED;
        } else if (this.getCombinedCharacterSet().containsAll(charSet)) {
            return OPTIONAL;
        }
        return EXCLUDED;
    }

    @Override
    public USAGE getUsage(char ch) {
        if (this.requiredCharacters.contains(ch)) {
            return REQUIRED;
        } else if (this.optionalCharacters.contains(ch)) {
            return OPTIONAL;
        }
        return EXCLUDED;
    }

    @Override
    public CharacterSet getRequiredCharacterSet() {
        return this.requiredCharacters;
    }

    @Override
    public CharacterSet getOptionalCharacterSet() {
        return this.optionalCharacters;
    }

    @Override
    public CharacterSet getCombinedCharacterSet() {
        return new CharacterSetImpl(this.requiredCharacters, this.optionalCharacters);
    }

    @Override
    public int getMinLength() {
        return minLength;
    }

    @Override
    public int getMaxLength() {
        return maxLength;
    }

    @Override
    public boolean isAlphabeticStart() {
        return alphabeticStart;
    }

    @Override
    public boolean matches(String key) {
        if (alphabeticStart && !CharacterSets.ALPHABETIC.contains(key.charAt(0))) {
            return false;
        }
        if (key.length() < minLength || key.length() > maxLength) {
            return false;
        }
        return this.getCombinedCharacterSet().containsAll(new CharacterSetImpl(key));
    }

}
