/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prediction;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import pfinnetwork.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class NetworkPrediction {

    public Map<Pair,Double> Predict(Map<Pair,Double>Network,Map<String,Set<Pair>>GeneNeighbours,Map<String,Set<String>>DiseaseNeighbours,Map<Pair,Set<String>>CommonNeighbours){ 
             
        Map<Pair,Double>Predictedassociations=new HashMap<Pair,Double>();
        for(Pair P:CommonNeighbours.keySet()){
           JaccardIndex Union=TotalWeighted(P.getG(),P.getD(),GeneNeighbours.get(P.getG()),DiseaseNeighbours.get(P.getD()),Network);
            Set<Pair>CommonGeneEdged=new HashSet<Pair>();
            Set<String>AllGeneDirect=new HashSet<String>();
            Set<String>CommonDirectNeighbours=new HashSet<String>();
            for(Pair p:GeneNeighbours.get(P.getG())){
                AllGeneDirect.add(p.getD());
                                   
                                   }
            for(String i:CommonNeighbours.get(P) ){
                for(Pair P1: GeneNeighbours.get(P.getG())){
                    if(P1.getG().equals(i)){
                        CommonGeneEdged.add(P1);
                        CommonDirectNeighbours.add(P.getD());
                                              }
                         
                                         }
            }
           JaccardIndex intersection=TotalWeighted(P.getG(),P.getD(),CommonGeneEdged,CommonNeighbours.get(P),Network);
            double UnweightedCN=CommonNeighbours.get(P).size()+CommonGeneEdged.size()+CommonDirectNeighbours.size();
            double Jaccardunweighted=UnweightedCN/(GeneNeighbours.get(P.getG()).size()+DiseaseNeighbours.get(P.getD()).size()+AllGeneDirect.size());
           double CommonNeighbours2=intersection.getDtotal()+intersection.getGtotalweight();
          // double PA=intersection.getDtotal()*intersection.getGtotalweight();
           double Jaccard=CommonNeighbours2/(Union.getDtotal()+Union.getGtotalweight());
           Predictedassociations.put(P,Jaccard);
           
           }
            return Predictedassociations;
            
                }
       
   
        
             
    
    
    public Map<String,Set<String>> DNeighbours(Map<Pair,Double>Network){
        
        
       
       Set<String>Diseases=new HashSet<String>();
       Map<String,Set<String>>DFinalNeighbour=new HashMap<String,Set<String>>();
       for(Pair P:Network.keySet()){
                Diseases.add(P.getD());
        }
            for(String d: Diseases){
                Set<String>DNeighbour=new HashSet<String>();
               for(Pair P:Network.keySet()){
                  if(d.equals(P.getD()))
                    DNeighbour.add(P.getG());
        }
               
            DFinalNeighbour.put(d, DNeighbour);
            }
       return DFinalNeighbour;
            /* Set<Pair>CNeighbour=new HashSet<Pair>();
             Set<Pair>CNeighbour2=new HashSet<Pair>();
             for(Pair Pe: GNeighbour){
             CNeighbour.add(Pe);
             CNeighbour2.add(Pe);
             }
             for(Pair P:CNeighbour){
                 for(Pair P2: CNeighbour2){
                     if(P.getG().equals(P2.getG())){
                       if(Network.get(P)+Network.get(new Pair(P.getD(),G))>=Network.get(P2)+Network.get(new Pair(P2.getD(),G))){
                           GNeighbour.remove(P2);
                       }else{
                          GNeighbour.remove(P);
                       }
                     
                     
                     }
                     else{
                        GNeighbour=GNeighbour;
                     
                     
                     }
                 }
             
             }*/
             
    }
     public Map<String,Set<Pair>> GNeighbours(Map<Pair,Double>Network){
        Set<String>Genes=new HashSet<String>();
        Map<String,Set<Pair>>GNEIGHBOURS=new HashMap<String,Set<Pair>>();
        for(Pair P:Network.keySet()){
                Genes.add(P.getG());
        }
            for(String gene:Genes){
               Set<String>GInNeighbour=new HashSet<String>();
               Set<Pair>GNeighbour=new HashSet<Pair>(); 
                for(Pair P1:Network.keySet()){
                    if(gene.equals(P1.getG()))
                    GInNeighbour.add(P1.getD());
        }
            
       
         for(String d2: GInNeighbour){
            for(Pair P2:Network.keySet()){
                if(P2.getD().equals(d2)&&!P2.getG().equals(gene)){
                    GNeighbour.add(new Pair(d2,P2.getG()));
                       
                     
                     }
                 
                 
        }}
         if(!GNeighbour.isEmpty()){
         GNEIGHBOURS.put(gene, GNeighbour);
            }
            }
         return GNEIGHBOURS;
     }
 public Map<Pair,Set<String>>CommonNeighbours(Map<Pair,Double>Network,Map<String,Set<String>>DN, Map<String,Set<Pair>>GN){
         Map<Pair,Set<String>>f = new HashMap<Pair,Set<String>>();
         
         
         for(String G:GN.keySet()){
             
             Set<Pair>genesneighbour=GN.get(G);
              Set<String> GSet =new HashSet<String>();
             
                 for(Pair P: genesneighbour){
                     GSet.add(P.getG());
                 }
                 for(String D:DN.keySet()){
                     if(!Network.containsKey(new Pair(D,G))){
                         Set<String>CommonGenes=new HashSet<String>();
                     Set<String>Diseasesneighbours=DN.get(D);
                 
                  
                       for(String d: Diseasesneighbours){
                            if(GSet.contains(d)){
                                CommonGenes.add(d);
                               }
                       }
                 
                 
                       
                  if(!CommonGenes.isEmpty()){
                      f.put(new Pair(D,G), CommonGenes);
                  }      
    
    }
         }}
                 return f;   
         }
 
  public JaccardIndex TotalWeighted(String G, String D,Set<Pair>GNeighbours,Set<String>DNeighbours,Map<Pair,Double>Network){
         
      double GTotal =0;
       double Dtotal=0;
      for(Pair P:GNeighbours){
           GTotal+=Network.get(P)+Network.get(new Pair(P.getD(),G));
          
           }
      
       for(String d:DNeighbours){
           Dtotal+=Network.get(new Pair(D,d));
      
       }
  
  
    
            
            
              
        
       
         return new JaccardIndex(Dtotal,GTotal);
  }
  public void writetoFilePrediction(Map<Pair,Double>Prediction) throws IOException {
      Map<Pair,Double>ranking=Prediction;
      Map<Pair,Double>topranked=new HashMap<Pair,Double>();
      final Comparator<Double> ordering=Collections.<Double>reverseOrder();
       List<Pair> PairRankedByScore = new ArrayList<Pair>(Prediction.keySet());
        Collections.sort(PairRankedByScore, new Comparator<Pair>() {

            public int compare(Pair o1, Pair O2) {
                return ordering.compare(ranking.get(o1), ranking.get(O2));
            }

        });
        
        PrintWriter out = null;
        try {
            int counter=0;
            String outFileName = "Prediction.txt";
            out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            //out.println("number of Predicted Pairs = " + Prediction.size());
            
                for (Pair s2 : PairRankedByScore) {
                      out.println(s2.getD()+"\t" +s2.getG()+ "\t" + Prediction.get(s2));
                       
            }
            
           
        } finally {
            if (out != null) {
                out.close();
                
            }
        }
        for(Pair p:topranked.keySet())
            System.out.print(p+"\t"+topranked.get(p));
    }
 
}