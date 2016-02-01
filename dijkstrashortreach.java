import java.io.*;
import java.util.*;

public class Main {
    
   @SuppressWarnings("unchecked")
   private static void updQ(PriorityQueue<Map.Entry<Integer,Integer>> Q, int V, int w) { // O(lgn) thoeretically, but here for will do o(n)
   // to update the priority, we need to remove and insert again
        Map.Entry<Integer,Integer> a = null;
        for(Object x: Q.toArray()) {
           a = (Map.Entry<Integer,Integer>) x;
           if(a.getKey() == V) break;
        }
        Q.remove(a);
        Q.add(new AbstractMap.SimpleEntry<Integer,Integer>(V, w));
    }
    
    @SuppressWarnings("unchecked")
    private static void djikstra(Map<Integer, Map<Integer,Integer>> adj, int start ,int V, int E) {
        // the set of finished items
        Map<Integer, Integer> set = new HashMap<>(); // S
        // predecessor
        Map<Integer, Integer> predecessor = new HashMap<>();
        // the queue
        PriorityQueue<Map.Entry<Integer,Integer>> Q = 
            new PriorityQueue<>(V, (Map.Entry<Integer,Integer> x, Map.Entry<Integer,Integer> y) -> x.getValue() - y.getValue());
        for(int c : adj.keySet()){ // for all V
            Q.add(new AbstractMap.SimpleEntry<Integer,Integer>(c, Integer.MAX_VALUE));
        }
        // initialize queue
        updQ(Q, start, 0);
        while(Q.size() > 0){
            Map.Entry<Integer,Integer> u = Q.poll();
            if( ! (  (u.getValue() < Integer.MAX_VALUE) &&  (u.getValue() >= 0) ) ) break; // after getting one unreachable node, no need to go further
            set.put(u.getKey(), u.getValue());
            int du = u.getValue(); // d[u]
            // relaxing
            Map<Integer, Integer> qVal = new HashMap<>();
            for(Object x: Q.toArray()) {
                Map.Entry<Integer,Integer> a = (Map.Entry<Integer,Integer>) x;
                qVal.put(a.getKey(), a.getValue());
            }
            // actual relaxation step
            for(Map.Entry<Integer,Integer> v: adj.get(u.getKey()).entrySet()) {
                int newW = v.getValue() + du; 
                if(qVal.containsKey(v.getKey())) {
                    if(qVal.get(v.getKey()) > newW) {
                        updQ(Q,v.getKey(), newW);
                        predecessor.put(v.getKey(), u.getKey());
                    }
                }
            }
        }
        for(int i = 1; i <= V; i++) {
            if(i == start) continue;
            if(set.containsKey(i)) { 
                System.out.format("%s ",set.get(i)); 
            } else{
                System.out.format("-1 ",set.get(i)); 
            }
        }
        System.out.println();
        //System.out.println(); 
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int noOfTestCases = in.nextInt();
        while(noOfTestCases != 0) {
            int N = in.nextInt(); // no of v
            int M = in.nextInt(); //no of E
            Map<Integer, Map<Integer,Integer>> adj = new HashMap<Integer, Map<Integer,Integer>>();
            for(int i=0; i<M; i++){
            /*
            The next M lines each consist of three space-separated integers x y r, where x and y denote the two nodes between                 which the undirected edge exists, r denotes the length of edge between these corresponding nodes.
            */
                int u = in.nextInt();                    
                int v = in.nextInt();
                int w = in.nextInt();
                if (!adj.containsKey(u)) {
                    adj.put(u, new HashMap<Integer,Integer>() );
                    adj.get(u).put(v,w); 
                } else {
                    // in case of duplicates insert only the min
                    boolean inuv = false;
                    if(adj.get(u).containsKey(v)) {
                        if(adj.get(u).get(v) > w)  {                          
                            inuv = true; 
                        }
                    } else {
                       adj.get(u).put(v,w);
                    } 
                    if(inuv) {
                        adj.get(u).put(v,w);
                    }
                }
                if (!adj.containsKey(v)) {
                    adj.put(v, new HashMap<Integer,Integer>());
                    adj.get(v).put(u, w);   
                } else {
                    boolean invu = false;
                    // in case of duplicates insert only the min
                    if(adj.get(v).containsKey(u)) {
                        if(adj.get(v).get(u) > w)  {                          
                            invu = true; 
                        }
                    } else {
                       adj.get(v).put(u, w);
                    } 
                    if(invu) {
                        adj.get(v).put(u, w);
                    }
                }
            }
            int s = in.nextInt();
            /*
            debugging, to print the graph
            for (Map.Entry<Integer, Set<Pair<Integer,Integer>>> entry : adj.entrySet())
            {
                System.out.println(entry.getKey() + "/" + entry.getValue());
            }
            */
            djikstra(adj, s, N, M);
            noOfTestCases--;
        }
    }
}
