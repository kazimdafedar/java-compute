package strings;

/**
 * File 04 — Q388: Compare Version Numbers
 * Split and compare segments — O(n) time.
 */
class CompareVersionNumbers {

    static int compareVersion(String v1, String v2) {
        String[] a = v1.split("\\.");
        String[] b = v2.split("\\.");
        int n = Math.max(a.length, b.length);
        for (int i = 0; i < n; i++) {
            int x = i < a.length ? Integer.parseInt(a[i]) : 0;
            int y = i < b.length ? Integer.parseInt(b[i]) : 0;
            if (x != y) {
                return x < y ? -1 : 1;
            }
        }
        return 0;
    }

    void main() {
        IO.println(compareVersion("1.01", "1.001"));     // 0
        IO.println(compareVersion("1.0", "1.0.0"));      // 0
        IO.println(compareVersion("0.1", "1.1"));        // -1
    }
}
