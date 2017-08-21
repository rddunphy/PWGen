package uk.co.daviddunphy.pwgen.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class CharacterSetImpl implements CharacterSet {

    private Set<Character> characters;

    public CharacterSetImpl(String characters) {
        this.characters = new HashSet<>();
        for (char ch : characters.toCharArray()) {
            this.characters.add(ch);
        }
    }

    public CharacterSetImpl(Collection<Character> characters) {
        this.characters = new HashSet<>();
        this.characters.addAll(characters);
    }

    public CharacterSetImpl(CharacterSet... characterSets) {
        this.characters = new HashSet<>();
        for (CharacterSet set : characterSets) {
            addAll(set);
        }
    }

    @Override
    public void addAll(CharacterSet charSet) {
        characters.addAll(charSet.getCharacters());
    }

    @Override
    public void removeAll(CharacterSet charSet) {
        characters.removeAll(charSet.getCharacters());
    }

    @Override
    public Set<Character> getCharacters() {
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
    public boolean containsAlphabeticCharacter() {
        for (char ch : characters) {
            if (CharacterSets.ALPHABETIC.contains(ch)) {
                return true;
            }
        }
        return false;
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
        return c.containsAll(characters) && this.containsAll(c.getCharacters());
    }

}
