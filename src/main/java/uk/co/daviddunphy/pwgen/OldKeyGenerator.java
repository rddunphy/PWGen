package uk.co.daviddunphy.pwgen;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class OldKeyGenerator {

	public static int PASSWORD = 0;
	public static int SIMPLE = 1;
	public static int SECURE = 2;
	public static int EXTRA = 3;
	public static int PIN = 4;
	public static int CUSTOM = 5;
	
	private String lowerChars;
	private String upperChars;
	private String numericChars;
	private String specialChars;
	private String defaultSpecialChars;

	private int defaultType;
	private int minLength;
	private int maxLength;
	private boolean startAlpha;
	private boolean lower;
	private boolean upper;
	private boolean numeric;
	private boolean special;
	
	private int customMin;
	private int customMax;
	private boolean customStart;
	private boolean customLower;
	private boolean customUpper;
	private boolean customNumeric;
	private boolean customSpecial;
	private String customSpecialChars;

	public OldKeyGenerator() {
		initialiseCharSets();
		loadState();
		setToDefault();
	}

	private void initialiseCharSets() {
		lowerChars = "abcdefghijklmnopqrstuvwxyz";
		upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		numericChars = "0123456789";
	}

	public void setToDefault() {
		if (defaultType == PASSWORD) {
			minLength = 8;
			maxLength = 10;
			startAlpha = true;
			lower = true;
			upper = true;
			numeric = true;
			special = false;
			defaultSpecialChars = "!$&*()-=+;:@#?<>";
		} else if (defaultType == SIMPLE) {
			minLength = 5;
			maxLength = 7;
			startAlpha = true;
			lower = true;
			upper = false;
			numeric = false;
			special = false;
			defaultSpecialChars = "!$&*()-=+;:@#?<>";
		} else if (defaultType == SECURE) {
			minLength = 11;
			maxLength = 13;
			startAlpha = true;
			lower = true;
			upper = true;
			numeric = true;
			special = true;
			defaultSpecialChars = "!$&*()-=+;:@#?<>";
		} else if (defaultType == EXTRA) {
			minLength = 14;
			maxLength = 16;
			startAlpha = true;
			lower = true;
			upper = true;
			numeric = true;
			special = true;
			defaultSpecialChars = "!\"£$%^&*()-_=+{[]};:'@#~?<>";
		} else if (defaultType == PIN) {
			minLength = 4;
			maxLength = 4;
			startAlpha = false;
			lower = false;
			upper = false;
			numeric = true;
			special = false;
			defaultSpecialChars = "!$&*()-=+;:@#?<>";
		} else if (defaultType == CUSTOM) {
			minLength = customMin;
			maxLength = customMax;
			startAlpha = customStart;
			lower = customLower;
			upper = customUpper;
			numeric = customNumeric;
			special = customSpecial;
			defaultSpecialChars = customSpecialChars;
		}
		specialChars = defaultSpecialChars;
	}

	public String generate() {
		String poolAlpha = "";
		String poolOther = "";
		String charList = "";
		if (isLower()) {
			charList += lowerChars;
			poolAlpha += generateChar(lowerChars);
		}
		if (isUpper()) {
			charList += upperChars;
			poolAlpha += generateChar(upperChars);
		}
		if (isNumeric()) {
			charList += numericChars;
			poolOther += generateChar(numericChars);
		}
		if (isSpecial()) {
			charList += specialChars;
			poolOther += generateChar(specialChars);
		}
		int currentLength = poolAlpha.length() + poolOther.length();
		int targetLength = generateLength(currentLength);
		String alphaCharList = lowerChars + upperChars;
		while (currentLength < targetLength) {
			String c = generateChar(charList);
			if (alphaCharList.contains(c)) {
				poolAlpha += c;
			} else {
				poolOther += c;
			}
			currentLength++;
		}
		String key = arrange(poolAlpha, poolOther);
		return key;
	}

	private String generateChar(String charList) {
		Random r = new Random();
		int index = r.nextInt(charList.length());
		return charList.substring(index, index + 1);
	}

	private int generateLength(int currentLength) {
		Random r = new Random();
		int min = getMinLength();
		if (currentLength > min) {
			min = currentLength;
		}
		int l = min + r.nextInt(getMaxLength() + 1 - min);
		return l;
	}

	private String arrange(String poolAlpha, String poolOther) {
		String key = "";
		Random r = new Random();
		int index;
		if (isStartAlpha() && poolAlpha.length() != 0) {
			index = r.nextInt(poolAlpha.length());
			key += poolAlpha.substring(index, index + 1);
			poolAlpha = poolAlpha.substring(0, index)
					+ poolAlpha.substring(index + 1);
		}
		String pool = poolAlpha + poolOther;
		while (pool.length() > 0) {
			index = r.nextInt(pool.length());
			key += pool.substring(index, index + 1);
			pool = pool.substring(0, index) + pool.substring(index + 1);
		}
		return key;
	}
	
	public void setAsCustom() {
		customMin = minLength;
		customMax = maxLength;
		customStart = startAlpha;
		customLower = lower;
		customUpper = upper;
		customNumeric = numeric;
		customSpecial = special;
		customSpecialChars = specialChars;
		saveState();
	}

	public void setSpecialChars(String s) {
		specialChars = s;
	}

	public String getSpecialChars() {
		return specialChars;
	}

	public void addSpecialChar(char c) {
		String s = "" + c;
		if (!specialChars.contains(s)) {
			setSpecialChars(specialChars + s);
		}
	}

	/**
	 * @return the minLength
	 */
	public int getMinLength() {
		return minLength;
	}

	/**
	 * @param minLength
	 *            the minLength to set
	 */
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	/**
	 * @return the maxLength
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * @param maxLength
	 *            the maxLength to set
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * @return the lower
	 */
	public boolean isLower() {
		return lower;
	}

	/**
	 * @param lower
	 *            the lower to set
	 */
	public void setLower(boolean lower) {
		this.lower = lower;
	}

	/**
	 * @return the upper
	 */
	public boolean isUpper() {
		return upper;
	}

	/**
	 * @param upper
	 *            the upper to set
	 */
	public void setUpper(boolean upper) {
		this.upper = upper;
	}

	/**
	 * @return the numeric
	 */
	public boolean isNumeric() {
		return numeric;
	}

	/**
	 * @param numeric
	 *            the numeric to set
	 */
	public void setNumeric(boolean numeric) {
		this.numeric = numeric;
	}

	/**
	 * @return the special
	 */
	public boolean isSpecial() {
		return special;
	}

	/**
	 * @param special
	 *            the special to set
	 */
	public void setSpecial(boolean special) {
		this.special = special;
	}

	/**
	 * @return the startAlpha
	 */
	public boolean isStartAlpha() {
		return startAlpha;
	}

	/**
	 * @param startAlpha
	 *            the startAlpha to set
	 */
	public void setStartAlpha(boolean startAlpha) {
		this.startAlpha = startAlpha;
	}

	/**
	 * @return the defaultType
	 */
	public int getDefaultType() {
		return defaultType;
	}

	/**
	 * @param defaultType
	 *            the defaultType to set
	 */
	public void setDefaultType(int defaultType) {
		this.defaultType = defaultType;
	}
	
	public void saveState() {
		try {
			FileWriter fw = new FileWriter(new File("custom.txt"));
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Integer.toString(defaultType));
			bw.newLine();
			bw.write(Integer.toString(customMin));
			bw.newLine();
			bw.write(Integer.toString(customMax));
			bw.newLine();
			bw.write(Boolean.toString(customStart));
			bw.newLine();
			bw.write(Boolean.toString(customLower));
			bw.newLine();
			bw.write(Boolean.toString(customUpper));
			bw.newLine();
			bw.write(Boolean.toString(customNumeric));
			bw.newLine();
			bw.write(Boolean.toString(customSpecial));
			bw.newLine();
			bw.write(customSpecialChars);
			bw.close();
		} catch (IOException e) {
			
		}
	}
	
	private void loadState() {
		try {
			FileReader fr = new FileReader("custom.txt");
			BufferedReader br = new BufferedReader(fr);
			Scanner sc = new Scanner(br);
			defaultType = sc.nextInt();
			customMin = sc.nextInt();
			customMax = sc.nextInt();
			customStart = sc.nextBoolean();
			customLower = sc.nextBoolean();
			customUpper = sc.nextBoolean();
			customNumeric = sc.nextBoolean();
			customSpecial = sc.nextBoolean();
			sc.nextLine();
			customSpecialChars = sc.nextLine();
			sc.close();
		} catch (IOException e) {
			defaultType = PASSWORD;
			customMin = 8;
			customMax = 10;
			customStart = true;
			customLower = true;
			customUpper = true;
			customNumeric = true;
			customSpecial = false;
			customSpecialChars = "!$&*()-=+;:@#?<>";
		}
	}
}
