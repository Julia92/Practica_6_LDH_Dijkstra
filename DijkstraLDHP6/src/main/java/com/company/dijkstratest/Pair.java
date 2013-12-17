package main.java.com.company.dijkstratest;

/**
 * Pair hold vertex and the steps from the vertex to all others
 * @author hzhang
 */
public class Pair implements Comparable<Pair>
{
    private Vertex vertex;
    private int stepNumber;

    public Pair(Vertex v, int s)
    {
        this.vertex = v;
        this.stepNumber = s;
    }

    public Vertex getVertex()
    {
        return vertex;
    }

    public void setStepNumberToInfinity()
    {
        stepNumber = 150;
    }
    
    public int getStepNumber()
    {
        return stepNumber;
    }

    /**
     * Sort Pair object
     * @param other
     * @return 
     */
    public int compareTo(Pair other)
    {
        if(this.getStepNumber() > other.getStepNumber())
        {
            return 1;
        }
        if(this.getStepNumber() == other.getStepNumber())
        {
            if(this.getVertex().getDiameter() > other.getVertex().getDiameter())
                return 1;
            else
                return -1;
        }
        if(this.getStepNumber() < other.getStepNumber())
            return -1;
        return 0;
    }
}
