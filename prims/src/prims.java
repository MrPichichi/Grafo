// Java program for Prim's MST for 
// adjacency list representation of graph 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList; 
import java.util.PriorityQueue; 
import java.util.Comparator; 
import java.util.HashMap;
  
public class prims {
    static HashMap<Integer, ArrayList<Double>> nodos = new HashMap<>();
    static HashMap<Integer, Double> pesos = new HashMap<>();
    class node1 { 
  
        // Stores destination vertex in adjacency list 
        int dest; 
  
        // Stores weight of a vertex in adjacency list 
        double weight; 
  
        // Constructor 
        node1(int a, Double b) 
        { 
            dest = a; 
            weight = b; 
        } 
    } 
    static class Graph { 
  
        // Number of vertices in the graph 
        int V; 
        File construccion = new File("test.txt");
        BufferedReader reader = null;
        
        
        
        // List of adjacent nodes of a given vertex 
        LinkedList<node1>[] adj; 
  
        // Constructor 
        Graph(int e) 
        { 
            V = e; 
            adj = new LinkedList[V]; 
            for (int o = 0; o < V; o++) 
                adj[o] = new LinkedList<>(); 
        } 
    } 
  
    // class to represent a node in PriorityQueue 
    // Stores a vertex and its corresponding 
    // key value 
    class node { 
        int vertex; 
        double key; 
    } 
  
    // Comparator class created for PriorityQueue 
    // returns 1 if node0.key > node1.key 
    // returns 0 if node0.key < node1.key and 
    // returns -1 otherwise 
    class comparator implements Comparator<node> { 
  
        @Override
        public int compare(node node0, node node1) 
        { 
            return  (int) (node0.key - node1.key); 
        } 
    } 
  
    // method to add an edge 
    // between two vertices 
    void addEdge(Graph graph, int src, int dest, Double weight) 
    { 
        node1 node0 = new node1(dest, weight);
        nodos.get(src).get(2);
        //System.out.println("Nodo src: "+src+" "+nodos.get(src).get(2));
        //System.out.println("Nodo dest: "+dest+" "+nodos.get(dest).get(2));
        node1 node = new node1(src, weight); 
        //System.out.println(graph.adj.length);
        graph.adj[nodos.get(src).get(2).intValue()].addLast(node0); 
        graph.adj[nodos.get(dest).get(2).intValue()].addLast(node); 
    } 
    
    // method used to find the mst 
    void prims_mst(Graph graph) 
    { 
  
        // Whether a vertex is in PriorityQueue or not 
        Boolean[] mstset = new Boolean[graph.V]; 
        node[] e = new node[graph.V]; 
  
        // Stores the parents of a vertex 
        int[] parent = new int[graph.V]; 
  
        for (int o = 0; o < graph.V; o++) 
            e[o] = new node(); 
  
        for (int o = 0; o < graph.V; o++) { 
  
            // Initialize to false 
            mstset[o] = false; 
  
            // Initialize key values to infinity 
            e[o].key = Integer.MAX_VALUE; 
            e[o].vertex = o; 
            parent[o] = -1; 
        } 
  
        // Include the source vertex in mstset 
        mstset[0] = true; 
  
        // Set key value to 0 
        // so that it is extracted first 
        // out of PriorityQueue 
        e[0].key = 0; 
  
        // PriorityQueue 
        PriorityQueue<node> queue = new PriorityQueue<>(graph.V, new comparator()); 
  
        for (int o = 0; o < graph.V; o++) 
            queue.add(e[o]); 
  
        // Loops until the PriorityQueue is not empty 
        while (!queue.isEmpty()) { 
  
            // Extracts a node with min key value 
            node node0 = queue.poll(); 
  
            // Include that node into mstset 
            mstset[node0.vertex] = true; 
  
            // For all adjacent vertex of the extracted vertex V 
            for (node1 iterator : graph.adj[node0.vertex]) { 
  
                // If V is in PriorityQueue 
                if (mstset[nodos.get(iterator.dest).get(2).intValue()] == false) { 
                    // If the key value of the adjacent vertex is 
                    // more than the extracted key 
                    // update the key value of adjacent vertex 
                    // to update first remove and add the updated vertex 
                    if (e[nodos.get(iterator.dest).get(2).intValue()].key > iterator.weight) { 
                        queue.remove(e[nodos.get(iterator.dest).get(2).intValue()]); 
                        e[nodos.get(iterator.dest).get(2).intValue()].key = iterator.weight; 
                        queue.add(e[nodos.get(iterator.dest).get(2).intValue()]); 
                        parent[nodos.get(iterator.dest).get(2).intValue()] = node0.vertex; 
                    } 
                } 
            } 
        } 
  
        // Prints the vertex pair of mst 
        for (int o = 0; o < graph.V; o++) 
            System.out.println(parent[o] + " "
                               + "-"
                               + " " + o); 
    } 
  
    public static void main(String[] args) throws FileNotFoundException, IOException 
    { 
        //int V = 9; 
  
        Graph graph = null; 
        prims e = new prims(); 
        int vert=0;
        
        
        //Construcción
        File construccion = new File("test.txt");
        BufferedReader reader = null;
        int numCasas;
        reader = new BufferedReader(new FileReader(construccion));
        String linea = null;
        ArrayList<ArrayList<Double>> coordNodos=new ArrayList<>();
        ArrayList<ArrayList<Double>> adyac=new ArrayList<>();
        while ((linea = (String) reader.readLine()) != null){
               // System.out.println(linea);
                int numSpaces = linea.replaceAll("[^ ]", "").length();
                if( numSpaces==0){
                    numCasas=Integer.parseInt(linea);
                    graph= new Graph(numCasas);
                    System.out.println("\n Numro de casas: "+numCasas);
                }
                if( numSpaces==2){
                    ArrayList<Double> coordenadas=new ArrayList<>();
                    String val="";
                    for(int n=0; n <linea.length (); n++) { 
                        char c = linea.charAt (n); 
                        String s=Character.toString(c);
                        if(!" ".equals(s)){
                            val+=s;
                        }
                        if(" ".equals(s)){
                            coordenadas.add(Double.parseDouble(val));
                            val="";
                        }
                        if(n ==linea.length()-1){
                            coordenadas.add(Double.parseDouble(val));
                        }
                    }   
                    ArrayList<Double> coord= new ArrayList<>();
                    coord.add(coordenadas.get(1));
                    coord.add(coordenadas.get(2));
                    coord.add((double) vert);
                    vert++;
                    nodos.put(coordenadas.get(0).intValue(), coord);
                    
                    coordNodos.add(coordenadas);
                    //e.addEdge(graph, Integer.parseInt(valores.get(0)),Integer.parseInt(valores.get(1)), Integer.parseInt(valores.get(2)));
                
                }
                 if( numSpaces==1){
                    //System.out.println("Nodos: "+nodos.size());
                    ArrayList<Integer> nodosEnlazados=new ArrayList<>();
                    String val="";
                    for(int n=0; n <linea.length (); n++) { 
                        char c = linea.charAt (n); 
                        String s=Character.toString(c);
                        if(!" ".equals(s)){
                            val+=s;
                        }
                        if(" ".equals(s)){
                            nodosEnlazados.add(Integer.parseInt(val));
                            val="";
                        }
                        if(n ==linea.length()-1){
                            nodosEnlazados.add(Integer.parseInt(val));
                        }
                    }
                    //System.out.println(nodosEnlazados.toString());
                    ArrayList<Double> cord0=nodos.get(nodosEnlazados.get(0));
                    ArrayList<Double> cord1=nodos.get(nodosEnlazados.get(1));
                    ///System.out.println(cord0+"\n"+cord1);
                    double dN=Math.sqrt(Math.pow((cord0.get(0)-cord1.get(0)), 2)+Math.pow((cord0.get(1)-cord1.get(1)), 2));
                   
                    e.addEdge(graph, nodosEnlazados.get(0), nodosEnlazados.get(1), dN);
                    //System.out.println("Distancia: "+dN);
                 }
        }  
//        e.addEdge(graph, 0, 1, 4.0); 
//        e.addEdge(graph, 0, 7, 8.0); 
//        e.addEdge(graph, 1, 2, 8.0); 
//        e.addEdge(graph, 1, 7, 11.0); 
//        e.addEdge(graph, 2, 3, 7.0); 
//        e.addEdge(graph, 2, 8, 2.0); 
//        e.addEdge(graph, 2, 5, 4.0); 
//        e.addEdge(graph, 3, 4, 9.0); 
//        e.addEdge(graph, 3, 5, 14.0); 
//        e.addEdge(graph, 4, 5, 10.0); 
//        e.addEdge(graph, 5, 6, 2.0); 
//        e.addEdge(graph, 6, 7, 1.0); 
//        e.addEdge(graph, 6, 8, 6.0); 
//        e.addEdge(graph, 7, 8, 7.0); 
  
        // Method invoked 
        e.prims_mst(graph); 
    } 
} 