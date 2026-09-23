package concurrency;

/**
 * File 01 Q24 + File 11 CC23: Thread-safe singleton.
 * Enum (best), holder idiom (preferred lazy), DCL with volatile.
 */
class ThreadSafeSingleton {

    enum EnumSingleton {
        INSTANCE;

        String label() {
            return "enum-singleton";
        }
    }

    static final class HolderSingleton {
        private HolderSingleton() {}

        private static class Holder {
            static final HolderSingleton INSTANCE = new HolderSingleton();
        }

        static HolderSingleton getInstance() {
            return Holder.INSTANCE;
        }
    }

    static final class DclSingleton {
        private static volatile DclSingleton instance;

        private DclSingleton() {}

        static DclSingleton getInstance() {
            DclSingleton local = instance;
            if (local == null) {
                synchronized (DclSingleton.class) {
                    local = instance;
                    if (local == null) {
                        instance = local = new DclSingleton();
                    }
                }
            }
            return local;
        }
    }

    void main() {
        IO.println(EnumSingleton.INSTANCE == EnumSingleton.INSTANCE);           // true
        IO.println(HolderSingleton.getInstance() == HolderSingleton.getInstance()); // true
        IO.println(DclSingleton.getInstance() == DclSingleton.getInstance());   // true
        IO.println(EnumSingleton.INSTANCE.label());
    }
}
