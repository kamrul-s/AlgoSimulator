package client;

import java.util.LinkedList;

public class AnimBip {
    private int V ,G[][],src=0;

    public AnimBip(int v, int[][] g, int src) {
        V = v;
        G = g;
        this.src = src;
    }

    boolean isBipartite() {
        int colorArr[] = new int[V];
        for (int i = 0; i < V; ++i)
            colorArr[i] = -1;
        colorArr[src] = 1;
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(src);
        while (q.size() != 0) {
            int u = q.poll();
            if (G[u][u] == 1)
                return false;
            for (int v = 0; v < V; ++v) {
                if (G[u][v] == 1 && colorArr[v] == -1) {
                    colorArr[v] = 1 - colorArr[u];
                    q.add(v);
                }

                else if (G[u][v] == 1 && colorArr[v] == colorArr[u])
                    return false;
            }
        }
        return true;
    }
}
