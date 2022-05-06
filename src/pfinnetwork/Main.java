
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfinnetwork;

import GoldStandard.goldstandard;
import GoldStandard.goldstandardparsing;
import Integration.IntegratedScores;
//import Integration.*;
import LLS.LTPSCores;
import LLS.*;
import Prediction.NetworkPrediction;
import java.io.*;
import java.util.Collections;
import java.util.Map;
import Prediction.*;
import java.util.*;
import DiseaseNetwork.*;


/**
 *
 * @author aoeshagaedmalsobhe
 */
public class Main {
    public static void main(String[] args) throws IOException {
      

/* String filename = "allgenediseasepmidassociations.tsv";

       PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata(filename);
        BuildingBioGrid B = PFIN.buildbiodata(P);
        PFIN.writetoFile(B) ;
        PFIN.writetoFile2(B);
       goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(B.getGS());
         Score S = new Score();
         LTPSCores LTP = new LTPSCores();
           Map<String,Double>LLSScore=S.ScoredData(gss, B);
           Map<String,Double>LLSScore2=LTP.LTPScore(B);
           LLSScore.putAll(LLSScore2);
       IntegratedScores IS = new IntegratedScores();
          Map<Pair,Double>IntegratedS=IS.doIntegration(LLSScore,LLSScore,Collections.<Double>reverseOrder(),B);
          Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("integrated.txt",IntegratedS);
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
           //Integrationlogger ILogger = new Integrationlogger();
         //ILogger.logToFile("integrated.txt",IntegratedS);
         
          /* DValue Dvalue = new DValue();
             Dvalue.run(gss);
             NodesDistribution N= new NodesDistribution();
             N.NodesDistribution1();
              N.NodesDistribution2();
             /* ClusterAnalysis Cl = new ClusterAnalysis();
              Map<String, Set<String>>Cluster=Cl.clusters();
              Cl.run(IntegratedS,Cluster);
             TenfoldCrossValidation CV=new TenfoldCrossValidation();
             CV.crossvalidation(IntegratedS);
          
       
           
         
  
                 
             
            
      /*DiseaseNetwork DN = new DiseaseNetwork();
      Map<Pair,Set<Pair>>Evidence=DN.DiseaseNet(IntegratedS);
       System.out.print(Evidence.size()+"\n");
        Map<Pair, Double>FinalDD=DN.doIntegration(LLSScore,LLSScore,Collections.<Double>reverseOrder(),B,Evidence);
       Integrationlogger ILogger2 = new Integrationlogger();
       ILogger.logToFile("integratedD-DPFINS.txt",FinalDD);*/
     // NodesDistribution N= new NodesDistribution();
     // N.NodesDistribution1();
         //N.NodesDistribution2();
        
   
    
    /*SharedGenes SD=new SharedGenes();
     Parsing Parse=new Parsing();
     HashMap<Pair,Set<String>>commongenes=Parse.CommonGenes();
     Map<String,Set<String>>SharedG=SD.sharedgenes("outProbabilistic30",commongenes);
     /*Network nx=new Network();
       
        HashMap<Pair,Double>network=nx.networkPurn();
         RandomNetwork RN = new RandomNetwork();
         System.out.print(network.size()+"\n");
          RN.CreateRN(network);
         SharedGenes SG = new SharedGenes();
          SG.RandomNetworksharedgenes();*/
        Parsing Parse=new Parsing();
        HashMap<Pair,Integer>CDrugs=Parse.CommonDrugs();
        HashMap<String,Set<String>>AllDrugs=Parse.DiseaseDrugs();
        DrugSimilarityJaccardIndex  GSJC=new DrugSimilarityJaccardIndex ();
        double JaccardAcerage=GSJC.JaccardDrugSimilarity("outProbabilistic30",CDrugs,AllDrugs);
        //DiseaseSemanticSimilarityPIDSN DSS=new DiseaseSemanticSimilarityPIDSN();
        //DSS.diseasesemanticsimilarity();
        /*Parsing Parse=new Parsing();
        HashMap<Pair,Set<String>>CGenes=Parse.CommonGenes();
        SharedGenes SD=new SharedGenes();
        SD.sharedgenes("outProbabilistic30",CGenes);
        HashMap<String,Set<String>>AllGenes=Parse.DiseasesGenes();
        GeneticSimilarityJaccardIndex  GSJC=new GeneticSimilarityJaccardIndex ();
        double JaccardAcerage=GSJC.JaccardSimilarity("outProbabilistic30",CGenes,AllGenes);*/
          }
}
