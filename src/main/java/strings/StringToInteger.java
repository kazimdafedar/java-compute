package strings;

/**
 * File 04 — Q383: String to Integer (atoi)
 * Parse with overflow guard — O(n) time, O(1) space.
 */
class StringToInteger {

    static int myAtoi(String s) {
        int i = 0;
        int n = s.length();
        int sign = 1;

        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        long val = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            val = val * 10 + (s.charAt(i) - '0');
            i++;
            if (sign == 1 && val > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -val < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        return (int) (sign * val);
    }

    void main() {
        IO.println(myAtoi("   -42"));       // -42
        IO.println(myAtoi("4193 with words")); // 4193
        IO.println(myAtoi("91283472332"));  // 2147483647 (overflow clamp)
    }
}
