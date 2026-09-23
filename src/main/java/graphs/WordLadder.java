package graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * File 04 — Q338: Word Ladder
 * BFS shortest transformation — O(N * L²) time.
 */
class WordLadder {

    static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictionary = new HashSet<>(wordList);
        if (!dictionary.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int steps = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return steps;
                }
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char original = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String next = new String(chars);
                        if (dictionary.remove(next)) {
                            queue.add(next);
                        }
                    }
                    chars[i] = original;
                }
            }
            steps++;
        }
        return 0;
    }

    void main() {
        int length = ladderLength(
            "hit",
            "cog",
            List.of("hot", "dot", "dog", "lot", "log", "cog")
        );
        IO.println(length);   // 5
    }
}
