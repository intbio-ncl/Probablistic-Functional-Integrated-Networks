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
import java.util.Collections;

/**
 *
 * @author aoeshaalsobhe
 */
public class NetworkAnalysis {
    public void networkthreshold(Map<Pair,Double>network)throws IOException{
           ClusterAnalysis CA=new ClusterAnalysis();
           Map<String,Integer>Networksize=new HashMap<String,Integer>();
            PrintWriter out = null;
            String outFileName = "MCScoredNetworkThresholds.txt";
            out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
        try {
           for(int i=7;i<=30; i++){
                Map<Pair,Double>IntegratedThreshald=new HashMap<Pair,Double>();
                for(Pair p:network.keySet()){
                if(network.get(p)>i){
                   IntegratedThreshald.put(p, network.get(p));
         }
                }
               Map<String,Set<String>>Cluster= CA.clusters("outfileMC"+i+1.5);
              double Average=CA.ClusterAverage(IntegratedThreshald, Cluster);
           // System.out.print(i+"\t"+Cluster.size()+"\t"+IntegratedThreshald.size()+"\n");
            
         Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("MCScoredNetwork"+i+".txt",IntegratedThreshald);
           
         
            
           
                out.append(i+"\t"+Cluster.size()+"\t"+IntegratedThreshald.size()+"\n");
            
           }
            
        
         out.close();
        
        } finally {
            if (out != null) {
                out.close();
                
            }
        }
         
           }
    
    
    public void MCLInflationvalue(Map<Pair,Double>network)throws IOException{
           ClusterAnalysis CA=new ClusterAnalysis();
           for(double i=1.5;i<=10;i+=0.5){
               
               Map<String,Set<String>>Cluster= CA.clusters("outfileOverlap"+i);
              // double Average=CA.ClusterAverage(network, Cluster);
               //System.out.print(i+"\t"+Avera./ge+"\n");
         //Integrationlogger ILogger = new 'nmm,,,˚k                nnIntegrationlogger();
        // ILogger.logToFile("integratedhighlevelnetwork"+i+".txt",IntegratedThreshald);
         
           }
    }
    
    public void MCLClusterSizeAllNetwork()throws IOException{
           ClusterAnalysis CA=new ClusterAnalysis();
               Map<String,Map<String,Set<String>>>Clustersize=new HashMap<String,Map<String,Set<String>>>();
               
               Map<String,Set<String>>Cluster1= CA.clusters("outfileLTP1.8");
               Map<String,Set<String>>Cluster2= CA.clusters("outfileHEL1.8");
               Map<String,Set<String>>Cluster3= CA.clusters("outfileMG1.8");
               Map<String,Set<String>>Cluster4= CA.clusters("outfileMC1.8");
               Map<String,Set<String>>Cluster5= CA.clusters("outfileOMIM1.8");
               Map<String,Set<String>>Cluster6= CA.clusters("outfileUniprot1.8");
               
                 //  System.out.print(Cluster6+"\n");
               //System.out.print(Cluster1);
               
             
                    Clustersize.put("LTP",Cluster1);
                    Clustersize.put("HEL",Cluster2);
                    Clustersize.put("MG",Cluster3);
                    Clustersize.put("MC", Cluster4);
                    Clustersize.put("OMIM",Cluster5);
                    Clustersize.put("UniProt",Cluster6);
                    
              //double Average=CA.ClusterAverage(network, Cluster);
              PrintWriter out = null;
        try {
            String outFileName = "MCLClusterSize.txt";
            out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
                
                for(String i:Clustersize.keySet()){
                    for(String j:Clustersize.get(i).keySet())
                       
                        out.print(i+"\t"+j+"\t"+Clustersize.get(i).get(j).size()+"\n");
                       System.out.print(i+"\t"+Clustersize.get(i).size()+"\n");
         }
                
        out.close();
        } finally {
            if (out != null) {
                out.close();
                
            }
        }
               
    
    
    
           
           }
 public void inflatthreshold(Map<Pair,Double>network)throws IOException{
            ClusterAnalysis CA=new ClusterAnalysis();
             List<Double>inflation=new ArrayList<Double>();
             //inflation.add(0.50);
            inflation.add(1.5);
          inflation.add(1.8);
           inflation.add(2.0);
            inflation.add(2.5);
            inflation.add(3.0);
             inflation.add(3.5);
             inflation.add(4.0);
            inflation.add(4.5);
            inflation.add(5.0);
            inflation.add(5.5);
            inflation.add(6.0);
            inflation.add(6.5);
            inflation.add(7.0);
            inflation.add(7.5);
            Collections.sort(inflation);
            //System.out.print(inflation);
            PrintWriter out = null;
        try {
            String outFileName = "ClusterAverageUniProtDI.txt";
            out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            
          
           
                 out.append("\n");
                for(int j=10;j<=10;j++){
                    Map<Pair,Double>IntegratedThreshald=new HashMap<Pair,Double>();
                    for(Pair p:network.keySet()){
                        if(network.get(p)>j){
                        IntegratedThreshald.put(p, network.get(p));
         }
                } 
                   // out.append(j+"\t");
                   // System.out.print(IntegratedThreshald.size());
                        for(double d: inflation){
                            out.append(d+"\t");
                            Map<String,Set<String>>Cluster= CA.clusters("outfileUniProt"+j+d);
                          //double Average=CA.ClusterAverage(IntegratedThreshald, Cluster);
                          //System.out.print(Average);
                          
                            //out.append(Average+"\n");
                        
                }
                        //out.append("\n");
                    }
                   // out.append("\n");
                    //System.out.print("\n");
            
        
         out.close();
        } finally {
            if (out != null) {
                out.close();
                
            }
        }
        
        
   
    }
  public void Clustercohesives(Map<Pair,Double>network)throws IOException{
           ClusterAnalysis CA=new ClusterAnalysis();
           
                
            PrintWriter bw100 = null;
        try {
                  
            
                 bw100 = new PrintWriter(new FileWriter(new File("ClustersAverage.txt")));
               Map<String,Set<String>>Cluster= CA.clusters("outfileunweightednetwork");
             // double Average=CA.ClusterAverage(network, Cluster);
               //System.out.print(i+"\t"+Avera./ge+"\n");
         //Integrationlogger ILogger = new 'nmm,,,˚k                nnIntegrationlogger();
        // ILogger.logToFile("integratedhighlevelnetwork"+i+".txt",IntegratedThreshald);
        /* for(String i:Average.keySet()){
            bw100.println(i+"\t"+Average.get(i)+"\n");
                      
                         }*/
                       //System.out.print(Average+"\n");
                  
                       
                   bw100.close();
                              }
        catch(Exception e){
           e.printStackTrace();

                           }   
                
            finally {
            if (bw100 != null) {
                try {
                    bw100.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    
    
  }
  public void ClusterConnectednessAverage()throws IOException{
        PIAN network=new PIAN();
        Map<Pair,Double>LTPScoredNetwork=network.producenetwork();
        MonogenicScoredNetwork MG=new MonogenicScoredNetwork();
        Map<Pair,Double>MGScoredNetwork=MG.ProduceMonogenicScoredNetwork();
        ELScoredData EL=new ELScoredData();
        Map<Pair,Double>HELScoredNetwork=EL.ProduceNetwork();
        OMIMScoredNetwork OMIM=new OMIMScoredNetwork();
        OMIM.parsingOMIM();
        OMIMasGoldStandard omim=new OMIMasGoldStandard();
         Map<Pair,Double>OMIMScoredNetwork=omim.produceOmimNetwork();
         UniprotscoredPIAN uniprot=new UniprotscoredPIAN();
         Map<Pair,Double>UniProtScoredNetwork=uniprot.produceUniprotNetwork();
         OverlapedDataScoredDatasets MC=new OverlapedDataScoredDatasets();
         Map<Pair,Double>MCScoredNetwork=MC.producenetwork();
         Map<String,Map<Pair,Double>>AllNetworks=new HashMap<String,Map<Pair,Double>>();
         AllNetworks.put("LTP_GS",LTPScoredNetwork);
         AllNetworks.put("HEL_GS",HELScoredNetwork);
         AllNetworks.put("MG_GS",MGScoredNetwork);
         AllNetworks.put("OMIM_GS",OMIMScoredNetwork);
         AllNetworks.put("UniProt_GS",UniProtScoredNetwork);
         AllNetworks.put("MC_GS",MCScoredNetwork);
         ClusterAnalysis CA=new ClusterAnalysis();
               Map<String,Map<String,Set<String>>>Clustersize=new HashMap<String,Map<String,Set<String>>>();
                PrintWriter bw100 = null;
        try {
                  
            
                 bw100 = new PrintWriter(new FileWriter(new File("NetworkClusterConnectedness.txt")));
               Map<String,Set<String>>Cluster1= CA.clusters("outfileLTP1.8");
               Map<String,Set<String>>Cluster2= CA.clusters("outfileHEL1.8");
               Map<String,Set<String>>Cluster3= CA.clusters("outfileMG1.8");
               Map<String,Set<String>>Cluster4= CA.clusters("outfileMC1.8");
               Map<String,Set<String>>Cluster5= CA.clusters("outfileOMIM1.8");
               Map<String,Set<String>>Cluster6= CA.clusters("outfileUniprot1.8");
               
                 //  System.out.print(Cluster6+"\n");
               //System.out.print(Cluster1);
               
             
                    Clustersize.put("LTP_GS",Cluster1);
                    Clustersize.put("HEL_GS",Cluster2);
                    Clustersize.put("MG_GS",Cluster3);
                    Clustersize.put("MC_GS", Cluster4);
                    Clustersize.put("OMIM_GS",Cluster5);
                    Clustersize.put("UniProt_GS",Cluster6);
                    for(String i: AllNetworks.keySet()){
                        /*Map<String,Double> Average=CA.ClusterAverage(AllNetworks.get(i),Clustersize.get(i));
                        for(String g:Average.keySet()){
                            bw100.append(i+"\t"+g+"\t"+Average.get(g)+"\n");
                        }*/
                        
                        
                    }
  
  
        bw100.close();
                              }
        catch(Exception e){
           e.printStackTrace();

                           }   
                
            finally {
            if (bw100 != null) {
                try {
                    bw100.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
           
  }   
/*   public void clusterDistribution(Map<Pair,Double>network) throws IOException{
    
           ClusterAnalysis CA=new ClusterAnalysis();
            PrintWriter bw100 = null;
        try {
                  
            
                 bw100 = new PrintWriter(new FileWriter(new File("ClustersDistribution.txt")));
                  List<Double>inflation=new ArrayList<Double>();
                 //inflation.add(0.50);
            inflation.add(1.5);
          inflation.add(1.8);
           inflation.add(2.0);
            inflation.add(2.5);
            inflation.add(3.0);
             inflation.add(3.5);
             inflation.add(4.0);
            inflation.add(4.5);
            inflation.add(5.0);
            inflation.add(5.5);
            inflation.add(6.0);
            inflation.add(6.5);
            inflation.add(7.0);
            inflation.add(7.5);
                 /* for(float j=10;j<=25;j++){
                    Map<Pair,Double>IntegratedThreshald=new HashMap<Pair,Double>();
                    for(Pair p:network.keySet()){
                        if(network.get(p)>j){
                        IntegratedThreshald.put(p, network.get(p));
         }
                } 
                    for(double d:inflation){
                     //Map<String,Set<String>>Cluster= CA.clusters("outfilefewer"+j+"1.5");
                     //Map<String,Double> Average=CA.ClusterAverage(IntegratedThreshald, Cluster);
                     Map<String,Set<String>>Cluster= CA.clusters("outfileOverlap"+8+d);
                     Map<String,Double> Average=CA.ClusterAverage(network, Cluster);
                       for(String i : Average.keySet()){
                           //System.out.print(Average.get(i)+"\n");
                          bw100.println(d+"\t"+i + "\t"+ Cluster.get(i).size()+"\t"+Average.get(i)+"\t"+Cluster.size());
                      
                         }
                       //System.out.print(Average+"\n");
                  }
                       
                   bw100.close();
                              }
        catch(Exception e){
           e.printStackTrace();

                           }   
                
            finally {
            if (bw100 != null) {
                try {
                    bw100.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
                        
    
    }*/
         }
    
    
    

         