package main.java.com.company.dijkstratest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Read phone information and phones-neighborhood relationship info 
 * from two files
 * @author hzhang
 */
public class Registration
{
    private final ArrayList<String> adjacencies;
    private final ArrayList<Vertex> vertices;

    /**
     * Constructor
     *
     * @param vertices list of all subscribers
     * @param adjacencies list of connected phone pairs
     */
    public Registration(ArrayList<Vertex> vertices, ArrayList<String> adjacencies)
    {
        this.vertices = vertices;
        this.adjacencies = adjacencies;
    }

    /**
     * Register subscribers with its phone numbers
     */
    public void registerVertices(String phonesFile)
    {
        try
        {
            FileInputStream fstream = new FileInputStream(phonesFile);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String string;

            while((string = br.readLine()) != null)
            {
                vertices.add(new Vertex(string));
            }
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(phonesFile + " is not found..." + fnfe.getMessage());
        }
        catch(IOException ioe)
        {
            System.err.println(phonesFile + " io exception occur..." + ioe.getMessage());
        }
    }

    /**
     * Initializing all the phones who call/sms each other. Those "op-cp" pairs
     * satisfy with the condition: F = 40(SMS/Month) && 240(sec/Month)
     */
    public void registerAdjacencies(String adjacenciesFile)
    {
        try
        {
            FileInputStream fstream = new FileInputStream(adjacenciesFile);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String neighborInfo;

            while((neighborInfo = br.readLine()) != null)
            {
                adjacencies.add(neighborInfo);
            }
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(adjacenciesFile + " is not found..." + fnfe.getMessage());
        }
        catch(IOException ioe)
        {
            System.err.println(adjacenciesFile + " io exception occur..." + ioe.getMessage());
        }
    }
}