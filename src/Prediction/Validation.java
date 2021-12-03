/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prediction;
import pfinnetwork.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class Validation {
   
   
    
public List<Double> NetworkValidat(Map<Pair,Double>NetWork,int j)throws IOException {
    Map<Double, Double> AUC=new HashMap<>();
     int count = 0;
     int sumP=0;
    int sumN=0;
    double AUCC=0.0;
    Set<Pair>goldStandard= new HashSet<>();
    Set<String>goldStandardGene= new HashSet<>();
    Set<String>goldStandardDisease= new HashSet<>();
    List<Double>Arrays=new ArrayList<Double>();
    Map<Double, Set<Pair>> scoredAssociations=new HashMap<>();
    List<Double> scores;
     Set<Pair> sample;
      int check;
     double[] scoresToPLot= new double[2000];
    

        


        for(Pair u: NetWork.keySet()){
           goldStandard.add(u);
           goldStandardGene.add(u.getG());
           goldStandardDisease.add(u.getD());
    }
    
    BufferedReader br = null;
       
        //variables
        String from;
        String fromID;
        String to;
        double score;
        
       File bioFile = new File("Prediction.txt");
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            //in.readLine();
            String line;
            if(bioFile.length()!=0){
              while ((line = in.readLine()) != null) {
                String[] colums = line.split("\t");
                from = colums[0];
                to = colums[1];
                score = Double.parseDouble(colums[2]);
                count++;
                Pair P = new Pair(from, to);
        
        
       // System.out.print(P+"\t"+score+"\n"+"iiiiiiii");
        
               /* if (goldStandard.contains(P)) {
                    truePositives++;
                } else {
                    falsePositives++;
                }*/

                if (scoredAssociations.containsKey(score)) {
                    Set<Pair> pairs = scoredAssociations.get(score);
                    pairs.add(P);
                    scoredAssociations.put(score, pairs);
                } else {
                    Set<Pair> pairs = new HashSet<>();
                    pairs.add(P);
                    scoredAssociations.put(score, pairs);
                }
            }}
        
       
          if(!scoredAssociations.isEmpty()){
        Set<Double> allScores = scoredAssociations.keySet();
        List<Double> sortedScores = new ArrayList<>();
        for (double scored : allScores) {
            sortedScores.add(scored);
        }

        Collections.sort(sortedScores);
        scores = sortedScores;
       


   // System.out.print(scores.get(scores.size()));

        double size = scores.get(scores.size()-1  )/ (scoresToPLot.length * 1.0);
   //System.out.print(scores.get(scores.size()-1  ));
        for (int i = 0; i < scoresToPLot.length; i++) {
            scoresToPLot[i] = size * (i +1);
        }

        for (int i = 0; i < scoresToPLot.length; i++) {
            //System.out.println(scoresToPLot[i] + "\t" + i);
        }
          
             
    /**
     * Export the values to allow for plotting in r
     */
     try {
            BufferedWriter bw = null;
            
            bw = new BufferedWriter(new FileWriter(new File(j+"Validation.txt")));
            
        List<Integer>Array=new ArrayList<Integer>();
             List<Double>SpecificityList=new ArrayList<Double>();
             List<Double>Recall=new ArrayList<Double>();
             List<Double>PrecisionList=new ArrayList<Double>();
            
        for (int i = 0; i < scoresToPLot.length; i++) {
            
              int falseNegative = 0;
               int trueNegative = 0;
                int truePositive = 0;
                int falsePositive =0;
              Set<Pair>allPairsLess= getAllPairsLessThanScore(i,scores,scoresToPLot,scoredAssociations);
               for (Pair p : allPairsLess) {
                if (goldStandard.contains(p)) {
                falseNegative++;
                     }
                else //if(!goldStandard.contains(p)&&goldStandardGene.contains(p.getG())&&goldStandardDisease.contains(p.getD())) {
                trueNegative++;      
           // }
        }
        Set<Pair>allPairsGreater= getAllPairsGreaterThanScore(i,scores,scoresToPLot,scoredAssociations);
                for (Pair p : allPairsGreater) {
            if (goldStandard.contains(p)) {
                truePositive++;
            } else //if(!goldStandard.contains(p)&&goldStandardGene.contains(p.getG())&&goldStandardDisease.contains(p.getD())) {
                falsePositive++;      
           // }
        }
       sumN+=(trueNegative + falseNegative);
        sumP+=(truePositive +falsePositive);
        double sensitivity = truePositive/ (truePositive+(falseNegative*1.0));//tp / (dm.getGoldStandard().getTruePositives().size() * 1.0);
        double specificity = trueNegative/ (trueNegative+(falsePositive*1.0));//(negClassed - (dm.getGoldStandard().getTruePositives().size() - tp)
        double Precision= truePositive / (truePositive + (falsePositive*1.0));
        double AUCv = (sensitivity+specificity)/2;
      double Accuracy= (truePositive+trueNegative) /(truePositive+trueNegative+falseNegative+falsePositive);
        double positivecases=truePositive;
        double negativecases=trueNegative;
       double F1 = 2 * (Precision * sensitivity) / (Precision + sensitivity);
                   //SensetivityList.add(sensitivity);
                   //SpecificityList.add(specificity);
                  // PrecisionList.add(Precision);
                  // Recall.add(sensitivity);
                   
            bw.append(falseNegative+ "\t" + trueNegative+ "\t" + truePositive+ "\t" + falsePositive+"\n");
            //System.out.println(sensitivity + "\t" + specificity+"\n");
            double AUC1=(sensitivity+specificity)/2;
            //System.out.print("Average AUC"+AUC1);
            AUCC+=AUC1;
           // bw.append(sensitivity+"\t"+specificity+"\t"+Precision+"\t"+Accuracy+"\n");
            //System.out.print(trueNegative+"\t"+(trueNegative+(falsePositive*1.0))+"\n");
        //Arrays.add( SensetivityList);
        //Arrays.add(SpecificityList);
        //Arrays.add(PrecisionList);
        //Arrays.add(Recall);
        }
     
         
           bw.close();
          // System.out.print("Average AUC"+AUC+"\n");
           System.out.print("Average AUC"+(AUCC/2000)+"\n");
}
        catch(Exception e){
           e.printStackTrace();

                           }  
        
          }
 
  Arrays.add((double)(sumP/10000));
  Arrays.add((double)(sumN/10000));
   //System.out.print("Negative cases"+sumN/10000+"\n");
         
    return Arrays;
    
    }
    /**
     * @return The set of Pairs that are all less than or equal to the score
     * being looked at.
     */
    public Set<Pair> getAllPairsLessThanScore(int p,List<Double>scores,double[] scoresToPLot,Map<Double,Set<Pair>>scoredAssociations) {
        Set<Pair> allPairs = new HashSet<Pair>();
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) <= scoresToPLot[p]) {
                allPairs.addAll(scoredAssociations.get(scores.get(i)));
            }

        }
        //System.out.print(scoresToPLot[p]+"\t"+ "Less"+allPairs);
        return allPairs;
    }
    
    /**
     * @return The set of Pairs that are all less than or equal to the score
     * being looked at.
     */
    public Set<Pair> getAllPairsGreaterThanScore(int p,List<Double>scores,double[] scoresToPLot,Map<Double,Set<Pair>>scoredAssociations) {
        Set<Pair> allPairs = new HashSet<Pair>();
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) > scoresToPLot[p]) {
                allPairs.addAll(scoredAssociations.get(scores.get(i)));
            }

        }
        //System.out.print(scoresToPLot[p]+"\t"+ "Less"+allPairs);
        return allPairs;
    }

    
    
    
    public void writetoFileValidation(Map<Double,Double>AUC) throws IOException {
      
       try {
            BufferedWriter bw = null;
            
            bw = new BufferedWriter(new FileWriter(new File("Validation.txt")));
            for (Double s : AUC.keySet()) {
                
                bw.append(s+ "\t" + AUC.get(s)+"\n");
            }
           bw.close();
        } 
        
        catch(Exception e){
           e.printStackTrace();

                           }  
        }
    }
 

