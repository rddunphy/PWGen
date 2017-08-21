package uk.co.daviddunphy.pwgen.model;

public interface KeyGenerator {

    /**
     * @return A new generated key
     */
    public String generate();

    /**
     * @return KeyProfile currently in use
     */
    public KeyProfile getProfile();

    /**
     * @param profile
     *            New KeyProfile to use
     */
    public void setProfile(KeyProfile profile);

}
