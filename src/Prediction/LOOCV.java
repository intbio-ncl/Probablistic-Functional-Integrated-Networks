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
   /* public void predictDGA(Map<Pair,Double>network) throws IOException{
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
                double commonweight=maximsumofweight(Trainnetwork,Commonneighbours,p2,diseaseNeighbour,GeneNeighbour);
                double totalweight=totaledges(Trainnetwork,Commonneighbours,p2,diseaseNeighbour,GeneNeighbour);
                double score =commonweight/totalweight;
                predicatedassociations.put(p2, score);
        }
            }
    }
        }
        
    
         //System.out.print("Predictedassociations"+predicatedassociations.size()+"\n");
         NetworkPrediction NP1=new NetworkPrediction();
         NP1.writetoFilePrediction(predicatedassociations);
         Validation vd = new Validation();
         List<Double>AUC=vd.NetworkValidat(test,i);
         i++;
    }
    }
    
    
    public void predictDGAs(Map<Pair,Double>network,Map<Pair,Double>Test,int i) throws IOException{
    Map<Pair,Double>predicatedassociations=new HashMap<Pair,Double>();
    Map<Pair,Double>removededges=new HashMap<Pair,Double>();
     NetworkPrediction NP = new NetworkPrediction();
     
    
       
        
       Set<String>Diseases=new HashSet<String>();
        Set<String>Genes=new HashSet<String>();
        Set<Pair>neighbour=new HashSet<Pair>();
         for(Pair p:network.keySet()){
              Diseases.add(p.getD());
          }
           for(Pair p:network.keySet()){
              Genes.add(p.getG());
          }
          for(String d: Diseases){
              for(String g: Genes){
        System.out.print("disease"+"\t"+d+"\n");
        System.out.print("gene"+"\t"+g+"\n");
            Set<Pair>GeneNeighbour=GNeighbours(network,new Pair(d,g));
            System.out.print("gene neighbours"+"\t"+GeneNeighbour+"\n");
            if(GeneNeighbour!=null){
            Set<String>diseaseNeighbour=DNeighbours(network,new Pair(d,g));
            System.out.print("diseaseNeighbours"+"\t"+diseaseNeighbour+"\n");
            if(diseaseNeighbour!=null){
            Set<Pair>Commonneighbours=CommonNeighbours(diseaseNeighbour,GeneNeighbour,new Pair(d,g));
            System.out.print("CommonNeighbours"+"\t"+Commonneighbours+"\n");
            if(Commonneighbours!=null){
                double commonweight=maximsumofweight(network,Commonneighbours,new Pair(d,g),diseaseNeighbour,GeneNeighbour);
                double totalweight=totaledges(network,Commonneighbours,new Pair(d,g),diseaseNeighbour,GeneNeighbour);
                double score =commonweight/totalweight;
                predicatedassociations.put(new Pair(d,g), score);
        }
            }
    }
        }
          }
    
         System.out.print("Predictedassociations"+predicatedassociations.size()+"\n");
         NetworkPrediction NP1=new NetworkPrediction();
         NP1.writetoFilePrediction(predicatedassociations);
         Validation vd = new Validation();
         List<Double>AUC=vd.NetworkValidat(Test,i);
        
        }*/
    
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
    
    
    public Map<Pair,Set<String>> DNeighboursall(Map<Pair,Double>network){
        Set<String>Diseases=new HashSet<String>();
        Set<String>Genes=new HashSet<String>();
        Map<Pair,Set<String>>DN=new HashMap<Pair,Set<String>>();
          for(Pair p:network.keySet()){
              Diseases.add(p.getD());
          }
           for(Pair p:network.keySet()){
              Genes.add(p.getD());
          }
          for(String d: Diseases){
              for(String g: Genes){
                 Set<String>DNeigh= DNeighbours(network, new Pair(d,g));
                   DN.put(new Pair(d,g),DNeigh);
          }
        
          }
     System.out.print(DN);
       return DN; 
      
    }
    public Map<String,Set<Pair>> GNeighbours(Map<Pair,Double>Network){
            
            Set<String>GfirstNeighbour=new HashSet<String>();
            Set<Pair>GSecondNeighbour=new HashSet<Pair>(); 
            Map<String,Set<Pair>>AllGenesNeighbours=new HashMap<String,Set<Pair>>();
            Set<String>AllGenes=new HashSet<String>();
            for(Pair edge:Network.keySet()){
                AllGenes.add(edge.getG());
            }   for(String g:AllGenes){
                    for(Pair P:Network.keySet()){
                    if(P.getG().equals(g))
                        GfirstNeighbour.add(P.getD());
        }
            
            if(!GfirstNeighbour.isEmpty()){
                for(String d2: GfirstNeighbour){
                    for(Pair P2:Network.keySet()){
                    if(P2.getD().equals(d2)&&!P2.getG().equals(g)){
                        GSecondNeighbour.add(new Pair(d2,P2.getG()));
                     }
        }
         }     
               
            }
            if(!GSecondNeighbour.isEmpty()){
                AllGenesNeighbours.put(g,GSecondNeighbour);
            }
            
            
     }
            return AllGenesNeighbours;
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
 
 
 
 //public void commonneighboursall
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

    public double totaledges(Map<Pair,Double>Network,Set<Pair>Common,Pair edge,Set<String>DN,Set<Pair>GN){
        double totaldiseaseweight=0.0;
        double totalgeneweight=0.0;
        double edgeweight2=0.0;
        Set<String>GeneNeigbourgenes=new HashSet<String>();
         
        for(String d:DN){
            totaldiseaseweight+=Network.get(new Pair(edge.getD(),d));
        }
        for(Pair p :GN){
            GeneNeigbourgenes.add(p.getG());
            
        }
       
        
        for(String g2:GeneNeigbourgenes){
            Set<String>Paths=new HashSet<String>();
            List<Double>score=new ArrayList<Double>();
            for(Pair p:GN){
                if(p.getG().equals(g2)){
                    Paths.add(p.getD());
        }
 }

         
             for(String d: Paths){
                    double sumweight=Network.get(new Pair(d,g2))+Network.get(new Pair(d,edge.getG()));
                    score.add(sumweight);
                     }
         
           totalgeneweight+=Collections.max(score);
         }
         


          edgeweight2=totaldiseaseweight+totalgeneweight;


      return edgeweight2;

}

}