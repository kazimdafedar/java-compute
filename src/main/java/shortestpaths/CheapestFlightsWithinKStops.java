package shortestpaths;

import java.util.Arrays;

/**
 * File 04 — Q345: Cheapest Flights Within K Stops
 * Bellman-Ford style bounded relaxation — O(E * K) time.
 */
class CheapestFlightsWithinKStops {

    static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] temp = dist.clone();
            for (int[] flight : flights) {
                if (dist[flight[0]] == Integer.MAX_VALUE) {
                    continue;
                }
                temp[flight[1]] = Math.min(temp[flight[1]], dist[flight[0]] + flight[2]);
            }
            dist = temp;
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    void main() {
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 3, 100}, {0, 3, 500}};
        IO.println(findCheapestPrice(4, flights, 0, 3, 1));   // 300
        IO.println(findCheapestPrice(4, flights, 0, 3, 0));   // 500
    }
}
