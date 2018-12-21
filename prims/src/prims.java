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
    class node1 { 
  
        // Almacena el vértice de destino en la lista de adyacencia.
        int destino; 
  
        // Almacena el peso de un vértice en la lista de adyacencia.
        int peso; 
  
        // Constructor 
        node1(int a, int b) 
        { 
            destino = a; 
            peso = b; 
        } 
    } 
    static class Graph { 
  
        // Numero de vertices en el grafo 
        int vertices; 
  
        // Lista de nodos adyacentes de un vértice dado
        LinkedList<node1>[] adyacentes; 
  
        // Constructor 
        Graph(int e) 
        { 
            vertices = e; 
            adyacentes = new LinkedList[vertices]; 
            for (int o = 0; o < vertices; o++) 
                adyacentes[o] = new LinkedList<>(); 
        } 
    } 
  
    // Almacena un vértice y su valor clave correspondiente
    class node { 
        int vertice; 
        int key; 
    } 
    // Comparator class created for PriorityQueue 
    // returns 1 if node0.key > node1.key 
    // returns 0 if node0.key < node1.key and 
    // returns -1 otherwise 
    class comparator implements Comparator<node> { 
        @Override
        public int compare(node node0, node node1) 
        { 
            return node0.key - node1.key; 
        } 
    } 
  
    // method to add an edge 
    // between two vertices 
    void addEdge(Graph graph, int nodo1, int nodo2, int pesoEntreNodos) 
    { 
        node1 node0 = new node1(nodo2, pesoEntreNodos); 
        node1 node = new node1(nodo1, pesoEntreNodos); 
        graph.adyacentes[nodo1].addLast(node0); 
        graph.adyacentes[nodo2].addLast(node); 
    } 
  
    // method used to find the mst 
    void prims_mst(Graph graph) 
    { 
        // Whether a vertex is in PriorityQueue or not 
        Boolean[] mstset = new Boolean[graph.vertices]; 
        node[] e = new node[graph.vertices]; 
  
        // Stores the parents of a vertex 
        int[] parent = new int[graph.vertices]; 
  
        for (int o = 0; o < graph.vertices; o++) 
            e[o] = new node(); 
  
        for (int o = 0; o < graph.vertices; o++) { 
  
            // Initialize to false 
            mstset[o] = false; 
  
            // Initialize key values to infinity 
            e[o].key = Integer.MAX_VALUE; 
            e[o].vertice = o; 
            parent[o] = -1; 
        } 
  
        // Include the source vertex in mstset 
        mstset[0] = true; 
  
        // Set key value to 0 
        // so that it is extracted first 
        // out of PriorityQueue 
        e[0].key = 0; 
  
        // PriorityQueue 
        PriorityQueue<node> queue = new PriorityQueue<>(graph.vertices, new comparator()); 
  
        for (int o = 0; o < graph.vertices; o++) 
            queue.add(e[o]); 
  
        // Loops until the PriorityQueue is not empty 
        while (!queue.isEmpty()) { 
  
            // Extracts a node with min key value 
            node node0 = queue.poll(); 
  
            // Include that node into mstset 
            mstset[node0.vertice] = true; 
  
            // For all adjacent vertex of the extracted vertex V 
            for (node1 iterator : graph.adyacentes[node0.vertice]) { 
  
                // If V is in PriorityQueue 
                if (mstset[iterator.destino] == false) { 
                    // If the key value of the adjacent vertex is 
                    // more than the extracted key 
                    // update the key value of adjacent vertex 
                    // to update first remove and add the updated vertex 
                    if (e[iterator.destino].key > iterator.peso) { 
                        queue.remove(e[iterator.destino]); 
                        e[iterator.destino].key = iterator.peso; 
                        queue.add(e[iterator.destino]); 
                        parent[iterator.destino] = node0.vertice; 
                    } 
                } 
            } 
        } 
  
        // Prints the vertex pair of mst 
        for (int o = 1; o < graph.vertices; o++) 
            System.out.println(parent[o] + " "
                               + "-"
                               + " " + o); 
    } 
  
    public static void main(String[] args) throws FileNotFoundException, IOException 
    { 
        int V = 9; 
  
        Graph graph = null; 
        prims e = new prims(); 
        
        HashMap<Integer, ArrayList<Double>> nodos = new HashMap<>();
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
                    //numCasas=Integer.parseInt(linea);
                    numCasas=9;
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
                    nodos.put(coordenadas.get(0).intValue(), coord);
                    //System.out.println(nodos.get(coordenadas.get(0).intValue()));
                    coordNodos.add(coordenadas);
                    //e.addEdge(graph, Integer.parseInt(valores.get(0)),Integer.parseInt(valores.get(1)), Integer.parseInt(valores.get(2)));
                
                }
                 if( numSpaces==1){
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
                    System.out.println(coordenadas.toString());
                    ArrayList<Double> cord0=nodos.get(coordenadas.get(0).intValue());
                    ArrayList<Double> cord1=nodos.get(coordenadas.get(1).intValue());
                    double dN=Math.sqrt(Math.pow((cord0.get(0)-cord1.get(0)), 2)+Math.pow((cord0.get(1)-cord1.get(1)), 2));
                    
                 }
                
               
        }    
        //crea arista entre 2 vertices (grafo, identificador nodo1, identificador nodo2, peso entre nodos)
        e.addEdge(graph, 0, 7, 8); 
        e.addEdge(graph, 1, 2, 8); 
        e.addEdge(graph, 1, 7, 11); 
        e.addEdge(graph, 2, 3, 7); 
        e.addEdge(graph, 2, 8, 2); 
        e.addEdge(graph, 2, 5, 4); 
        e.addEdge(graph, 3, 4, 9); 
        e.addEdge(graph, 3, 5, 14); 
        e.addEdge(graph, 4, 5, 10); 
        e.addEdge(graph, 5, 6, 2); 
        e.addEdge(graph, 6, 7, 1); 
        e.addEdge(graph, 6, 8, 6); 
        e.addEdge(graph, 7, 8, 7); 
        // Method invoked 
        e.prims_mst(graph); 
    } 
} 