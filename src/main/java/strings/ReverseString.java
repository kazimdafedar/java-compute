package strings;

/**
 * File 04 — Q389: Reverse String
 * In-place two pointers on char[] — O(n) time, O(1) space.
 */
class ReverseString {

    static void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    static String reverse(String s) {
        char[] chars = s.toCharArray();
        reverseString(chars);
        return new String(chars);
    }

    void main() {
        char[] chars = {'h', 'e', 'l', 'l', 'o'};
        reverseString(chars);
        IO.println(new String(chars));   // olleh
        IO.println(reverse("Java"));     // avaJ
    }
}
