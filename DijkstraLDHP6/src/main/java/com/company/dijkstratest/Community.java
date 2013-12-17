package main.java.com.company.dijkstratest;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Community is to find all the community candidates 
 * @author hzhang
 */
public class Community
{
    private ArrayList<Vertex> vertices;
    private ArrayList<Vertex> toBeProcessed;
    private ArrayList<Vertex> eliminatedList;
    private ArrayList<Pair> steps;

    public Community(ArrayList<Vertex> vertices, ArrayList<Vertex> toBeProcessed, ArrayList<Vertex> eliminatedList)
    {
        this.vertices = vertices;
        this.toBeProcessed = toBeProcessed;
        this.eliminatedList = eliminatedList;
        
        resetSteps();
    }

    /**
     * Build Community
     */
    public ArrayList<Vertex> buildCommunity()
    {
        Vertex highest = getHighestCentralitied();//highest centrality vertex
        toBeProcessed = getCommunityCandidates(highest);
        deleteCandidates(toBeProcessed);

        return toBeProcessed;
    }

    /**
     * Get the vertex which has the highest centrality/smallest diameter
     *
     * @return highest vertex
     */
    public Vertex getHighestCentralitied()
    {
        int minDiameter = 1000;
        Vertex highest = null;

        for(Vertex vertex : vertices)
        {
            if(minDiameter > vertex.getDiameter() && !isIsolated(vertex))
            {
                minDiameter = vertex.getDiameter();
                highest = vertex;
            }
        }
        return highest;
    }

    /**
     * Get the vertices which belong to the community
     *
     * @param highest
     * @return
     */
    public ArrayList<Vertex> getCommunityCandidates(Vertex highest)
    {
        ArrayList<Vertex> candidatesList = new ArrayList<Vertex>();
        steps = highest.getSteps();
        Collections.sort(steps);

        for(int i = 1; i < 6; i++)
        {
            candidatesList.add(steps.get(i).getVertex());
        }
        candidatesList.add(highest);

        return candidatesList;
    }

    /**
     * Delete community candidates from the original vertices
     * @param cCandidates
     */
    public void deleteCandidates(ArrayList<Vertex> cCandidates)
    {
        for(Vertex vertex : cCandidates)
        {
            System.out.println("Vertex " + vertex.getName() + " is deleted from vertices");
            eliminatedList.add(vertex);//add the eliminated vertex to eliminatedList
            vertices.remove(vertex);// remove it from original list
        }
    }

    /**
     * Checking whether a vertex is isolated
     * If it is, it will not be chosen as a highest centrality
     * vertex.
     * @param v
     * @return
     */
    public boolean isIsolated(Vertex v)
    {
        ArrayList<Pair> vSteps = new ArrayList<Pair>();
        vSteps = v.getSteps();
        
        boolean isIsolated = true;

        for(Pair pair : vSteps)
        {
            int stepNumber = pair.getStepNumber();
            if(stepNumber == 1)
            {
                isIsolated = false;
            }
        }

        return isIsolated;
    }

    /**
     * Set the steps to the eliminated vertex to be INFINITY.
     */
    private void resetSteps()
    {
        ArrayList<Pair> resetSteps = new ArrayList<Pair>();
        
        for(Vertex vertex : vertices)
        {
            resetSteps = vertex.getSteps();
            for(Pair pair : resetSteps)
            {
                Vertex pV = pair.getVertex();
                if(eliminatedList.contains(pV))
                {
                    pair.setStepNumberToInfinity();
                }
            }
        }

    }
}