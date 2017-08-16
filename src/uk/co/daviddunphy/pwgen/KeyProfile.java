package uk.co.daviddunphy.pwgen;

import static uk.co.daviddunphy.pwgen.USAGE.EXCLUDED;
import static uk.co.daviddunphy.pwgen.USAGE.REQUIRED;

public class KeyProfile {

    public static final KeyProfile DEFAULT = new KeyProfile(REQUIRED, REQUIRED, REQUIRED, EXCLUDED, CharacterSets.BASIC_SPECIAL);

    private USAGE upperCaseUsage;
    private USAGE lowerCaseUsage;
    private USAGE numericUsage;
    private USAGE specialUsage;
    private String specialCharacterSet;

    public KeyProfile(USAGE upperCaseUsage, USAGE lowerCaseUsage, USAGE numericUsage, USAGE specialUsage, String specialCharacterSet) {
        this.setUpperCaseUsage(upperCaseUsage);
        this.setLowerCaseUsage(lowerCaseUsage);
        this.setNumericUsage(numericUsage);
        this.setSpecialUsage(specialUsage);
        this.setSpecialCharacterSet(specialCharacterSet);
    }

    /**
     * @return the upperCaseUsage
     */
    public USAGE getUpperCaseUsage() {
        return upperCaseUsage;
    }

    /**
     * @param upperCaseUsage
     *            the upperCaseUsage to set
     */
    public void setUpperCaseUsage(USAGE upperCaseUsage) {
        this.upperCaseUsage = upperCaseUsage;
    }

    /**
     * @return the lowerCaseUsage
     */
    public USAGE getLowerCaseUsage() {
        return lowerCaseUsage;
    }

    /**
     * @param lowerCaseUsage
     *            the lowerCaseUsage to set
     */
    public void setLowerCaseUsage(USAGE lowerCaseUsage) {
        this.lowerCaseUsage = lowerCaseUsage;
    }

    /**
     * @return the numericUsage
     */
    public USAGE getNumericUsage() {
        return numericUsage;
    }

    /**
     * @param numericUsage
     *            the numericUsage to set
     */
    public void setNumericUsage(USAGE numericUsage) {
        this.numericUsage = numericUsage;
    }

    /**
     * @return the specialUsage
     */
    public USAGE getSpecialUsage() {
        return specialUsage;
    }

    /**
     * @param specialUsage
     *            the specialUsage to set
     */
    public void setSpecialUsage(USAGE specialUsage) {
        this.specialUsage = specialUsage;
    }

    /**
     * @return the specialCharacterSet
     */
    public String getSpecialCharacterSet() {
        return specialCharacterSet;
    }

    /**
     * @param specialCharacterSet
     *            the specialCharacterSet to set
     */
    public void setSpecialCharacterSet(String specialCharacterSet) {
        this.specialCharacterSet = specialCharacterSet;
    }

}
