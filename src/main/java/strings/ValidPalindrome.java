package strings;

/**
 * File 04 — Q382: Valid Palindrome
 * Two pointers, skip non-alphanumeric — O(n) time, O(1) space.
 */
class ValidPalindrome {

    static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    void main() {
        IO.println(isPalindrome("A man, a plan, a canal: Panama"));   // true
        IO.println(isPalindrome("race a car"));                       // false
    }
}
