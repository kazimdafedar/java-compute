package stackqueue;

/** Print helpers for stack/queue mains. */
class ArrayUtils {

    static void print(int[] a) {
        IO.print("[");
        for (int i = 0; i < a.length; i++) {
            IO.print(a[i]);
            if (i < a.length - 1) {
                IO.print(", ");
            }
        }
        IO.println("]");
    }
}
