/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prediction;
import DiseaseNetwork.NodesDistribution;
import PIANnetwork.NodeDegree;
import pfinnetwork.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author aoeshaalsobhe
 */
public class NetworkThreshold {
    public Map<Pair,Double> threshold(Map<Pair,Double>network)throws IOException{
           Map<Pair,Double>Thresholdnetwork=new HashMap<Pair,Double>();
            Set<Pair>nodes=new HashSet<Pair>();
                for(Pair p2:network.keySet()){
        
                    if(network.get(p2)>8){
            
                        Thresholdnetwork.put(p2, network.get(p2));
                        nodes.add(p2);
            
            }
        }
                System.out.print("\n size network"+network.size()+"\t size after threshold"+Thresholdnetwork.size()+"\t");
            Map<Pair,Double>Thresholdnetwork2=new HashMap<Pair,Double>();
            Thresholdnetwork2.putAll(Thresholdnetwork);
            NodesDistribution ND=new NodesDistribution();
            NodeDegree nd= ND.NodesDistributionn(nodes);
            for(Pair p: Thresholdnetwork.keySet()){
                if(nd.getDiseaseDegree().get(p.getD())==1&&nd.getGeneDegree().get(p.getG())==1){
                    Thresholdnetwork2.remove(p);
                    
                }
            }
     System.out.print("\n"+Thresholdnetwork2.size()+"\t size after reomve isolated");
    
    return Thresholdnetwork2;
    
    }
}
