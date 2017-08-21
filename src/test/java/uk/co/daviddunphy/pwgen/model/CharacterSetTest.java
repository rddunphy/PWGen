package uk.co.daviddunphy.pwgen.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.testing.EqualsTester;

public class CharacterSetTest {

    private CharacterSet alphabeticCharacterSet;
    private CharacterSet numericCharacterSet;
    private CharacterSet alphanumericCharacterSet;
    private CharacterSet specialCharacterSet;
    private CharacterSet mixedCharacterSet;
    private CharacterSet emptyCharacterSet;

    @Before
    public void setUpCharacterSets() {
        alphabeticCharacterSet = new CharacterSetImpl("qwertyuiopASDFGHJKL");
        numericCharacterSet = new CharacterSetImpl("123789");
        alphanumericCharacterSet = new CharacterSetImpl("Alpha23456");
        specialCharacterSet = new CharacterSetImpl("!£$%^;,.\"");
        mixedCharacterSet = new CharacterSetImpl("C3POm,.");
        emptyCharacterSet = new CharacterSetImpl("");
    }

    @Test
    public void alphabeticCharacterSetShouldBeAlphabeticAndAlphanumeric() {
        assertTrue(alphabeticCharacterSet.isAlphabetic());
        assertTrue(alphabeticCharacterSet.isAlphanumeric());
        assertFalse(alphabeticCharacterSet.isNumeric());
    }

    @Test
    public void numericCharacterSetShouldBeNumericAndAlphanumeric() {
        assertFalse(numericCharacterSet.isAlphabetic());
        assertTrue(numericCharacterSet.isAlphanumeric());
        assertTrue(numericCharacterSet.isNumeric());
    }

    @Test
    public void alphanumericCharacterSetShouldBeAlphaNumeric() {
        assertFalse(alphanumericCharacterSet.isAlphabetic());
        assertTrue(alphanumericCharacterSet.isAlphanumeric());
        assertFalse(alphanumericCharacterSet.isNumeric());
    }

    @Test
    public void mixedAndSpecialCharacterSetsShouldNotBeAlphanumeric() {
        assertFalse(mixedCharacterSet.isAlphabetic());
        assertFalse(mixedCharacterSet.isAlphanumeric());
        assertFalse(mixedCharacterSet.isNumeric());
        assertFalse(specialCharacterSet.isAlphabetic());
        assertFalse(specialCharacterSet.isAlphanumeric());
        assertFalse(specialCharacterSet.isNumeric());
    }

    @Test
    public void emptyCharacterSetShouldBeAlphaNumeric() {
        assertTrue(emptyCharacterSet.isAlphabetic());
        assertTrue(emptyCharacterSet.isAlphanumeric());
        assertTrue(emptyCharacterSet.isNumeric());
    }

    @Test
    public void containsAlphabeticCharacterShouldIdentifyUpperCaseCharacters() {
        CharacterSet set = new CharacterSetImpl("123A4567");
        assertTrue(set.containsAlphabeticCharacter());
    }

    @Test
    public void containsAlphabeticCharacterShouldIdentifyLowerCaseCharacters() {
        CharacterSet set = new CharacterSetImpl("123x4567");
        assertTrue(set.containsAlphabeticCharacter());
    }

    @Test
    public void containsAlphabeticCharacterShouldNotMisidentifySpecialCharacters() {
        CharacterSet set = new CharacterSetImpl(CharacterSets.BASIC_SPECIAL, CharacterSets.NUMERIC);
        assertFalse(set.containsAlphabeticCharacter());
    }

    @Test
    public void containsShouldReturnTrueIfCharacterSetContainsCharacter() {
        assertTrue(alphabeticCharacterSet.contains('J'));
        assertTrue(numericCharacterSet.contains('1'));
        assertTrue(alphanumericCharacterSet.contains('p'));
        assertTrue(specialCharacterSet.contains('$'));
        assertTrue(mixedCharacterSet.contains(','));
    }

    @Test
    public void containsShouldReturnFalseIfCharacterSetDoesNotContainCharacter() {
        assertFalse(alphabeticCharacterSet.contains('V'));
        assertFalse(numericCharacterSet.contains('0'));
        assertFalse(alphanumericCharacterSet.contains(','));
        assertFalse(specialCharacterSet.contains('g'));
        assertFalse(mixedCharacterSet.contains('h'));
        assertFalse(emptyCharacterSet.contains('@'));
    }

    @Test
    public void containsAllShouldReturnTrueIfCharacterSetContainsAllCharacters() {
        CharacterSet subset = new CharacterSetImpl("SDiopAF");
        assertTrue(alphabeticCharacterSet.containsAll(subset));
        List<Character> subsetList = new ArrayList<>(Arrays.asList('2', '7', '9'));
        assertTrue(numericCharacterSet.containsAll(subsetList));
        assertTrue(emptyCharacterSet.containsAll(new ArrayList<>()));
    }

    @Test
    public void containsAllShouldReturnFalseIfCharacterSetDoesNotContainAllCharacters() {
        CharacterSet subset = new CharacterSetImpl("SDiopAbF");
        assertFalse(alphabeticCharacterSet.containsAll(subset));
        List<Character> subsetList = new ArrayList<>(Arrays.asList('2', '7', '0'));
        assertFalse(numericCharacterSet.containsAll(subsetList));
        assertFalse(emptyCharacterSet.containsAll(subsetList));
    }

    @Test
    public void sizeShouldReturnNumberOfCharactersInCharacterSet() {
        assertThat(alphabeticCharacterSet.size(), is(19));
        assertThat(numericCharacterSet.size(), is(6));
        assertThat(alphanumericCharacterSet.size(), is(10));
        assertThat(specialCharacterSet.size(), is(9));
        assertThat(mixedCharacterSet.size(), is(7));
        assertThat(emptyCharacterSet.size(), is(0));
    }

    @Test
    public void combinedConstructorShouldCreateCharacterSetWithAllCharactersFromInputSets() {
        CharacterSet a = new CharacterSetImpl("abc");
        CharacterSet b = new CharacterSetImpl("XYZ");
        CharacterSet c = new CharacterSetImpl("1aX");
        CharacterSet combined = new CharacterSetImpl(a, b, c);
        CharacterSet subset = new CharacterSetImpl("1abcXYZ");
        assertTrue(combined.containsAll(subset));
        assertThat(combined.size(), is(subset.size()));
    }

    @Test
    public void collectionConstructorShouldCreateCharacterSetWithAllCharactersFromInputSet() {
        List<Character> input = new ArrayList<>(Arrays.asList('a', 'b', '1', '&', 'a', '1', 'X'));
        CharacterSet set = new CharacterSetImpl(input);
        CharacterSet subset = new CharacterSetImpl("1&abX");
        assertTrue(set.containsAll(subset));
        assertThat(set.size(), is(subset.size()));
    }

    @Test
    public void equalsShouldReturnTrueIfCharacterSetsContainTheSameCharacters() {
        EqualsTester equalsTester = new EqualsTester();
        equalsTester.addEqualityGroup(alphabeticCharacterSet, new CharacterSetImpl("qwertyuiopASDFGHJKL"),
                new CharacterSetImpl("urSDtyurSDFKiopAqwGHJeLopA"), new CharacterSetImpl(alphabeticCharacterSet));
        equalsTester.addEqualityGroup(new CharacterSetImpl("qwertuiopASDFGHJKL"),
                new CharacterSetImpl(new CharacterSetImpl("qwertuiopAS"), new CharacterSetImpl("iopASDFGHJKL")));
        equalsTester.addEqualityGroup(mixedCharacterSet,
                new CharacterSetImpl(new ArrayList<>(Arrays.asList('C', 'P', 'm', '3', 'O', 'O', ',', '.'))));
        equalsTester.addEqualityGroup(emptyCharacterSet, new CharacterSetImpl(new ArrayList<>()));
        equalsTester.testEquals();
    }

}