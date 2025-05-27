package Hashing.P21_Ransom_Note;

public class Ransom_Note {
    public static void main(String[] args) {
        Ransom_Note solution = new Ransom_Note();

        String ransomNote = "aa", magazine = "aab";
        boolean isPossible = solution.canConstruct(ransomNote, magazine);
        System.out.println(isPossible);
    }

    /**
     * Approach : Using Hashing Approach
     * 
     * TC: O(M + N)
     * SC: O(1)
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] map = new int[26]; // SC: O(26) ~ O(1)
        for (char ch : ransomNote.toCharArray()) { // TC: O(M)
            map[ch - 'a']++;
        }
        for (char ch : magazine.toCharArray()) { // TC: O(N)
            map[ch - 'a']--;
        }
        for (int i = 0; i < 26; i++) { // TC: O(26) ~ O(1)
            if (map[i] > 0) {
                return false;
            }
        }
        return true;
    }
}
