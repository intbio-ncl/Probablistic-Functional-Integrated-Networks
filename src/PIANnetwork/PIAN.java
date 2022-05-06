
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PIANnetwork;
import pfinnetwork.*;
import GoldStandard.goldstandard;
import GoldStandard.goldstandardparsing;
import Integration.IntegratedScores;
import Integration.*;
import LLS.LTPSCores;
import LLS.*;
import Prediction.NetworkPrediction;
import java.io.*;
import java.util.Collections;
import java.util.Map;
import Prediction.*;
import java.util.Collections;
import java.util.*;
import DiseaseNetwork.*;


/**
 *
 * @author aoeshagaedmalsobhe
 */
public class PIAN {


public Map<Pair,Double> producenetwork()throws IOException{
    Map<Pair,Double>IntegratedS=new HashMap<Pair,Double>();
       /* PrintWriter out = null;
        try {
            String outFileName = "ThresholdAnalysis.txt";
            out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));*/
       PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
       /* List<Integer>index=new ArrayList<Integer>();
        
       // index.add(1);
        index.add(2);
       /* index.add(5);
        index.add(8);
        index.add(9);
        index.add(10);
        index.add(15);
        index.add(20);
        index.add(25);
        index.add(50);
        index.add(75);
        index.add(100);*/
       /* Map<Integer,Map<String,Double>>ALLLLS=new HashMap<Integer,Map<String,Double>>();
        Map<Integer,Map<Pair,Double>>ALLNetworks=new HashMap<Integer,Map<Pair,Double>>();
        System.out.print(index+"\n");
        Collections.sort(index);
        System.out.print(index);
        for(int i:index){*/
            BuildingBioGrid B = PFIN.buildbiodata(P,1);
           /* Set<Pair>DSPair=new HashSet<Pair>();
             Set<String>DSDiseases=new HashSet<String>();
             Set<String>DSGenes=new HashSet<String>();
             for(String s:B.getBio().keySet()){
            for(Pair p:B.getBio().get(s)){
              DSPair.add(p);
           }}
             for(Pair p:DSPair){
              DSGenes.add(p.getG());
              DSDiseases.add(p.getD());
           }
            Set<Pair>GSPair=new HashSet<Pair>();
            Set<String>GSGenes=new HashSet<String>();
            Set<String>GSDiseases=new HashSet<String>();
             
       
        for(String s:B.getGS().keySet()){
            for(Pair p:B.getGS().get(s)){
              GSPair.add(p);
           }}
        
         for(Pair p:GSPair){
              GSGenes.add(p.getG());
              GSDiseases.add(p.getD());
           }
         
        double LTP=(double)B.getGS().size()/B.getAlldatasets().size()*100;
        double HTP=(double)B.getBio().size()/B.getAlldatasets().size()*100;
         double LTPGenes=(double)GSGenes.size()/B.getAllgenes().size()*100;
          double LTPDiseases=(double)GSDiseases.size()/B.getAlldiseases().size()*100;
          double LTPAssociations=(double)GSPair.size()/B.getAllPairs().size()*100;
          double HTPDiseases=(double)DSDiseases.size()/B.getAlldiseases().size()*100;
          double HTPGenes=(double)DSGenes.size()/B.getAllgenes().size()*100;
          double HTPAssociations=(double)DSPair.size()/B.getAllPairs().size()*100;
         
          // BuildingBioGrid B = PFIN.buildbiodata(P,i);
        PFIN.writetoFile(B) ;
        PFIN.writetoFileDS(B);
        PFIN.writetoFileNodeAttributes(B);*/
       goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(B.getGS());
         Score S = new Score();
         LTPSCores LTPS = new LTPSCores();
           Map<String,Double>LLSScore=S.ScoredData(gss, B);
          Map<String,Double>LLSScore2=LTPS.LTPScore(B);
         /* ALLLLS.put(i, LLSScore);
          //LLSScore.putAll(LLSScore2);*/
          
       IntegratedScores IS = new IntegratedScores();
          IntegratedS=IS.doIntegration(LLSScore,LLSScore,Collections.<Double>reverseOrder(),B);
         // ALLNetworks.put(i, IntegratedS);
          Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("integratedneLTP"+2+".txt",IntegratedS);
          //Network net = new Network();
         // Map<Pair,Double>IntegratedS=net.networkPurn();
           System.out.print("size of network"+"\t"+IntegratedS.size()+"\n");
        
      
          Set<String>genes=new HashSet<String>();
          Set<String>diseases=new HashSet<String>();
           for(Pair p:IntegratedS.keySet()){
              genes.add(p.getG());
              diseases.add(p.getD());
           }
           System.out.print("genes"+genes.size()+"\n"+"diseases"+"\t"+diseases.size()+"\n");
          /* double genecoverage=(double)genes.size()/DSGenes.size()*100;
           double datasets=(double)LLSScore.size()/B.getBio().size()*100;
            double diseasecoverage=(double)diseases.size()/DSDiseases.size()*100;
             double associationcoverage=(double)IntegratedS.size()/DSPair.size()*100;
             int lost=B.getBio().size()-LLSScore.size();
             double percentage=(double)lost/B.getBio().size()*100;
            // out.append(i+"\t"+B.getBio().size()+"\t"+lost+"\t"+percentage+"\n");
             ClusterAnalysis CA=new ClusterAnalysis();
              Map<String,Set<String>>Cluster= CA.clusters("outfileLTH"+i+",");
              double Average=CA.ClusterAverage(IntegratedS, Cluster);
          // out.append(i+"\t"+IntegratedS.size()+"\t"+datasets+"\t"+genecoverage+"\t"+diseasecoverage+"\t"+associationcoverage+ "\n") ;
             out.append(i+"\t"+Average+"\n");
        
             for(int u:ALLNetworks.keySet()){
                for(Pair j:ALLNetworks.get(u).keySet()){
                    //out.append(i+"\t"+j+"\t"+ALLNetworks.get(i).get(j)+"\n");
                }
             
             }
        }}
          finally {
            if (out != null) {
                out.close();
                
            }
        }
           //Integrationlogger ILogger = new Integrationlogger();
         //ILogger.logToFile("integrated.txt",IntegratedS);
         
        
        
            /*DValue Dvalue = new DValue();
             Dvalue.run(gss);
             NodesDistribution N= new NodesDistribution();
             N.NodesDistribution1();
              N.NodesDistribution2();
             ClusterAnalysis Cl = new ClusterAnalysis();
              Map<String, Set<String>>Cluster=Cl.clusters();
              Cl.run(IntegratedS,Cluster);*/
            /* Map<Pair,Double>IntegratedS2=new HashMap<Pair,Double>();
             for(Pair p2:IntegratedS.keySet()){
        
            if(IntegratedS.get(p2)>19){
            
                IntegratedS2.put(p2, IntegratedS.get(p2));
            
            }
        }
             
             System.out.print(IntegratedS2.size()+"\n");*/
             //TenfoldCrossValidation CV=new TenfoldCrossValidation();
             //CV.crossvalidation(IntegratedS2);









return IntegratedS;
}


public Map<String,Double>LTPLLS()throws IOException{
       
       PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        BuildingBioGrid B = PFIN.buildbiodata(P,2);
        PFIN.writetoFile(B) ;
        PFIN.writetoFileDS(B);
        PFIN.writetoFileNodeAttributes(B);
       goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(B.getGS());
         Score S = new Score();
         LTPSCores LTP = new LTPSCores();
           Map<String,Double>LLSScore=S.ScoredData(gss, B);
           
           return LLSScore;

}
 public void OverlapGSLTP()throws IOException{
        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        BuildingBioGrid B = PFIN.buildbiodata(P,2);
        PFIN.writetoFile(B) ;
        PFIN.writetoFileDS(B);
        PFIN.writetoFileNodeAttributes(B);
       goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(B.getGS());
        double GOverlap=0.0;
        double DOverlap=0.0;
        double AssoOverlap=0.0;
        Set<String>OverlapedDiseases=new HashSet<String>();
         Set<String>OverlapedGenes=new HashSet<String>();
         Set<Pair>OverlapedAssociations=new HashSet<Pair>();
         Set<String>Diseases=new HashSet<String>();
         Set<String>Genes=new HashSet<String>();
         Set<Pair>Associations=new HashSet<Pair>();
         for(String ds: B.getBio().keySet()){
             for(Pair p: B.getBio().get(ds)){
                 Associations.add(p);
                 Diseases.add(p.getD());
                 Genes.add(p.getG());
             
             }
         
         }
         for(Pair p2: Associations){
            if(gss.getPositive().contains(p2)){
               OverlapedAssociations.add(p2);
            }
         
         
         }
       for(String G: Genes){
            if(gss.getGenes().contains(G)){
               OverlapedGenes.add(G);
            }
         
         
         }
       for(String D: Diseases){
            if(gss.getDiseases().contains(D)){
               OverlapedDiseases.add(D);
            }
         
         
         }
        System.out.print("GDatasets"+"\t"+Genes.size()+"\n");
        System.out.print("DDatasets"+"\t"+Diseases.size());
         System.out.print("AssoDatasets"+"\t"+Associations.size()+"\n");
          System.out.print("DGS"+"\t"+gss.getDiseases().size()+"\n");
           System.out.print("GGS"+"\t"+gss.getGenes().size()+"\n");
            System.out.print("GSAssociations"+"\t"+gss.getPositive().size()+"\n");
       int x=OverlapedGenes.size();
       int y=Genes.size();
       GOverlap=(double)x/y*100;
       int x1=OverlapedDiseases.size();
       int y1=Diseases.size();
       DOverlap=(double)x1/y1*100;
       int x2=OverlapedAssociations.size();
       int y2=Associations.size();
       DOverlap=(double)x1/y1*100;
      // DOverlap=OverlapedDiseases.size()/Diseases.size();
       AssoOverlap=(double)x2/y2*100;
        System.out.print("\n"+GOverlap+"\n");
        System.out.print(DOverlap+"\n");
        System.out.print(AssoOverlap+"\n");
    }

}
