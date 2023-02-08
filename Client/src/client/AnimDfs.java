package client;

import java.io.FileNotFoundException;

public class AnimDfs {
    int node1, edgep;
    int adj[][];
    boolean visited[];
    int i, s = 0, p = 0;
    int[] d = new int[100];

    public AnimDfs(int node11, int edgep1, int[][] adj1) throws FileNotFoundException {
        this.node1 = node11;
        this.edgep = edgep1;
        //visited = new boolean[edgep1];
        visited = new boolean[node1];
        for (i = 0; i < this.node1; i++)
            visited[i] = false;
        adj = new int[node11][node11];
        this.adj = adj1;
        int stringLength, l = 0;
    }

    public void dfskoro(int[][] rGraph, int s) {
        visited[s] = true;
        d[p++] = s;
        for (int i = 0; i < rGraph.length; i++) {
            if (rGraph[s][i] > 0 && !visited[i]) {
                dfskoro(rGraph, i);
            }
        }
    }
    public int[] getd()
    {
        return d;
    }
}