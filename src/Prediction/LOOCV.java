/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prediction;
import java.util.*;
import pfinnetwork.Pair;
import pfinnetwork.Triple;
import java.io.*;
/**
 *
 * @author aoeshaalsobhe
 */
public class LOOCV {
    public void predictDGA(Map<Pair,Double>network) throws IOException{
    Map<Pair,Double>predicatedassociations=new HashMap<Pair,Double>();
    Map<Pair,Double>removededges=new HashMap<Pair,Double>();
     NetworkPrediction NP = new NetworkPrediction();
      int i=0;
    for(Pair p:network.keySet()){
       
        Map<Pair,Double>Trainnetwork=new HashMap<Pair,Double>();
        Map<Pair,Double>test=new HashMap<Pair,Double>();
        test.put(p, network.get(p));
        Trainnetwork.putAll(network);
        Trainnetwork.remove(p);
        Set<String>genes=new HashSet<String>();
        Set<String>diseases=new HashSet<String>();
        Set<Pair>neighbour=new HashSet<Pair>();
        for(Pair pt:Trainnetwork.keySet()){
            diseases.add(pt.getD());
            genes.add(pt.getG());
        }
        
        for(String g:genes){
            neighbour.add(new Pair(p.getD(),g));
        }
        for(String d:diseases){
            neighbour.add(new Pair(d,p.getG()));
        }
        for(Pair p2:neighbour){
            Set<Pair>GeneNeighbour=GNeighbours(Trainnetwork,p2);
            if(GeneNeighbour!=null){
            Set<String>diseaseNeighbour=DNeighbours(Trainnetwork,p2);
            if(diseaseNeighbour!=null){
            Set<Pair>Commonneighbours=CommonNeighbours(diseaseNeighbour,GeneNeighbour,p2);
            if(Commonneighbours!=null){
                double score=maximsumofweight(Trainnetwork,Commonneighbours,p2,diseaseNeighbour,GeneNeighbour);
                predicatedassociations.put(p2, score);
        }
            }
    }
        }
        
    
         System.out.print(predicatedassociations.size());
         NetworkPrediction NP1=new NetworkPrediction();
         NP1.writetoFilePrediction(predicatedassociations);
         Validation vd = new Validation();
         List<Double>AUC=vd.NetworkValidat(test,i);
         i++;
    }
    }
    public Set<String> DNeighbours(Map<Pair,Double>Network,Pair edge){
         Set<String>DNeighbour=new HashSet<String>();
            for(Pair P:Network.keySet()){
                if(P.getD().equals(edge.getD())){
                    DNeighbour.add(P.getG());
        }
            }
            if(!DNeighbour.isEmpty())
                 return DNeighbour;
            else 
                return null;
          
            
    }
    public Set<Pair> GNeighbours(Map<Pair,Double>Network,Pair edge){
            
            Set<String>GfirstNeighbour=new HashSet<String>();
            Set<Pair>GSecondNeighbour=new HashSet<Pair>(); 
            
                for(Pair P:Network.keySet()){
                    if(P.getG().equals(edge.getG()))
                        GfirstNeighbour.add(P.getD());
        }
            
            if(!GfirstNeighbour.isEmpty()){
                for(String d2: GfirstNeighbour){
                    for(Pair P2:Network.keySet()){
                    if(P2.getD().equals(d2)&&!P2.getG().equals(edge.getG())){
                        GSecondNeighbour.add(new Pair(d2,P2.getG()));
                     }
        }
         }     
               
            }
            if(!GSecondNeighbour.isEmpty()){
                return GSecondNeighbour;
            }
            else 
                return null;
            
     }
    
 public Set<Pair>CommonNeighbours(Set<String>DN,Set<Pair>GN,Pair edge){
         Set<Pair>Common = new HashSet<Pair>();
         Set<String>GNN=new HashSet<String>();
         for(Pair p:GN){
             GNN.add(p.getG());
         }
        for (Pair p : GN){
             for(String g1:DN ){
                 if(p.getG().equals(g1)){
                    Common.add(p);
                 
                 }
             
             }
        }
        if(!Common.isEmpty()){
         return Common;
 
        }
        else{
            return null;
        }
    }
 public double maximsumofweight(Map<Pair,Double>Network,Set<Pair>Common,Pair edge,Set<String>DN,Set<Pair>GN){
        double commondiseaseweight=0.0;
        double commongeneweight=0.0;
        double edgeweight=0.0;
        double totaldiseaseweight=0.0;
        double totalgeneweight=0.0;
        Set<String>Commongenes=new HashSet<String>();
         
        for(String d:DN){
            totaldiseaseweight+=Network.get(new Pair(edge.getD(),d));
        }
        for(Pair p :Common){
            Commongenes.add(p.getG());
            
        }
        for(String g :Commongenes){
            
            commondiseaseweight+=Network.get(new Pair(edge.getD(),g));
        }
        
        for(String g2:Commongenes){
            Set<String>Paths=new HashSet<String>();
            List<Double>score=new ArrayList<Double>();
            for(Pair p:Common){
                if(p.getG().equals(g2)){
                    Paths.add(p.getD());
        }
 }

         
             for(String d: Paths){
                    double sumweight=Network.get(new Pair(d,g2))+Network.get(new Pair(d,edge.getG()));
                    score.add(sumweight);
                     }
         
           commongeneweight+=Collections.max(score);
         }
         
         edgeweight=commondiseaseweight+commongeneweight;
        
        return edgeweight;
 }
}
