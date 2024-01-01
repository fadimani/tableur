import java.io.Serializable;
import java.util.*;

public class Graph implements Serializable {


    // We use Hashmap to store the edges in the graph
    public Map<Cell, List<Cell>> map = new HashMap<>();

    // This function adds a new vertex to the graph
    public void addVertex(Cell s) {
        map.put(s, new LinkedList<Cell>());
    }

    public Cell getVertex(String coord) {
        for (Cell c : map.keySet()) {
            if (c.getCoord().equals(coord)) return c;
        }
        return null;
    }

    public void removeVertex(Cell s) {
        map.values().stream().forEach(e -> e.remove(s));
        map.remove(s);
    }

    // This function adds the edge
    // between source to destination
    public void addEdge(Cell source, Cell destination) {

        if (!map.containsKey(source))
            addVertex(source);

        if (!map.containsKey(destination))
            addVertex(destination);

        // no duplicate edges
        if (map.get(source).contains(destination)) return;

        map.get(source).add(destination);

    }


    public void removeEdge(Cell source, Cell destination) {
        List<Cell> eV1 = map.get(source);
        if (eV1 != null)
            eV1.remove(destination);
    }

    // This function gives the count of vertices
    public int getVertexCount() {
        return map.keySet().size();
    }

    // This function gives the count of edges
    public void getEdgesCount() {
        int count = 0;
        for (Cell v : map.keySet()) {
            count += map.get(v).size();
        }
        System.out.println("The graph has "
                + count
                + " edges.");
    }

    // This function gives whether
    // a vertex is present or not.
    public void hasVertex(Cell s) {
        if (map.containsKey(s)) {
            System.out.println("The graph contains "
                    + s + " as a vertex.");
        }
        else {
            System.out.println("The graph does not contain "
                    + s + " as a vertex.");
        }
    }

    // This function gives whether an edge is present or not.
    public void hasEdge(Cell s, Cell d)
    {
        if (map.get(s).contains(d)) {
            System.out.println("The graph has an edge between "
                    + s + " and " + d + ".");
        }
        else {
            System.out.println("The graph has no edge between "
                    + s + " and " + d + ".");
        }
    }

    public boolean is_leafNode(Cell node){
        return map.get(node).size() == 0;
    }

    List<Cell> getAdjVertices(Cell s) {
        return map.get(s);
    }

    List<Cell> getAllVerticies() {
        return new ArrayList<Cell>(map.keySet());
    }


    Set<Cell> breadthFirstTraversal(Cell root) {
        Set<Cell> visited = new LinkedHashSet<Cell>();
        Queue<Cell> queue = new LinkedList<Cell>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            Cell vertex = queue.poll();
            for (Cell v : this.getAdjVertices(vertex)) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }

        }
        return visited;
    }



    List<Cell> fathersOf(Cell node){
        List<Cell> vertices = new ArrayList<Cell>();
        for (Cell v : map.keySet()) {
            for (Cell w : map.get(v)) {
                if (w.equals(node)){
                    vertices.add(v);
                }
            }
        }
        if (vertices.size() == 0) return null;
        return vertices;
    }


    public Graph(Graph other) {
        this.map = other.map;
    }

    public Graph() {
    }

    // Prints the adjancency list of each vertex.
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (Cell v : map.keySet()) {
            builder.append(v.toString() + ": ");
            for (Cell w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }

        return (builder.toString());
    }


}
