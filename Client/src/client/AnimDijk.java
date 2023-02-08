package client;

public class AnimDijk {

    private int V;
    int ret[][]=new int[100][100],p=0,j=1,k=0;
    int minDistance(int dist[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    void printSolution(int dist[], int n) {
        System.out.println("Vertex   Distance from Source");
        for (int i = 1; i < V; i++)
            ret[i][k]=dist[i];
    }

    int[] dijkstra(int graph[][], int src, int ver) {
        V=ver;
        int dist[] = new int[V]; // The output array. dist[i] will hold

        Boolean sptSet[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }


        dist[src] = 0;
        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            System.out.println(u);
            /*ret[j][k]=u;
            k++;*/
            for (int v = 0; v < V; v++)

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v])
                {

                    System.out.println(v);
                    dist[v] = dist[u] + graph[u][v];
                    // ret[j][k]=v;
                    //k++;
                }
                /*ret[0][p]=k;
                p++;
                j++;*/
            // k=0;
        }
        // print the constructed distance array
        //printSolution(dist, V);
        return dist;
    }
}