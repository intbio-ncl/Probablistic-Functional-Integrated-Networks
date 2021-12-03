/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LLS;
import java.util.*;
import java.io.*;
import GoldStandard.*;
import pfinnetwork.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class LTPSCores {
    public Map<String,Double>LTPScore(BuildingBioGrid bio) throws IOException{
    Map<String,Double> dataSetToScore = new HashMap<String, Double>();
    double highscore = 0;
        for(String i: bio.getGS().keySet()){
          Map<String,Set<Pair>>newGS=new HashMap<String,Set<Pair>>();
           newGS.putAll(bio.getGS());
           newGS.remove(i);
           //System.out.print(i+"\n");
           // System.out.print(newGS.keySet()+"\n");
            // System.out.print(newGS.size()+"\n");
            goldstandardparsing gsp = new goldstandardparsing();
                    goldstandard LTP =gsp.ParsingGS(newGS);
                    //System.out.print(bio.getGS().keySet()+"\n");
             //System.out.print(i+"\n");
                Double lls = new SingalLLS().LogScore(LTP, bio.getGS().get(i));
   
                     if (lls>0 && !lls.isNaN())
                           {
    //System.out.print(lls +"\t is the LLS for\t" + dataset);
                      dataSetToScore.put(i, lls);
                       if (lls> highscore && !lls.isInfinite()){
                          highscore = lls;
    }
    }
    else{
            //System.out.println(lls + "\t is the LLS for\t" + i);
    
            //System.out.println(i + " \t will not be used for integration");
    }
    }
    double finalhighscore = Math.ceil(highscore +1 );
    
    /*System.out.println("scoring done");
    System.out.println("highest score is\t" + highscore);
    System.out.println(" high score to be used\t" + finalhighscore);*/
    for(String datasett : new HashSet<String>(dataSetToScore.keySet()))
    {
        if(dataSetToScore.get(datasett).isInfinite()){
        dataSetToScore.put(datasett,finalhighscore);
        }
    } 
    
    
    String outfile = "LTPLLS.txt";
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
        
    
    
    
    
    
        
    
    
   
