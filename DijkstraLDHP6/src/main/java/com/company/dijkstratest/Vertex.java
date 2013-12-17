package main.java.com.company.dijkstratest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Vertex Information
 * @author hzhang
 */
public class Vertex implements Comparable<Vertex>
{
    /**
     * Constructor
     * Initial vertex name and default weight
     *
     * @param name vertex name
     */
    public Vertex(String name){this.name = name;}
    
    /**
     * The weight of each vertex, 
     * now all weight is 1
     */
    private final int weight = 1;
    public int getWeight(){return weight;}
    
    /**
     * The minimum distance
     */
    private int minDistance = Integer.MAX_VALUE;
    public void setMinDistance(int distance){minDistance = distance;}
    public int getMinDistance(){return minDistance;}
    
    /**
     * We don't use it now
     */
    private Vertex previous;
    public void setPrevious(Vertex vertex){ previous = vertex;}
    public Vertex getPrevious(){return previous;}

    private HashSet<Vertex> adjacencies = new HashSet<Vertex>();
    public void setAdjacencies(Vertex... vertexList){ adjacencies.addAll(Arrays.asList(vertexList));}
    public HashSet<Vertex> getAdjacencies(){ return adjacencies;}
    
    /**
     * Steps is an arrayList hold all steps from current vertex to all other vertices
     */
    private ArrayList<Pair> steps = new ArrayList<Pair>();
    public void setSteps(Vertex v, int integer){steps.add(new Pair(v,integer));}
    public ArrayList<Pair> getSteps(){return steps;}
    
    /**
     * Vertex name
     */
    private final String name;
    public String getName(){return name;}
    
    /**
     * Diameter 
     */
    private int diameter = 0;
    public void setDiameter(int diameter){this.diameter = diameter;}
    public int getDiameter(){return this.diameter;}

    @Override
    public String toString(){return name;}

    @Override
    public int compareTo(Vertex other){return (Integer.parseInt(this.getName()) - Integer.parseInt(other.getName()));}
    
    public void removeSteps(Vertex v)
    {
        for(Pair p : steps)
        {
            if(p.getVertex().getName().equals(v.getName()))
            {
                steps.remove(p);
            }
        }
    }
}
