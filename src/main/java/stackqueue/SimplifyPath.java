package stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * File 04 — Q375: Simplify Path
 * Stack of directory components — O(n) time.
 */
class SimplifyPath {

    static String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        for (String part : path.split("/")) {
            if (part.isEmpty() || ".".equals(part)) {
                continue;
            }
            if ("..".equals(part)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(part);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (String dir : stack) {
            sb.insert(0, "/" + dir);
        }
        return sb.toString();
    }

    void main() {
        IO.println(simplifyPath("/a/./b/../../c/"));   // /c
        IO.println(simplifyPath("/../"));               // /
        IO.println(simplifyPath("/home//foo/"));       // /home/foo
    }
}
