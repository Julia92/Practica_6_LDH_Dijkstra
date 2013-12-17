package main.java.com.company.dijkstratest;

import java.util.ArrayList;

/**
 * Find the neighbor-relationship among all subscribers, and then build a
 * connection matrix based on the neighborhood relationship
 *
 * @author hzhang
 */
public class ConnectionMatrix
{
    private final ArrayList<String> adjacencyList;
    private final ArrayList<Vertex> vertices;
    private final ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

    /**
     * Constructor
     *
     * @param subscribersList list of all subscribers
     * @param adjacencyList list of all neighbors
     */
    public ConnectionMatrix(ArrayList<Vertex> vertices, ArrayList<String> adjacencyList)
    {
        this.vertices = vertices;
        this.adjacencyList = adjacencyList;
    }

    public void buildConnectionMatrix()
    {
        registerAdjacenciesToEachVertex();
        fillGrid();
        displayConnectionMatrix();
    }
    
    
    public void registerAdjacenciesToEachVertex()
    {
        for(Vertex v : vertices)
        {
            for(String pair : adjacencyList)
            {
                String[] op_cp = splitOpCp(pair);
                // equals op
                if(v.getName().equals(op_cp[0]))
                {
                    for(Vertex vertex : vertices)
                        if(op_cp[1].equals(vertex.getName()))
                            v.setAdjacencies(vertex);
                }
                if(v.getName().equals(op_cp[1]))//equals to cp
                {
                    for(Vertex vertex : vertices)
                        if(op_cp[0].equals(vertex.getName()))
                            v.setAdjacencies(vertex);
                }
            }
        }
    }

    /**
     * Split the "op-cp" form, and put op and cp into an array
     *
     * @param opCp the "op-cp" String
     * @return an array holds both op and cp
     */
    public String[] splitOpCp(String opCp)
    {
        String[] tmp = opCp.split("-");
        return tmp;
    }

    /**
     * Build the Connection matrix base on the choose subscriber's neighborhood
     * info with other subscribers
     */
    public void fillGrid()
    {
        for(Vertex sRow : vertices)
        {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for(Vertex sColumn : vertices)
            {
                if(sRow.getName().equals(sColumn.getName()))
                    row.add(0);
                else
                {
                    if(sColumn.getAdjacencies().contains(sRow))
                        row.add(1);
                    else
                        row.add(0);
                }
            }
            matrix.add(row);
        }
    }

    public void displayConnectionMatrix()
    {
        System.out.println("\r\nCONNECTION MATRIX:");
        for(ArrayList<Integer> line : matrix)
        {
            for(int m : line)
            {
                System.out.print(m + "  ");
            }
            System.out.println();
        }
    }
}