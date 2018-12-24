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
    static HashMap<Integer, Integer> nodos = new HashMap<>();
    static HashMap<Integer, ArrayList<Double>> datosNodos = new HashMap<>();
    class Nodo1 { 
  
        int dest; 
        double peso; 
  
        // Constructor 
        Nodo1(int a, double b) 
        { 
            dest = a; 
            peso = b; 
        } 
    } 
    static class Graph { 
        // numero de nodos del grafo
        int V; 
  
        // nodos adyacentes 
        LinkedList<Nodo1>[] adj; 
  
        // Constructor 
        Graph(int e) 
        { 
            V = e; 
            adj = new LinkedList[V]; 
            for (int o = 0; o < V; o++) 
                adj[o] = new LinkedList<>(); 
        } 
    } 
 
    class Nodo { 
        int vertex; 
        double peso; 
    } 
  
    
    // returns 1 if node0.peso > Nodo1.peso 
    // returns 0 if node0.peso < Nodo1.peso and 
    // returns -1 otherwise 
    class comparator implements Comparator<Nodo> { 
  
        @Override
        public int compare(Nodo node0, Nodo node1) 
        { 
            return (int) (node0.peso - node1.peso); 
        } 
    } 
  
    void addEdge(Graph graph, int src, int dest, double weight){ 
  
        Nodo1 node0 = new Nodo1(dest, weight); 
        Nodo1 node = new Nodo1(src, weight); 
        graph.adj[src].addLast(node0); 
        graph.adj[dest].addLast(node); 
    } 
  
    // Metodo para encontrar mst
    void prims_mst(Graph graph){ 
        Boolean[] mstset = new Boolean[graph.V]; 
        Nodo[] e = new Nodo[graph.V]; 
  
        // aqui guardaremos los nodos cercanos
        int[] parent = new int[graph.V]; 
  
        for (int o = 0; o < graph.V; o++) 
            e[o] = new Nodo(); 
  
        for (int o = 0; o < graph.V; o++) { 
  
            mstset[o] = false; 
  
            e[o].peso = Double.MAX_VALUE; 
            e[o].vertex = o; 
            parent[o] = -1; 
        } 
  
        mstset[0] = true; 
        e[0].peso = 0.0; 
  
        // PriorityQueue 
        PriorityQueue<Nodo> queue = new PriorityQueue<>(graph.V, new comparator()); 
  
        for (int o = 0; o < graph.V; o++) 
            queue.add(e[o]); 
  
        while (!queue.isEmpty()) { 
  
            Nodo node0 = queue.poll(); 
  
            mstset[node0.vertex] = true; 
  
            // recorremos los nodos adyacentes
            for (Nodo1 iterator : graph.adj[node0.vertex]) { 
                if (mstset[iterator.dest] == false) { 
                    //si el peso del nodo es mayor al del inspeccionado, cambiamos el peso 
                    //del nodo por el menor
                    if (e[iterator.dest].peso > iterator.peso) { 
                        queue.remove(e[iterator.dest]); 
                        e[iterator.dest].peso = iterator.peso; 
                        queue.add(e[iterator.dest]); 
                        parent[iterator.dest] = node0.vertex; 
                    } 
                } 
            } 
        } 
        double cable=0;
        //calculamos el cable en base a los nodos cercanos obtenidos
        for (int o = 1; o < graph.V; o++){
            ArrayList<Double> cord0=datosNodos.get(o);
            ArrayList<Double> cord1=datosNodos.get(parent[o]);
            double dN=Math.sqrt(Math.pow((cord0.get(1)-cord1.get(1)), 2)+Math.pow((cord0.get(2)-cord1.get(2)), 2));
            cable+=dN;
        }
        System.out.println(cable); 
    } 
  
    public static void main(String[] args) throws FileNotFoundException, IOException 
    { 
        Graph graph = null; 
        prims e = new prims(); 
        int nod=0;
        
        //Construcción
        File construccion = new File("test.txt");
        BufferedReader reader = null;
        int numCasas;
        reader = new BufferedReader(new FileReader(construccion));
        String linea = null;
        while ((linea = (String) reader.readLine()) != null){
                int numSpaces = linea.replaceAll("[^ ]", "").length();
                if( numSpaces==0){
                    numCasas=Integer.parseInt(linea);
                    graph= new Graph(numCasas);
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
                    coord.add(coordenadas.get(0));
                    coord.add(coordenadas.get(1));
                    coord.add(coordenadas.get(2));
                    datosNodos.put(nod, coord);
                    nodos.put(coord.get(0).intValue(), nod);
                    nod++;
                
                }
                 if( numSpaces==1){
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
                    ArrayList<Double> cord0=datosNodos.get(nodos.get(nodosEnlazados.get(0)));
                    ArrayList<Double> cord1=datosNodos.get(nodos.get(nodosEnlazados.get(1)));
                    double dN=Math.sqrt(Math.pow((cord0.get(1)-cord1.get(1)), 2)+Math.pow((cord0.get(2)-cord1.get(2)), 2));
                    e.addEdge(graph, nodos.get(nodosEnlazados.get(0)), nodos.get(nodosEnlazados.get(1)), dN);
                 }
        }  
        e.prims_mst(graph); 
    } 
} 