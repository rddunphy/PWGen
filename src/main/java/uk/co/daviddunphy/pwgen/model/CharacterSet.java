package uk.co.daviddunphy.pwgen.model;

import java.util.Collection;
import java.util.List;

public interface CharacterSet {

    public List<Character> getCharacters();

    public boolean isAlphanumeric();

    public boolean isAlphabetic();

    public boolean isNumeric();

    public boolean contains(char ch);

    public boolean containsAll(Collection<Character> chars);

    public boolean containsAll(CharacterSet chars);

    public int size();

}
