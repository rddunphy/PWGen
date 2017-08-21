package uk.co.daviddunphy.pwgen.model;

import java.util.Collection;
import java.util.Set;

public interface CharacterSet {

    public Set<Character> getCharacters();

    public void addAll(CharacterSet key);

    public void removeAll(CharacterSet charSet);

    public boolean isAlphanumeric();

    public boolean isAlphabetic();

    public boolean isNumeric();

    public boolean containsAlphabeticCharacter();

    public boolean contains(char ch);

    public boolean containsAll(Collection<Character> chars);

    public boolean containsAll(CharacterSet chars);

    public int size();

}
