package client;

import java.io.FileNotFoundException;

public class AnimMST {
    private  int V;
    int node1,edgep;
    int graph[][];
    int temp[][];
    boolean visited[];
    int i,s,p=0;
    int[] d=new int[100];
    int ret[][];
    int parent[];
    public AnimMST(int node11,int edgep1,int[][] adj1) throws FileNotFoundException {
        this.V = node11;
        parent = new int[V];
        ret=new int[V+1][2];
        this.edgep=edgep1;
        visited=new boolean[edgep1];
        for(i=0;i<node1;i++)
            visited[i]=false;
        graph=new int[node11][node11];
        temp=new int[node11][node11];
        this.graph = adj1;
        System.out.println(graph[1][3]);
        int stringLengthl=0;
        /*System.out.println("freuhionfcbc");
        System.out.println(adj1[0][1]);
        System.out.println(adj[0][1]);*/
    }
    int minKey(int key[], Boolean mstSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        return min_index;
    }
    void printMST() {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            ret[i][0] = parent[i];
            ret[i][1] = graph[i][parent[i]];
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    public int[][] getRet() {
        return ret;
    }

    void primMST() {
        int key[] = new int[V];
        Boolean mstSet[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        key[0] = 0;
        parent[0] = -1;
        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;
            for (int v = 0; v < V; v++)
                if (graph[u][v] != 0 && mstSet[v] == false &&
                        graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }
        printMST();
    }
}