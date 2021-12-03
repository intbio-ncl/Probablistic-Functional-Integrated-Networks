/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiseaseNetwork;
import java.util.*;
import java.io.*;
import pfinnetwork.*;
/**
 *
 * @author aoeshagaedmalsobhe
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class DiseaseNetworkValidationGN{

  public void NetworkValidat(HashMap<Pair,Integer>GeneticNetwork)throws IOException {
     HashMap<Double, Double> AUC=new HashMap<>();
     int count = 0;
    Set<Pair>goldStandard= new HashSet<>();
     Set<String>goldStandardD= new HashSet<>();
    Set<List>Arrays=new HashSet<List>();
    HashMap<Double, Set<Pair>> scoredAssociations=new HashMap<>();
    List<Double> scores;
     Set<Pair> sample;
      int check;
     double[] scoresToPLot= new double[2000];
    

        


        for(Pair u: GeneticNetwork.keySet()){
           goldStandard.add(u);
           goldStandardD.add(u.getD());
           goldStandardD.add(u.getG());
    }
    
    BufferedReader br = null;
       
        //variables
        String from;
        String fromID;
        String to;
        double score;
        
       File bioFile = new File("DiseaseNetworkOneComponent.txt");
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            in.readLine();
            String line;
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
            }
        
       
  
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
            
            bw = new BufferedWriter(new FileWriter(new File("Validation.txt")));
            
        List<Double>SensetivityList=new ArrayList<Double>();
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
                     } else {
                trueNegative++;      
            }
        }
        Set<Pair>allPairsGreater= getAllPairsGreaterThanScore(i,scores,scoresToPLot,scoredAssociations);
                for (Pair p : allPairsGreater) {
            if (goldStandard.contains(p)) {
                truePositive++;
            } else if(!goldStandard.contains(p)&&goldStandardD.contains(p.getD())&&goldStandardD.contains(p.getG())) {
                falsePositive++;      
            }
        }

        double sensitivity = truePositive/ (truePositive+(falseNegative*1.0));//tp / (dm.getGoldStandard().getTruePositives().size() * 1.0);
        double specificity = trueNegative/ (trueNegative+(falsePositive*1.0));//(negClassed - (dm.getGoldStandard().getTruePositives().size() - tp)
        double Precision= truePositive / (truePositive + (falsePositive*1.0));
        double Accuracy= (truePositive+trueNegative) /(truePositive+trueNegative+falseNegative+falsePositive);
        double positivecases=truePositive;
        double negativecases=trueNegative;
       double F1 = 2 * (Precision * sensitivity) / (Precision + sensitivity);
                   SensetivityList.add(sensitivity);
                   SpecificityList.add(specificity);
                   PrecisionList.add(Precision);
                   Recall.add(sensitivity);
                   
            //System.out.print(falseNegative+ "\t" + trueNegative+ "\t" + truePositive+ "\t" + falsePositive+ "\t"+ "score"+score + "\t" +allPairsLess.size()+"\t"+allPairsGreater.size()+"\n");
            //System.out.println(sensitivity + "\t" + specificity+"\n");
            bw.append(positivecases+"\t"+negativecases+"\t"+falseNegative+"\t"+falsePositive+"\t"+sensitivity+"\t"+specificity+"\t"+Precision+"\t"+Accuracy+"\n");
            //System.out.print(trueNegative+"\t"+(trueNegative+(falsePositive*1.0))+"\n");
        Arrays.add( SensetivityList);
        Arrays.add(SpecificityList);
        Arrays.add(PrecisionList);
        Arrays.add(Recall);
        }
        
         
           bw.close();
        
}
        catch(Exception e){
           e.printStackTrace();

                           }  
        
    
 

         
 
    
    }
    /**
     * @return The set of Pairs that are all less than or equal to the score
     * being looked at.
     */
    public Set<Pair> getAllPairsLessThanScore(int p,List<Double>scores,double[] scoresToPLot,HashMap<Double,Set<Pair>>scoredAssociations) {
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
    public Set<Pair> getAllPairsGreaterThanScore(int p,List<Double>scores,double[] scoresToPLot,HashMap<Double,Set<Pair>>scoredAssociations) {
        Set<Pair> allPairs = new HashSet<Pair>();
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) > scoresToPLot[p]) {
                allPairs.addAll(scoredAssociations.get(scores.get(i)));
            }

        }
        //System.out.print(scoresToPLot[p]+"\t"+ "Less"+allPairs);
        return allPairs;
    }

    
    
    
    public void writetoFileValidation(HashMap<Double,Double>AUC) throws IOException {
      
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
 
           
   

