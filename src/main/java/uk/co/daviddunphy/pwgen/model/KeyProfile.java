package uk.co.daviddunphy.pwgen.model;

import java.util.Set;

public interface KeyProfile {

    public USAGE getCharacterSetUsage(CharacterSet characterSet);

    public Set<CharacterSet> getCharacterSets();

    public CharacterSet getCombinedCharacterSet();

    public int getMinLength();

    public int getMaxLength();

    public boolean isAlphabeticStart();

}
