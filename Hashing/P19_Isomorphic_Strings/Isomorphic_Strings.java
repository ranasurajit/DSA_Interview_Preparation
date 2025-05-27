package Hashing.P19_Isomorphic_Strings;

import java.util.HashMap;
import java.util.Map;

public class Isomorphic_Strings {
    public static void main(String[] args) {
        Isomorphic_Strings solution = new Isomorphic_Strings();

        String s1 = "egg", t1 = "add";
        boolean isIsomorphicString1 = solution.isIsomorphicApproachI(s1, t1);
        System.out.println(isIsomorphicString1);

        String s2 = "paper", t2 = "title";
        boolean isIsomorphicString2 = solution.isIsomorphicApproachII(s2, t2);
        System.out.println(isIsomorphicString2);
    }

    /**
     * Approach II : Using Hashing Approach (Clean Approach)
     * 
     * TC: O(N)
     * SC: O(2 x 257) ~ O(1)
     */
    public boolean isIsomorphicApproachII(String s, String t) {
        int[] sMap = new int[257]; // as ASCII characters are 256
        int[] tMap = new int[257]; // as ASCII characters are 256
        for (int i = 0; i < s.length(); i++) {
            if (sMap[(int) s.charAt(i)] != tMap[(int) t.charAt(i)]) {
                return false;
            }
            sMap[(int) s.charAt(i)] = i + 1;
            tMap[(int) t.charAt(i)] = i + 1;
        }
        return true;
    }

    /**
     * Approach I : Using Hashing Approach
     * 
     * TC: O(N)
     * SC: O(2 x N) ~ O(N)
     */
    public boolean isIsomorphicApproachI(String s, String t) {
        Map<Character, Character> map = new HashMap<Character, Character>(); // SC: O(N)
        Map<Character, Character> revMap = new HashMap<Character, Character>(); // SC: O(N)
        int n = s.length();
        for (int i = 0; i < n; i++) { // TC: O(N)
            char sch = s.charAt(i);
            char tch = t.charAt(i);
            if (map.containsKey(sch) && map.get(sch) != tch) {
                return false;
            } else {
                if (revMap.containsKey(tch) && revMap.get(tch) != sch) {
                    return false;
                }
                map.put(sch, tch);
                revMap.put(tch, sch);
            }
        }
        return true;
    }
}
