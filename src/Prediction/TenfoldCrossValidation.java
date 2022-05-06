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
import pfinnetwork.Pair;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class TenfoldCrossValidation {
    public void crossvalidation(Map<Pair,Double>Network)throws IOException{
    
           /*for(Pair P1 : IntegratedS.keySet()){
               if(IntegratedS.get(P1)<15){
                  Network.remove(P1);
               }
           
           }*/
              System.out.print("Size Network after removing"+"\t"+Network.size()+"\n");
         
        List<Pair>network=new ArrayList<Pair>();
         List<Map>networks=new ArrayList<Map>();
         Set<Double>d=new HashSet<Double>();
         Set<Double>d1=new HashSet<Double>();

          for(Pair link:Network.keySet()){
              network.add(link);
         
         }
         System.out.print("size list"+"\t"+network.size()+"\n");
         for( int i =0; i<10;i++){
             
               
          Map<Pair,Double>subnetwork=new HashMap<Pair,Double>();
            while(subnetwork.size()<2454) {
                            
                Random random = new Random();
                int randomNumber = random.nextInt(network.size());
                subnetwork.put(network.get(randomNumber),Network.get(network.get(randomNumber)));
                //System.out.print(network.get(randomNumber)+"\t"+Network.get(network.get(randomNumber))+"\n"+"\n");
                
                network.remove(network.get(randomNumber));
               
            }
             networks.add(subnetwork);
         }
           for(int s=0;s<1;s++){
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
                       // System.out.print(Test);
         NetworkPrediction NP = new NetworkPrediction();
        
       Map<String,Set<Pair>>GeneNeighbour=NP.GNeighbours(Train);
       
        Map<String,Set<String>>DNeighbour=NP.DNeighbours(Train);
       
        Map<Pair,Set<String>>Common=NP.CommonNeighbours(Train,DNeighbour, GeneNeighbour);
        System.out.print(Common.size());
       Map<Pair,Double>Predicatedassociation=NP.Predict(Train,GeneNeighbour,DNeighbour,Common);
         NP.writetoFilePrediction(Predicatedassociation);
   
         Validation vd = new Validation();
         Set<List>AUC=vd.NetworkValidat(Test,j);
        
         }
         
           
        
                          
}
}