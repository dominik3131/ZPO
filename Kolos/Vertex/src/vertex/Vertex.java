
package vertex;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Vertex {
    
    String name;
    List<Vertex> neighbours;
    
    public Vertex(String n){
        name=n;
        neighbours = new LinkedList<>();
    }
  
    public void addNeighbour(Vertex v){
        neighbours.add(v);
        v.addNeighbour2(this);
    }
    public void addNeighbour2(Vertex v){
        neighbours.add(v);
    }
    
    public void removeNeighbour(Vertex v){
        neighbours.remove(v);
        v.removeNeighbour(this);
    }
    
    public boolean isNeighbour(Vertex v){
        return neighbours.contains(v);
    }
    
    public String getName(){
        return name;
    }
    
    
    @Override
    public String toString(){
        return "[ "+name+" ]";
    }
  

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex other = (Vertex) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
}
