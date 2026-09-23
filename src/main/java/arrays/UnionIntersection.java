package arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * File 04 — Q413: Union and Intersection of Two Arrays
 * Set-based — O(n + m) time.
 */
class UnionIntersection {

    static int[] union(int[] a, int[] b) {
        Set<Integer> set = new TreeSet<>();
        for (int x : a) {
            set.add(x);
        }
        for (int x : b) {
            set.add(x);
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    static int[] intersection(int[] a, int[] b) {
        Set<Integer> inB = new HashSet<>();
        for (int x : b) {
            inB.add(x);
        }
        Set<Integer> result = new TreeSet<>();
        for (int x : a) {
            if (inB.contains(x)) {
                result.add(x);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    void main() {
        int[] a = {1, 2, 2, 1};
        int[] b = {2, 2};
        IO.println("union:         " + Arrays.toString(union(a, b)));
        IO.println("intersection:  " + Arrays.toString(intersection(a, b)));
    }
}
