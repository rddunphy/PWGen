package uk.co.daviddunphy.pwgen.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CharacterSetImpl implements CharacterSet {

    private List<Character> characters;

    public CharacterSetImpl(String characters) {
        this.characters = new ArrayList<>();
        for (char ch : characters.toCharArray()) {
            if (!this.characters.contains(ch)) {
                this.characters.add(ch);
            }
        }
    }

    public CharacterSetImpl(Collection<Character> characters) {
        this.characters = new ArrayList<>(characters);
        for (char ch : characters) {
            if (!this.characters.contains(ch)) {
                this.characters.add(ch);
            }
        }
    }

    @Override
    public List<Character> getCharacters() {
        return characters;
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
