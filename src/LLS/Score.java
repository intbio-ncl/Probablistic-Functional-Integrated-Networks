/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LLS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import GoldStandard.*;
import pfinnetwork.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */

public class Score {
    
    public Map<String,Double> ScoredData(goldstandard LTH, BuildingBioGrid Bio){
    
    //System.out.println("getting LLs....");
    Map<String,Double> dataSetToScore = new HashMap<String, Double>();
    double highscore = 0;
    for(String dataset : Bio.getBio().keySet()){
   //System.out.print("calculating LLS for \t" + dataset + "...");
    Double lls = new SingalLLS().LogScore(LTH, Bio.getBio().get(dataset));
   
    if (lls>0 && !lls.isNaN())
    {
    //System.out.print(lls +"\t is the LLS for\t" + dataset);
    dataSetToScore.put(dataset, lls);
    if (lls> highscore && !lls.isInfinite()){
        highscore = lls;
    }
    }
    else{
           // System.out.println(lls + "\t is the LLS for\t" + dataset);
    
            //System.out.println(dataset + " \t will not be used for integration");
    }
    }
    double finalhighscore = Math.ceil(highscore+1 );
    
    /*System.out.println("scoring done");
    System.out.println("highest score is\t" + highscore);
    System.out.println(" high score to be used\t" + finalhighscore);*/
    for(String datasett : new HashSet<String>(dataSetToScore.keySet()))
    {
        if(dataSetToScore.get(datasett).isInfinite()){
        dataSetToScore.put(datasett,finalhighscore);
        }
    } 
    
    
    String outfile = "LLS.txt";
    try{
    BufferedWriter out = new BufferedWriter(new FileWriter(outfile));
       out.write("The number of scored datasets\t" + dataSetToScore.size() + "\n");
    for(String s : dataSetToScore.keySet()){
        
        out.write(s + "\t" + dataSetToScore.get(s) +"\n");
        }
    out.close();
    }
     catch(IOException ex){
          ex.printStackTrace();
         //System.out.println("Somthins gone horroblliy");
     }
    return dataSetToScore;
    }
    
}


