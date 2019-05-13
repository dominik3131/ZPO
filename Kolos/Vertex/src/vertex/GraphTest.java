package vertex;

import java.util.ArrayList;
import java.util.List;

public class GraphTest {

    List<Vertex> Graph;
    List<List<Vertex>> elements;
    List<Vertex> visited;

    public static void main(String[] args) {
        GraphTest g = new GraphTest();
        g.generateGraph();
        g.findElements();
        g.printElements();
        g.findMax();
        g.findCycles();
    }

    public GraphTest() {
        Graph = new ArrayList<>();
        elements=null;
        
    }

    public void addVertex(Vertex v) {
        Graph.add(v);
    }

    public void addElement(List<Vertex> e) {
        elements.add(e);
    }

    public void findElements() {
        elements = new ArrayList<>();
        List<Vertex> tempGraph = new ArrayList<>(Graph);
        Vertex v;
        while (!tempGraph.isEmpty()) {
            visited = new ArrayList<>();
            v = tempGraph.remove(0);
            visitVertex(v, tempGraph);
            elements.add(visited);
        }
    }

    public void visitVertex(Vertex v, List<Vertex> tempGraph) {
        if (!visited.contains(v)) {
            visited.add(v);
            tempGraph.remove(v);
            for (Vertex vertex : v.neighbours) {
                visitVertex(vertex, tempGraph);
            }
        }
    }
    
    public void findCycles(){
        for(List<Vertex> list:elements){
            if(getEdgesCount(list)==getVertexCount(list)-1){
                System.out.println(list+" has no cycle");
            }
            else{
                System.out.println(list+" has cycle");
            }
        }
    }
    public void printElements() {
        int i = 1;
        for (List<Vertex> element : elements) {
            System.out.println(i + " " + element);
            i++;
        }
    }

    public int getVertexCount(List<Vertex> list) {
        
        return list.size();
    }
    
    public int getEdgesCount(List<Vertex> list){
        int edges=0;
        visited=new ArrayList<>();
        
       
        for(Vertex v:list){
            visited.add(v);
            for(Vertex v2:v.neighbours){
                if(!visited.contains(v2)){
                    edges++;
                }
            }
            
        }
        return edges/2;
    }
    
    public void findMax(){
        if(elements==null){
            findElements();
        }
        int maxEdges=0;
        int maxEdgesIndex=-1;
        int maxVertex=0;
        int maxVertexIndex=-1;
        
        for(List<Vertex> l:elements){
            if(getEdgesCount(l)>maxEdges){
                maxEdges=getEdgesCount(l);
                maxEdgesIndex=elements.indexOf(l)+1;
            }
            if(getVertexCount(l)>maxVertex){
                maxVertex=getVertexCount(l);
                maxVertexIndex=elements.indexOf(l)+1;
            }
        }
        System.out.println(maxVertexIndex+" "+maxEdgesIndex);
        
        
    }
    

    public void generateGraph() {
        for (int i = 0; i < 10; i++) {
            Vertex v = new Vertex("V" + i);
            addVertex(v);
        }
        //V0
        Graph.get(0).addNeighbour(Graph.get(1));
        Graph.get(0).addNeighbour(Graph.get(2));
        //V1
        Graph.get(1).addNeighbour(Graph.get(0));
        Graph.get(1).addNeighbour(Graph.get(2));
        //V2
        Graph.get(2).addNeighbour(Graph.get(0));
        Graph.get(2).addNeighbour(Graph.get(1));

        
        //V4
        Graph.get(4).addNeighbour(Graph.get(6));

        //V5
        Graph.get(5).addNeighbour(Graph.get(6));
        //V6
        Graph.get(6).addNeighbour(Graph.get(4));
        Graph.get(6).addNeighbour(Graph.get(7));
        Graph.get(6).addNeighbour(Graph.get(8));
        Graph.get(6).addNeighbour(Graph.get(5));

        //V7
        Graph.get(7).addNeighbour(Graph.get(6));
        Graph.get(7).addNeighbour(Graph.get(8));

        //v8
        Graph.get(8).addNeighbour(Graph.get(6));
        Graph.get(8).addNeighbour(Graph.get(7));
    }

}
