package uk.co.daviddunphy.pwgen.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomKeyGenerator implements KeyGenerator {

    private static Random rng = new Random();
    private KeyProfile profile;

    public RandomKeyGenerator(KeyProfile profile) {
        this.profile = profile;
    }

    private Character chooseRandomCharacter(CharacterSet set) {
        List<Character> chars = set.getCharacters();
        return chars.get(rng.nextInt(chars.size()));
    }

    private List<Character> generateCharacters() {
        List<Character> chars = new ArrayList<>();
        for (CharacterSet characterSet : profile.getCharacterSets()) {
            if (profile.getCharacterSetUsage(characterSet) == USAGE.REQUIRED) {
                chars.add(chooseRandomCharacter(characterSet));
            }
        }
        int minLength = chars.size() < profile.getMinLength() ? profile.getMinLength() : chars.size();
        int maxLength = chars.size() < profile.getMaxLength() ? profile.getMaxLength() : chars.size();
        int length = rng.nextInt(maxLength + 1 - minLength) + minLength;
        while (chars.size() < length) {
            chars.add(chooseRandomCharacter(CharacterSets.combine(profile.getCharacterSets())));
        }
        return chars;
    }

    private String assembleCharaters(List<Character> characters) {
        Collections.shuffle(characters, rng);
        StringBuilder builder = new StringBuilder(characters.size());
        for (char ch : characters) {
            builder.append(ch);
        }
        return builder.toString();
    }

    @Override
    public String generate() {
        return assembleCharaters(generateCharacters());
    }

    @Override
    public KeyProfile getProfile() {
        return profile;
    }

    @Override
    public void setProfile(KeyProfile profile) {
        this.profile = profile;
    }

}
