package client;

import java.util.LinkedList;

class AnimMaxFlow {
    private  int V;
    int ret[][]=new int[100][100],j=0,k=0,tec=1,fec=0;
    boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;
        while (queue.size() != 0) {
            int u = queue.poll();
            for (int v = 0; v < V; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return (visited[t] == true);
    }
    int[][] fordFulkerson(int graph[][], int s, int t,int lol) {
        ret[0][0]=0;
        j++;
        ret[1][0]=0;
        j++;
        V=lol;
        int u, v;
        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];
        int parent[] = new int[V];

        int max_flow = 0;
        while (bfs(rGraph, s, t, parent)) {
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                System.out.println("niui"+u);
                System.out.println("niui"+v);
                ret[j][k]=u;
                k++;
                ret[j][k]=v;
                k++;
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }
            max_flow += path_flow;
            ret[j][k]=max_flow;
            System.out.println(ret[j][k]);
            j++;
            ret[0][tec]=k;
            ret[1][fec]=max_flow;
            fec++;
            tec++;
            k=0;
        }
        ret[0][0]=j;
        System.out.println("jngfnbgkjnb"+max_flow);
        return ret;
    }
}