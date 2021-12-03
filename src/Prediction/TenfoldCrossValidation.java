/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prediction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import pfinnetwork.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class TenfoldCrossValidation {
    public void crossvalidation(Map<Pair,Double>Network)throws IOException{
          Map<Pair,Double>Purnnetwork=new HashMap<Pair,Double>();
           for(Pair P1 : Network.keySet()){
               if(Network.get(P1)>10){
                  Purnnetwork.put(P1,Network.get(P1));
               }
           
           }
              System.out.print("Size Network after removing"+"\t"+Purnnetwork.size()+"\n");
         
        List<Pair>network=new ArrayList<Pair>();
         List<Map>networks=new ArrayList<Map>();
         Set<Double>d=new HashSet<Double>();
         Set<Double>d1=new HashSet<Double>();

          for(Pair link:Purnnetwork.keySet()){
              network.add(link);
         
         }
         System.out.print("size list"+"\t"+network.size()+"\n");
         for( int i =0; i<10;i++){
             
               
          Map<Pair,Double>subnetwork=new HashMap<Pair,Double>();
            while(subnetwork.size()<781) {
                            
                Random random = new Random();
                int randomNumber = random.nextInt(network.size());
                subnetwork.put(network.get(randomNumber),Network.get(network.get(randomNumber)));
                //System.out.print(network.get(randomNumber)+"\t"+Network.get(network.get(randomNumber))+"\n"+"\n");
                
                network.remove(network.get(randomNumber));
               
            }
             networks.add(subnetwork);
         }
           for(int s=0;s<3;s++){
               networks.get(0).put(network.get(s), Network.get(network.get(s)));
           }
         /*Map<Pair,Double>Train=new HashMap<Pair,Double>(); 
           for(int j=0;j<9;j++){
               Train.putAll(networks.get(j));
           }*/
         //System.out.print(networks.get(9)+"\n"+"\n");  
                //System.out.print(networks.get(8)+"\n"+"\n");  
                 for(int j=0;j<10;j++){
                      List<Map>newList=new ArrayList<Map>();
                      newList.addAll(networks);
                   Map<Pair,Double>Train=new HashMap<Pair,Double>();
                     Map<Pair,Double>Test=new HashMap<Pair,Double>();
                       Test.putAll(newList.get(j));
                        newList.remove(j);
                        for(int s=0;s<9;s++){
                        Train.putAll(newList.get(s));
                        
                        }
                       System.out.print("test set"+Test.size()+"\n");
                       System.out.print("test set"+Test.size()+"\n");
         NetworkPrediction NP = new NetworkPrediction();
        
       Map<String,Set<Triple>>GeneNeighbour=NP.GNeighbours(Train);
       
        Map<String,Set<String>>DNeighbour=NP.DNeighbours(Train);
       System.out.print(Train.size()+"\n");
        Map<Pair,Set<String>>Common=NP.CommonNeighbours(Train,DNeighbour, GeneNeighbour);
        System.out.print("Common Pair"+Common.size()+"\n");
        System.out.print(Train.size());
        Map<Pair,Double>Predicatedassociation=NP.Predict(Train,GeneNeighbour,DNeighbour,Common);
        NP.writetoFilePrediction(Predicatedassociation);
        double Negativecases=0.0;
        double Positivecases=0.0;
        Validation vd = new Validation();
        List<Double>AUC=vd.NetworkValidat(Test,j);
        double x =AUC.get(0);
        double y = AUC.get(0);
         Negativecases+=x;
         Positivecases+=y;
        
         }
         
           
        
                          
}
     public void crossvalidationLOO(Map<Pair,Double>Network)throws IOException{
          Map<Pair,Double>Purnnetwork=new HashMap<Pair,Double>();
           for(Pair P1 : Network.keySet()){
               if(Network.get(P1)>10){
                  Purnnetwork.put(P1,Network.get(P1));
               }
           
           }
              System.out.print("Size Network after removing"+"\t"+Purnnetwork.size()+"\n");
         
        List<Pair>network=new ArrayList<Pair>();
         
          for(Pair link:Purnnetwork.keySet()){
              network.add(link);
         
         }
         System.out.print("size list"+"\t"+network.size()+"\n");
         for( int i =0; i<network.size();i++){
             Map<Pair,Double>train=new HashMap<Pair,Double>();
             train.putAll(Purnnetwork);
             Map<Pair,Double>test=new HashMap<Pair,Double>();
                Random random = new Random();
                int randomNumber = random.nextInt(network.size());
                test.put(network.get(randomNumber),Network.get(network.get(randomNumber)));
                //System.out.print(network.get(randomNumber)+"\t"+Network.get(network.get(randomNumber))+"\n"+"\n");
                
                network.remove(network.get(randomNumber));
                train.remove(network.get(randomNumber));
                NetworkPrediction NP = new NetworkPrediction();
        
                Map<String,Set<Triple>>GeneNeighbour=NP.GNeighbours(train);
       
                Map<String,Set<String>>DNeighbour=NP.DNeighbours(train);
       
                Map<Pair,Set<String>>Common=NP.CommonNeighbours(train,DNeighbour, GeneNeighbour);
                 System.out.print("Common Pair"+Common.size()+"\n");
                 Map<Pair,Double>Predicatedassociation=NP.Predict(train,GeneNeighbour,DNeighbour,Common);
                 NP.writetoFilePrediction(Predicatedassociation);
                 double Negativecases=0.0;
                 double Positivecases=0.0;
                Validation vd = new Validation();
               List<Double>AUC=vd.NetworkValidat(test,i);
               double x =AUC.get(0);
               double y = AUC.get(0);
               Negativecases+=x;
                Positivecases+=y;
        
         }
         
           
        
                          
}
}