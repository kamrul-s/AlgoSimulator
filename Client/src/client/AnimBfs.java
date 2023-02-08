package client;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class AnimBfs {
    int node1,edgep;
    int adj[][];
    boolean visited[];
    int i,s,p=0;
    int[] d=new int[100];

    public AnimBfs(int node11, int edgep1, int[][] adj1) throws FileNotFoundException {
        this.node1 = node11;
        this.edgep=edgep1;
        //visited = new boolean[edgep1];
        visited = new boolean[node1];
        for(i=0;i<this.node1;i++)
            visited[i]=false;
        adj = new int[node11][node11];
        this.adj = adj1;
        int stringLength,l=0;
    }

    public int[] bfskoro(){
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        visited[0]=true;
        queue.add(0);
        while (queue.size()  != 0) {
            s = queue.poll(); ;
            d[p++]=s;
            for(int i = 0; i < this.node1; i++) {
                if((!visited[i]) && adj[s][i] == 1) {
                    System.out.println(i);
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
        return d;
    }
}
