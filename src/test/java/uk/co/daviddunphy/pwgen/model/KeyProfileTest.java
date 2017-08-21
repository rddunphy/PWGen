package uk.co.daviddunphy.pwgen.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class KeyProfileTest {

    private KeyProfile simpleProfile;
    private KeyProfile secureProfile;

    @Before
    public void setUpKeyProfiles() {
        simpleProfile = new KeyProfileImpl(8, 10, true, CharacterSets.NUMERIC, CharacterSets.ALPHABETIC);
        secureProfile = new KeyProfileImpl(14, 16, false, new CharacterSetImpl("!£$%^&*()@~#"), CharacterSets.ALPHANUMERIC,
                new CharacterSetImpl("\",./"));
    }

    @Test
    public void testGetCharacterSetUsage() {
        assertThat(simpleProfile.getUsage(CharacterSets.ALPHABETIC), is(USAGE.REQUIRED));
    }

    @Test
    public void testGetCharacterSets() {
    }

    @Test
    public void testGetCombinedCharacterSet() {
    }

    @Test
    public void testGetMinLength() {
    }

    @Test
    public void testGetMaxLength() {
    }

    @Test
    public void testIsAlphabeticStart() {
    }

}
