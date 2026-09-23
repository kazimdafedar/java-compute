package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Accounts Merge — Union-Find on emails, then group by root.
 * O(n · α(n) + n log n) for sorted emails.
 */
class AccountsMerge {

    static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parent = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        for (List<String> account : accounts) {
            String name = account.get(0);
            String first = account.get(1);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                parent.putIfAbsent(email, email);
                parent.putIfAbsent(first, first);
                union(parent, first, email);
                emailToName.put(email, name);
            }
        }

        Map<String, TreeSet<String>> groups = new HashMap<>();
        for (String email : parent.keySet()) {
            groups.computeIfAbsent(find(parent, email), k -> new TreeSet<>()).add(email);
        }

        List<List<String>> merged = new ArrayList<>();
        for (var e : groups.entrySet()) {
            List<String> row = new ArrayList<>();
            row.add(emailToName.get(e.getKey()));
            row.addAll(e.getValue());
            merged.add(row);
        }
        return merged;
    }

    static String find(Map<String, String> parent, String x) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent, parent.get(x)));
        }
        return parent.get(x);
    }

    static void union(Map<String, String> parent, String a, String b) {
        String ra = find(parent, a);
        String rb = find(parent, b);
        if (!ra.equals(rb)) {
            parent.put(rb, ra);
        }
    }

    void main() {
        List<List<String>> accounts = List.of(
                List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                List.of("John", "johnsmith@mail.com", "john00@mail.com"),
                List.of("Mary", "mary@mail.com"),
                List.of("John", "johnnybravo@mail.com")
        );
        IO.println(accountsMerge(accounts));
    }
}
