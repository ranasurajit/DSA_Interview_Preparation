package Strings.Medium.P2_String_To_Integer_ATOI;

public class String_To_Integer_ATOI {
    public static void main(String[] args) {
        String_To_Integer_ATOI solution = new String_To_Integer_ATOI();

        String s1 = "42";
        int atoi1 = solution.myAtoi(s1);
        System.out.println(atoi1);

        String s2 = " -042";
        int atoi2 = solution.myAtoi(s2);
        System.out.println(atoi2);

        String s3 = "1337c0d3";
        int atoi3 = solution.myAtoi(s3);
        System.out.println(atoi3);
    }

    /**
     * Approach : Using String Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int myAtoi(String s) {
        s = s.trim();
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        long value = 0L;
        boolean hasNeg = false;
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = s.charAt(i);
            if (i == 0) {
                if (ch == ' ') {
                    continue;
                } else if (ch == '+') {
                    hasNeg = false;
                    continue;
                } else if (ch == '-') {
                    hasNeg = true;
                    continue;
                }
            }
            if (ch >= '0' && ch <= '9') {
                int digit = (ch - '0');
                value = value * 10 + digit;
                if (hasNeg) {
                    long computedVal = value * -1;
                    if (computedVal <= Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    }
                } else {
                    long computedVal = value;
                    if (computedVal >= Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                }
            } else {
                break;
            }
        }
        if (hasNeg) {
            return -1 * (int) value;
        }
        return (int) value;
    }
}
