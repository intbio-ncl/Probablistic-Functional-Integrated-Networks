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

    public Map<Pair,Double> Predict(Map<Pair,Double>Network,Map<String,Set<Triple>>GeneNeighbours,Map<String,Set<String>>DiseaseNeighbours,Map<Pair,Set<String>>CommonNeighbours){ 
             
        Map<Pair,Double>Predictedassociations=new HashMap<Pair,Double>();
        
        for(Pair P:CommonNeighbours.keySet()){
            //System.out.print(P+"\n");
            
            Set<Pair>DiseaseCommon=new HashSet<Pair>();
            Set<Triple>GeneCommon=new HashSet<Triple>();
           Set<Pair>unweightedfirstcommonneighbours=new HashSet<Pair>();
           Set<Pair>unweightedsecondcommonneighbours=new HashSet<Pair>();
            for(String g:CommonNeighbours.get(P)){
             DiseaseCommon.add(new Pair(P.getD(),g));
                    for(Triple t:GeneNeighbours.get(P.getG())){
                       if(t.getPubmedID().equals(g)){
                            GeneCommon.add(t);
                 
                 }
                     }}
            for(Triple T:GeneCommon){
            
               unweightedfirstcommonneighbours.add(new Pair(T.getG(),T.getD()));
               unweightedsecondcommonneighbours.add(new Pair(T.getG(),T.getPubmedID()));
            }
           // System.out.print(DiseaseCommon+"\n");
            // System.out.print(GeneCommon+"\n");
                    NetworkPrediction NP=new NetworkPrediction();
                   JaccardIndex Union= NP.TotalWeighted(P.getG(), P.getD(), GeneNeighbours.get(P.getG()),DiseaseNeighbours.get(P.getD()), Network);
                    JaccardIndex Intersection=NP.TotalWeighted(P.getG(), P.getD(), GeneCommon,CommonNeighbours.get(P), Network);;
                   double weightedCommonNeighbour= Intersection.getDtotal()+Intersection.getGtotalweight();
                   double weightedJaccardIndex= weightedCommonNeighbour/(Union.getGtotalweight()+Union.getDtotal());
                  // double UnweightedCommonNumber =GeneCommon.size()+DiseaseCommon.size();
                  // double UnweightedJaccardIndex= UnweightedCommonNumber/(GeneNeighbours.size()+DiseaseNeighbours.size());
                    
           Predictedassociations.put(P,weightedJaccardIndex);
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
          
            
    }
     public Map<String,Set<Triple>> GNeighbours(Map<Pair,Double>Network){
        Set<String>Genes=new HashSet<String>();
        Map<String,Set<Triple>>GNEIGHBOURS=new HashMap<String,Set<Triple>>();
        for(Pair P:Network.keySet()){
                Genes.add(P.getG());
        }
            for(String gene:Genes){
               Set<String>GInNeighbour=new HashSet<String>();
               Set<Pair>GNeighbour=new HashSet<Pair>(); 
               Set<String>GENES=new HashSet<String>();
               Set<Triple>GenesNeighbours2=new HashSet<Triple>();
               Set<Triple>GenesNeighbours=new HashSet<Triple>();
               Set<Triple>GenesNeighboursfinal=new HashSet<Triple>();
                for(Pair P1:Network.keySet()){
                    if(gene.equals(P1.getG()))
                    GInNeighbour.add(P1.getD());
        }
            
       
         for(String d2: GInNeighbour){
            for(Pair P2:Network.keySet()){
                if(P2.getD().equals(d2)&&!P2.getG().equals(gene)){
                    GNeighbour.add(new Pair(d2,P2.getG()));
                       
                     
                     }
                 
              
        }
         }
         for( Pair pair:GNeighbour ){
              
            GenesNeighbours.add(new Triple(gene,pair.getD(),pair.getG()));
            GENES.add(pair.getG());
            
         } 
   // System.out.print(GenesNeighbours.size()+"\t");
         
        //System.out.print(GenesNeighbours.size()+"\t"+GENES.size()+"\n");
         if(GenesNeighbours.size()>0){
              GNEIGHBOURS.put(gene, GenesNeighbours);
            }} //System.out.print(GNEIGHBOURS);
         return GNEIGHBOURS;
        
     }
 public Map<Pair,Set<String>>CommonNeighbours(Map<Pair,Double>Network,Map<String,Set<String>>DN, Map<String,Set<Triple>>GN){
         Map<Pair,Set<String>>f = new HashMap<Pair,Set<String>>();
         
         
         for(String G:GN.keySet()){
              Set<String> GSet =new HashSet<String>();
                 for(Triple P: GN.get(G)){
                     GSet.add(P.getPubmedID());
                 }
                 for(String D:DN.keySet()){
                     Set<String>CommonGenes=new HashSet<String>();
                        for(String d: DN.get(D)){
                            if(GSet.contains(d)){
                                CommonGenes.add(d);
                               }
                        }
                  if(!CommonGenes.isEmpty()){
                      f.put(new Pair(D,G), CommonGenes);
                  }      
    
    }}
        
                 return f;   
         }
 
  public JaccardIndex TotalWeighted(String G, String D,Set<Triple>GNeighbours,Set<String>DNeighbours,Map<Pair,Double>Network){
         
      double GTotal =0;
       double Dtotal=0;
       List<Triple>similartriple=new ArrayList<Triple>();
       similartriple.addAll(GNeighbours);
          for(int i=0;i<GNeighbours.size();i++){
               List<Double>similaredges=new ArrayList<Double>();
               double score1=0.0;
               double score2=0.0;
              for(int j=i+1;j<GNeighbours.size();j++){
              if(similartriple.get(i).getPubmedID().equals(similartriple.get(j).getPubmedID())){
                   score1+=Network.get(new Pair(similartriple.get(i).getG(),similartriple.get(i).getD()))+
                          Network.get(new Pair(similartriple.get(i).getG(),similartriple.get(i).getPubmedID()));
                   score2+=Network.get(new Pair(similartriple.get(j).getG(),similartriple.get(j).getD()))+
                          Network.get(new Pair(similartriple.get(j).getG(),similartriple.get(j).getPubmedID()));
                 similaredges.add(score1);
                 similaredges.add(score2);
              
              }
              }
              if(!similaredges.isEmpty()){
                 GTotal+=Collections.max(similaredges);
              
              }
          
              else{
                  GTotal+=Network.get(new Pair(similartriple.get(i).getG(),similartriple.get(i).getD()))+
                          Network.get(new Pair(similartriple.get(i).getG(),similartriple.get(i).getPubmedID()));;
              }
                  
          }
          //System.out.print(T+"\n");
           //System.out.print(Network.get(new Pair(T.getD(),T.getG()))+"\n");
           //System.out.print(Network.get(new Pair(T.getG(),T.getPubmedID()))+"\n");
           
          
           
      
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