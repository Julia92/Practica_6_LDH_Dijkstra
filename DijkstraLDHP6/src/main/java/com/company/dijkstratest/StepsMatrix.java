package main.java.com.company.dijkstratest;

import java.math.BigDecimal;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Dijkstra: find the shortest path to vertices
 * @author hzhang
 */
public class StepsMatrix
{
    private ArrayList<Vertex> vertices;
    private ArrayList<Vertex> toBeProcessed;
    private ArrayList<Vertex> eliminatedList;
    private ArrayList<Vertex> beingReturned = new ArrayList<Vertex>();
    private int size;

    /**
     * Constructor
     * @param vertices total vertices
     * @param toBeProcessed community candidates which will be processed
     * @param eliminatedList the eliminated vertices
     */
    public StepsMatrix(ArrayList<Vertex> vertices, ArrayList<Vertex> toBeProcessed, ArrayList<Vertex> eliminatedList)
    {
        this.vertices = vertices;
        this.toBeProcessed = toBeProcessed;
        if(this.toBeProcessed.isEmpty())
        {
            this.toBeProcessed = vertices;
            size = vertices.size();
        }
        this.eliminatedList = eliminatedList;
    }

    /**
     * Build the steps matrix.
     */
    public void buildStepsMatrix()
    {
        System.out.println("\r\n\r\nSTEPS MATRIX:");

        for(Vertex source : toBeProcessed)
        {
            int diameter = 0;
            calculateShortestPath(source);

            System.out.print(source + "  ");

            for(Vertex v : toBeProcessed)
            {
                if(toBeProcessed.size() == size)
                {
                    source.setSteps(v, v.getMinDistance());
                }
                System.out.print(v.getMinDistance() + "  ");
                diameter += v.getMinDistance();
            }
            source.setDiameter(diameter);
            System.out.print("   " + diameter + "   " + getCentrality(diameter));
            reset(toBeProcessed);
            recovery(source, diameter);
        }

        for(Vertex v : beingReturned)
        {
            eliminatedList.remove(v);
            vertices.add(v);
//            System.out.println("Vertex " + v.getName() + " is added back");
            Collections.sort(vertices);
        }
    }

    /**
     * Calculate the shortest path based on DIJKSTRA algorithm.
     * @param source 
     */
    public void calculateShortestPath(Vertex source)
    {
        source.setMinDistance(0);
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);
        while(!vertexQueue.isEmpty())
        {
            Vertex u = vertexQueue.poll();

            for(Vertex v : u.getAdjacencies())
            {
                int distanceThroughU = u.getMinDistance() + v.getWeight();
                if(distanceThroughU < v.getMinDistance())
                {
                    vertexQueue.remove(v);
                    v.setMinDistance(distanceThroughU);
                    v.setPrevious(u);
                    vertexQueue.add(v);
                }
            }
        }
    }

    /**
     * Return the centrality with two digits fractions
     * @param diameter
     * @return 
     */
    public float getCentrality(int diameter)
    {
        BigDecimal b = new BigDecimal((float) (1.0/diameter));
        return b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * Reset the minimum value to 0
     * @param vertices 
     */
    public void reset(ArrayList<Vertex> vertices)
    {
        for(Vertex v : vertices)
        {
            v.setMinDistance(Integer.MAX_VALUE);
        }
    }

    /**
     * put the vertices whose diameter > 10 back to the original vertices
     * @param source
     * @param diameter
     */
    public void recovery(Vertex source, int diameter)
    {
        if(toBeProcessed.size() <= 6 && diameter > 10)
        {
            System.out.print("   Vertex " + source.getName() + "'s diameter > 10,delete\r\n");
            beingReturned.add(source);
        }
        else
        {
            System.out.println();
        }
    }
}