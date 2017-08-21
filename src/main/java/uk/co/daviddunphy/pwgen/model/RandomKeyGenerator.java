package uk.co.daviddunphy.pwgen.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

class RandomKeyGenerator implements KeyGenerator {

    private static Random rng = new Random();
    private KeyProfile profile;

    public RandomKeyGenerator(KeyProfile profile) {
        this.profile = profile;
    }

    private Character chooseRandomCharacter(CharacterSet set) {
        Set<Character> chars = set.getCharacters();
        int index = rng.nextInt(chars.size());
        return chars.toArray(new Character[chars.size()])[index];
    }

    private List<Character> generateCharacters() {
        List<Character> chars = new ArrayList<>();
        for (CharacterSet characterSet : profile.getCharacterSets()) {
            if (profile.getCharacterSetUsage(characterSet) == USAGE.REQUIRED) {
                chars.add(chooseRandomCharacter(characterSet));
            }
        }
        int length = chars.size();
        int minLength = length < profile.getMinLength() ? profile.getMinLength() : length;
        int maxLength = length < profile.getMaxLength() ? profile.getMaxLength() : length;
        length = rng.nextInt(maxLength + 1 - minLength) + minLength;
        while (chars.size() < length) {
            chars.add(chooseRandomCharacter(profile.getCombinedCharacterSet()));
        }
        return chars;
    }

    private String assembleCharaters(List<Character> characters) {
        Collections.shuffle(characters, rng);
        if (profile.isAlphabeticStart() && new CharacterSetImpl(characters).containsAlphabeticCharacter()) {
            while (!CharacterSets.ALPHABETIC.contains(characters.get(0))) {
                Collections.shuffle(characters, rng);
            }
        }
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
