/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LLS;
import java.io.*;
import pfinnetwork.*;
import GoldStandard.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class DValue {

    public void run(goldstandard gs) throws IOException{
    
        String file="rankintegrated.txt";
         int points=10;
    
    //required values
         int possibleRels = gs.getDiseases().size() * gs.getGenes().size();
         int possibleFalseNeg =possibleRels - gs.getPositive().size();
        int truePositive;
     //ranges for the points
         double[] thresholds;
    //results
         int[][] matchesTruePositives;
         double highestScore=0.0;
         double lowestScore=0.0;
    BufferedReader br = new BufferedReader(new FileReader(file));
       
        
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t");
                if (count == 0) {
                    highestScore = Double.parseDouble(split[2]);
                }
                lowestScore = Double.parseDouble(split[2]);
                count++;
            }
         
        System.out.print(lowestScore+"\t"+ highestScore+"\n");

        double[] ranges = new double[points];
        for (int i = 0; i < ranges.length - 1; i++) {
            ranges[i] = (((highestScore - lowestScore) / (points - 1.0)) * (i * 1.0)) + lowestScore;
        }
        ranges[points-1]=highestScore;
        System.out.println(highestScore + "\t" + lowestScore);
        thresholds = ranges;
          for(int i =0; i<thresholds.length-1;i++)
              System.out.print(thresholds[i]+"\n");
          matchesTruePositives = new int[thresholds.length][2];
         BufferedReader br1 = new BufferedReader(new FileReader(file));
        
            String line1;
            while ((line1 = br1.readLine()) != null) {
                String[] split1 = line1.split("\t");
                Pair p = new Pair(split1[0], split1[1]);
                double pairScore = Double.parseDouble(split1[2]);
                int index = 0;
        for (int i = 0; i < thresholds.length; i++) {
            if (i == thresholds.length - 1) {
                if (pairScore >thresholds[i] && pairScore <= highestScore) {
                    index = i;
                }
            } else {
                if (pairScore >thresholds[i] && pairScore <= thresholds[i + 1]) {
                    index = i;
                }
            }
        }

        int checked = matchesTruePositives[index][0];
        int tp = matchesTruePositives[index][1];

        if (gs.getPositive().contains(p)) {
            //it is a true positive
            tp++;
        }
        checked++;

        matchesTruePositives[index][0] = checked;
        matchesTruePositives[index][1] = tp;

    }  
          for (int i = 0; i < matchesTruePositives.length; i++) {
  
            // Loop through all elements of current row 
            for (int j = 0; j < matchesTruePositives[i].length; j++) {
                System.out.print(matchesTruePositives[i][j]+"\n"); 
    } 
}



       int[][] matchesTruePositivesCUM = new int[matchesTruePositives.length][2];
        for (int i = 0; i < matchesTruePositives.length; i++) {
            for (int y = 0; y < matchesTruePositives.length; y++) {
                if (y <= i) {
                    int mappings = matchesTruePositivesCUM[y][0];
                    int tps = matchesTruePositivesCUM[y][1];
                    mappings += matchesTruePositives[i][0];
                    tps += matchesTruePositives[i][1];
                    matchesTruePositivesCUM[y][0] = mappings;
                    matchesTruePositivesCUM[y][1] = tps;
                }
            }
        }
        
        //output to file
        BufferedWriter bw = null;
        String opfile = "ou.txt";
        try {
            bw = new BufferedWriter(new FileWriter(opfile));
        } catch (IOException ex) {
            
        }

        int allAssociations = matchesTruePositivesCUM[0][0];
        System.out.println(allAssociations);
        System.out.println(gs.getPositive().size());

        for (int i = 0; i < matchesTruePositives.length; i++) {

            int negClassed = allAssociations -matchesTruePositivesCUM[i][0];
            int allNegative = allAssociations - gs.getPositive().size();
            int tp= matchesTruePositivesCUM[i][1];
            
            double sensitivity = tp/(gs.getPositive().size()*1.0);       
            //look here again!
            
            double specificity = (negClassed-(gs.getPositive().size()-tp))/(allNegative*1.0);                
                    
           //(allAssociations-(gs.getPositive().size()-allAssociations-matchesTruePositivesCUM[i][1]))/(allAssociations-(gs.getPositive().size()*1.0));

            try {
                bw.append(matchesTruePositivesCUM[i][0] + "\t" + matchesTruePositivesCUM[i][1] + "\t" + thresholds[i] + "\t"+sensitivity+"\t"+specificity+"\n");
            } catch (IOException ex) {
                
            }

        }
        try {
            bw.close();
        } catch (IOException ex) {
            
        }

        

    }
           
    }

         

   
       
    