package uk.co.daviddunphy.pwgen.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

class CharacterSetImpl implements CharacterSet {

    private List<Character> characters;

    public CharacterSetImpl(String characters) {
        this.characters = new ArrayList<>();
        for (char ch : characters.toCharArray()) {
            if (!contains(ch)) {
                this.characters.add(ch);
            }
        }
    }

    public CharacterSetImpl(Collection<Character> characters) {
        this.characters = new ArrayList<>();
        for (char ch : characters) {
            if (!contains(ch)) {
                this.characters.add(ch);
            }
        }
    }

    public CharacterSetImpl(CharacterSet... characterSets) {
        this.characters = new ArrayList<>();
        for (CharacterSet set : characterSets) {
            for (char ch : set.getCharacters()) {
                if (!contains(ch)) {
                    this.characters.add(ch);
                }
            }
        }
    }

    @Override
    public List<Character> getCharacters() {
        return characters;
    }

    @Override
    public boolean isAlphanumeric() {
        return CharacterSets.ALPHANUMERIC.containsAll(this);
    }

    @Override
    public boolean isAlphabetic() {
        return CharacterSets.ALPHABETIC.containsAll(this);
    }

    @Override
    public boolean isNumeric() {
        return CharacterSets.NUMERIC.containsAll(this);
    }

    @Override
    public boolean contains(char ch) {
        return this.characters.contains(ch);
    }

    @Override
    public boolean containsAll(Collection<Character> chars) {
        return this.characters.containsAll(chars);
    }

    @Override
    public boolean containsAll(CharacterSet chars) {
        return this.containsAll(chars.getCharacters());
    }

    @Override
    public int size() {
        return this.characters.size();
    }

    @Override
    public String toString() {
        return Arrays.toString(characters.toArray());
    }

    @Override
    public int hashCode() {
        return Objects.hash(characters);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CharacterSet)) {
            return false;
        }
        CharacterSet c = (CharacterSet) o;
        return c.getCharacters().equals(characters);
    }

}
