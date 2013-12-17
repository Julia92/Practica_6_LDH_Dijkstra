package main.java.com.company.dijkstratest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hzhang
 */

public class Main
{
    private ArrayList<String> adjacencies = new ArrayList<String>();//adjacency pair example:  1-2, 3-1  1,2,3 are vertices names
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();//all vertices
    private ArrayList<Vertex> toBeProcessed = new ArrayList<Vertex>();
    private ArrayList<Vertex> eliminatedList = new ArrayList<Vertex>();

    public static void main(String[] args)
    {
        Main main = new Main();
        String a[] = {"phoneList.txt", "neighbordList.txt"};
        main.go(a);
    }

    public void go(String[] args)
    {
        if(args.length != 2)
        {
            System.out.println("Two files are required, one is the phoneList, and another is adjacency List");
        }

        String phoneFile = args[0];
        String neighborFile = args[1];

        Registration r = new Registration(vertices, adjacencies);
        r.registerVertices(phoneFile);
        r.registerAdjacencies(neighborFile);

        ConnectionMatrix cm = new ConnectionMatrix(vertices, adjacencies);
        cm.buildConnectionMatrix();

        StepsMatrix sm = new StepsMatrix(vertices,toBeProcessed,eliminatedList);
        sm.buildStepsMatrix();



        try{
        while(vertices.size() > 0){
        Community c = new Community(vertices, toBeProcessed,eliminatedList);
        toBeProcessed = c.buildCommunity();

        Collections.sort(toBeProcessed);
        
        ConnectionMatrix cm1= new ConnectionMatrix(toBeProcessed, adjacencies);
        cm1.buildConnectionMatrix();

       
        StepsMatrix sm1 = new StepsMatrix(vertices,toBeProcessed,eliminatedList);
        sm1.buildStepsMatrix();
    }
        }catch(NullPointerException npe)
        {
            System.out.println("Done");
        }
    }

    /**
     * Print the prefix row
     */
    public void displayPrefix()
    {
        System.out.println("OUTPUT:");
        System.out.print("  ");

        for(Vertex vertex : vertices)
        {
            System.out.print("  " + vertex);
        }

        System.out.println("   " + "D" + "   " + "C");
    }
}