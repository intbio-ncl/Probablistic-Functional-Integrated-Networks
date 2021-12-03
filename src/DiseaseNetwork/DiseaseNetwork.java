/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiseaseNetwork;
import java.io.*;
import java.util.*;
import pfinnetwork.*;

/**
 *
 * @author aoesha gaed m alsobhe
 */
public class DiseaseNetwork {
    public HashMap<Pair,Set<Pair>> DiseaseNet(HashMap<Pair,Double>Networki)throws IOException{
          
          Set<String>diseases=new HashSet<String>();
         HashMap<String,Set<String>>CommonGenes=new HashMap<String,Set<String>>();
          for(Pair d : Networki.keySet()){
              diseases.add(d.getD());
          }
          
          System.out.print(diseases.size());
          
              
              for(String d: diseases){
                  Set<String>diseaseNeighbours=new HashSet<String>();
                for(Pair P:Networki.keySet()){
                    if(P.getD().equals(d)){
                        diseaseNeighbours.add(P.getG());
                    
                    
                    }
                
                
                }
              CommonGenes.put(d, diseaseNeighbours);
          
          }
              System.out.print(CommonGenes.size());
           HashMap<Pair,Set<Pair>>f = new HashMap<Pair,Set<Pair>>();
           Set<Pair>set4 = new HashSet<Pair>();
            for(String s: CommonGenes.keySet()){
                //System.out.print(s+"\n");
                
                Set<String> set1 =CommonGenes.get(s);
                // System.out.print(set1+"\n");
                 for(String r: CommonGenes.keySet()){
                     Set<String>set3 = new HashSet<String>();
                     if(!s.equals(r)){
                     //System.out.print(r+"\n");
                      Set set2 = CommonGenes.get(r);
                    // System.out.print(set2+"\n");
                      for(String i : set1){
                         if(set2.contains(i)){
                             set3.add(i);
                             
                         }}Set<Pair>set5=new HashSet<Pair>();
                            if(!set3.isEmpty()){
                                
                                for(String h: set3){
                                  set5.add(new Pair(s,h));
                                  set5.add(new Pair(r,h));
                             }
                            
                // System.out.print(new Pair(s,r));
                        
                             f.put(new Pair(s,r),set5);
                        //}
                         
                  
                     }
                     
//if(set3.size()>0){1739	5912
                        //f.put(new Pair(s,r),set3);
                          //System.out.print(P.getD());
}
                 }
            
            }
            System.out.print(f.size()+"disease"+"\n");
            
            return f;
            }


public HashMap<Pair, Double> doIntegration(final HashMap<String, Double> lls, final HashMap<String, Double> ranking, final Comparator<Double> ordering, BuildingBioGrid Bio, HashMap<Pair,Set<Pair>>DiseasePair) {
        List<String> datasetsRankedByScore = new ArrayList<String>(lls.keySet());
        Collections.sort(datasetsRankedByScore, new Comparator<String>() {

            public int compare(String o1, String O2) {
                return ordering.compare(ranking.get(o1), ranking.get(O2));
            }

        });
        

        HashMap<Pair, Double> scores = new HashMap<Pair, Double>();
        for (Pair pair : DiseasePair.keySet()) {
            //System.out.print(pair);
            Set<Pair>CommonDisease=DiseasePair.get(pair);
            //System.out.print(CommonDisease);
            List<Double> scoresInOrder = new ArrayList<Double>();
            for(Pair p: CommonDisease){
                
                for (String ds : datasetsRankedByScore) {

                    if (Bio.getAlldatasets().get(ds).contains(p)) {
                        Double score = lls.get(ds);
                        if (score == null) {
                        throw new NullPointerException("Null score for :" + ds);

                    }
                    scoresInOrder.add(score);
                }

            }}//System.out.print(scoresInOrder);
            double finalScore = calculateScore(scoresInOrder);
            if (finalScore > 0) {
                double roundOff = Math.round(finalScore*100)/100;

                scores.put(pair, roundOff);
            }
            
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
            out2 = new PrintWriter(new BufferedWriter(new FileWriter("rankintegrated2.txt")));
            
            for (Pair i :PairorderebyScoreValue) {
                out2.println(i.getD() + "\t" + i.getG()+"\t" + scores.get(i));
               
            }
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
}         
       System.out.print(scores.size()+"LLs score"+"\n");
        return scores;
        
}
    public double calculateScore(List<Double> scoresInOrder) {

        double LLS = 0.0;

        for (int i = 0; i < scoresInOrder.size(); i++) {
            double raw_score = scoresInOrder.get(i);
            double denominator = Math.pow(1.2, i);
            double scaled_score = raw_score / denominator;

            LLS += scaled_score;

            //System.out.println(i + "\t" + raw_score + "\t" + denominator + "\t" + scaled_score + "\t" + LLS);
        }

        return LLS;

    }
    
    public HashMap<Pair, List<String>> doIntegrationasso(BuildingBioGrid Bio, HashMap<Pair,Set<Pair>>DiseasePair) {
        

        HashMap<Pair, List<String>> Asso = new HashMap<Pair, List<String>>();
        for (Pair pair : DiseasePair.keySet()) {
            Set<Pair>CommonDisease=DiseasePair.get(pair);
            List<String> Associations = new ArrayList<String>();
            for(Pair p: CommonDisease){
                for (String ds : Bio.getAsso().keySet()) {

                    if (Bio.getAsso().get(ds).containsKey(p)) {
                        Associations.add(Bio.getAsso().get(ds).get(p));
                        
                    
                }

            }}///System.out.print(scoresInOrder);
            
            Asso.put(pair,Associations);
        }
        //System.out.print(Asso.size()+"association"+"\n");
        
        return Asso;
        
}
    
    
    

}





    
                      



                    




   

