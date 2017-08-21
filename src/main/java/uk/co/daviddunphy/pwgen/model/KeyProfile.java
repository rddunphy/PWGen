package uk.co.daviddunphy.pwgen.model;

public interface KeyProfile {

    public void setUsage(CharacterSet charSet, USAGE usage);

    public USAGE getUsage(CharacterSet charSet);

    public USAGE getUsage(char ch);

    public CharacterSet getRequiredCharacterSet();

    public CharacterSet getOptionalCharacterSet();

    public CharacterSet getCombinedCharacterSet();

    public int getMinLength();

    public int getMaxLength();

    public boolean isAlphabeticStart();

    public boolean matches(String key);

}
