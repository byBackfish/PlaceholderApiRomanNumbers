package de.bybackfish.papiformatter.formatters;

import org.bukkit.OfflinePlayer;

import java.util.Map;

public class RomanToArabicFormatter extends AbstractFormatter {
    private static final Map<Character, Integer> ROMAN_MAP = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    @Override
    public String getName() {
        return "roman_to_arabic";
    }

    @Override
    public String format(OfflinePlayer player, String[] parts) {
        if (parts.length < 2) return null;
        String roman = parts[1];
        int arabic = romanToArabic(roman);
        return Integer.toString(arabic);
    }

    int romanToArabic(String roman) {
        int result = 0;
        int lastNumber = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            int currentNumber = ROMAN_MAP.getOrDefault(roman.charAt(i), 0);
            if (currentNumber < lastNumber) {
                result -= currentNumber;
            } else {
                result += currentNumber;
            }
            lastNumber = currentNumber;
        }
        return result;
    }
}
