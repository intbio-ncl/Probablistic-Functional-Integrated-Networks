/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIANnetwork;
import Integration.Integrationlogger;
import java.util.*;
import pfinnetwork.*;
import java.io.*;

/**
 *
 * @author aoeshaalsobhe
 */
public class NetworkAnalysis {
    public void networkthreshold(Map<Pair,Double>network)throws IOException{
           ClusterAnalysis CA=new ClusterAnalysis();
           for(int i=9;i<20; i++){
                Map<Pair,Double>IntegratedThreshald=new HashMap<Pair,Double>();
                for(Pair p:network.keySet()){
                if(network.get(p)>i){
                   IntegratedThreshald.put(p, network.get(p));
         }
                }
               Map<String,Set<String>>Cluster= CA.clusters("outfileUniProt"+i);
              double Average=CA.ClusterAverage(IntegratedThreshald, Cluster);
            System.out.print(i+"\t"+Average+"\n");
         Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("UniProtintegrated"+i+".txt",IntegratedThreshald);
         
           }
    
    
    
           
           }
    
    
    
    public void MCLInflationvalue(Map<Pair,Double>network)throws IOException{
           ClusterAnalysis CA=new ClusterAnalysis();
           for(double i=1.5;i<=10;i+=0.5){
               
               Map<String,Set<String>>Cluster= CA.clusters("outfileUni"+i);
               double Average=CA.ClusterAverage(network, Cluster);
               System.out.print(i+"\t"+Average+"\n");
         //Integrationlogger ILogger = new Integrationlogger();
        // ILogger.logToFile("integratedhighlevelnetwork"+i+".txt",IntegratedThreshald);
         
           }
    
    
    
           
           }
    
           }
    
    
    

