package Strings.Basics.P6_Roman_To_Integer;

import java.util.HashMap;
import java.util.Map;

public class Roman_To_Integer {
    public static void main(String[] args) {
        Roman_To_Integer solution = new Roman_To_Integer();

        String s1 = "LVIII";
        int romanToInt1 = solution.romanToIntUsingHashing(s1);
        System.out.println(romanToInt1);

        String s2 = "MCMXCIV";
        int romanToInt2 = solution.romanToIntUsingHashing(s2);
        System.out.println(romanToInt2);
    }

    /**
     * Approach II : Using String Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int romanToIntOptimal(String s) {
        int n = s.length();
        int result = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = s.charAt(i);
            if (ch == 'I') {
                result += 1;
            } else if (ch == 'V') {
                result += 5;
                if (i > 0 && s.charAt(i - 1) == 'I') {
                    // negating -1 twice as it may be added previously
                    result -= 2;
                }
            } else if (ch == 'X') {
                result += 10;
                if (i > 0 && s.charAt(i - 1) == 'I') {
                    // negating -1 twice as it may be added previously
                    result -= 2;
                }
            } else if (ch == 'L') {
                result += 50;
                if (i > 0 && s.charAt(i - 1) == 'X') {
                    // negating -10 twice as it may be added previously
                    result -= 20;
                }
            } else if (ch == 'C') {
                result += 100;
                if (i > 0 && s.charAt(i - 1) == 'X') {
                    // negating -10 twice as it may be added previously
                    result -= 20;
                }
            } else if (ch == 'D') {
                result += 500;
                if (i > 0 && s.charAt(i - 1) == 'C') {
                    // negating -100 twice as it may be added previously
                    result -= 200;
                }
            } else if (ch == 'M') {
                result += 1000;
                if (i > 0 && s.charAt(i - 1) == 'C') {
                    // negating -100 twice as it may be added previously
                    result -= 200;
                }
            }
        }
        return result;
    }

    /**
     * Approach I : Using Hashing Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int romanToIntUsingHashing(String s) {
        int n = s.length();
        Map<Character, Integer> map = getMappings(); // TC: O(7), SC: O(7)
        int result = map.get(s.charAt(0));
        for (int i = 1; i < n; i++) { // TC: O(N)
            if (map.get(s.charAt(i)) > map.get(s.charAt(i - 1))) {
                result += map.get(s.charAt(i)) - 2 * map.get(s.charAt(i - 1));
            } else {
                result += map.get(s.charAt(i));
            }
        }
        return result;
    }

    /**
     * Using Hashing Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    private Map<Character, Integer> getMappings() {
        Map<Character, Integer> map = new HashMap<Character, Integer>(); // SC: O(7) ~ SC: O(1)
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        return map;
    }
}
