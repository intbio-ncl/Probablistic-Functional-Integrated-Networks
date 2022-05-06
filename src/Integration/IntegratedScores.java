/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pfinnetwork.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class IntegratedScores {
    public Map<Pair, Double> doIntegration(final Map<String, Double> lls, final Map<String, Double> ranking, final Comparator<Double> ordering, BuildingBioGrid Bio) {
        List<String> datasetsRankedByScore = new ArrayList<String>(lls.keySet());
        Collections.sort(datasetsRankedByScore, new Comparator<String>() {

            public int compare(String o1, String O2) {
                return ordering.compare(ranking.get(o1), ranking.get(O2));
            }

        });
        //System.out.println("number of genes" + );
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("rankes.txt")));
            out.println("The number of scored dataset is \t\t" + datasetsRankedByScore.size());
            int count = 1;
            for (String i : datasetsRankedByScore) {
                out.println(count + "\t" + i + "\t" +"\t"+ lls.get(i) );
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Map<Pair, Double> scores = new HashMap<Pair, Double>();
        Map<Pair,Map<String,Double>>associationevidence=new HashMap<Pair,Map<String,Double>>();
        for (Pair pair : Bio.getAllPairs()) {
            List<Double> scoresInOrder = new ArrayList<Double>();
           // List<Map<String,Double>>EvidenceScore=new ArrayList<Map<String,Double>>();
            Map<String,Double>evidence=new HashMap<String,Double>();
            for (String ds : datasetsRankedByScore) {

                if (Bio.getBio().get(ds).contains(pair)) {
                    Double score = lls.get(ds);
                   
                    if (score == null) {
                        throw new NullPointerException("Null score for :" + ds);

                    }
                    double dscore=(double) Math.round(score * 100) / 100;
                    evidence.put(ds, dscore);
                    scoresInOrder.add(score);
                    
                }

            }
            double finalScore = calculateScore(scoresInOrder);
            double roundOff = (double) Math.round(finalScore * 100) / 100;
            if (roundOff > 0) {

                scores.put(pair, roundOff);
                associationevidence.put(pair, evidence);
            }
          // System.out.print(associationevidence+"\n");
        }
        final Comparator<Double> ordering1= Collections.<Double>reverseOrder() ;
         List<Pair> PairorderebyScoreValue = new ArrayList<Pair>(scores.keySet());
        Collections.sort(PairorderebyScoreValue, new Comparator<Pair>() {

            public int compare(Pair o1, Pair O2) {
                return ordering1.compare(scores.get(o1), scores.get(O2));
            }

        });
              PrintWriter out2 = null;
        try {
            out2 = new PrintWriter(new BufferedWriter(new FileWriter("rankintegrated.txt")));
            
            for (Pair i :PairorderebyScoreValue) {
               // if(scores.get(i)>=15){
                out2.println(i.getD() + "\t" + i.getG()+"\t" + scores.get(i)+", "+associationevidence.get(i));
               
            }//}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out2 != null) {
                try {
                    out2.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }  
    }
}         return scores;
}
    public double calculateScore(List<Double> scoresInOrder) {

        double LLS = 0.0;

        for (int i = 0; i < scoresInOrder.size(); i++) {
            double raw_score = scoresInOrder.get(i);    
            double denominator = Math.pow(1.1, i);
            double scaled_score = raw_score / denominator;

            LLS += scaled_score;

            //System.out.println(i + "\t" + raw_score + "\t" + denominator + "\t" + scaled_score + "\t" + LLS);
        }

        return LLS;

    }
    
    Map<String,Integer>DatasetRank(final Map<String, Double> lls, final Map<String, Double> ranking, final Comparator<Double> ordering, BuildingBioGrid Bio){
    
         List<String> datasetsRankedByScore = new ArrayList<String>(lls.keySet());
         Map<String,Integer>rank=new HashMap<String,Integer>();
        Collections.sort(datasetsRankedByScore, new Comparator<String>() {
        
        
            public int compare(String o1, String O2) {
                return ordering.compare(ranking.get(o1), ranking.get(O2));
            }
        });
         int count = 1;
            for (String i : datasetsRankedByScore) {
                rank.put(i,count);
                count++;
            }
    
    return rank;
    
    }
}


