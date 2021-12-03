/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HistoricalEvaluation;

import GoldStandard.goldstandard;
import GoldStandard.goldstandardparsing;
import Integration.IntegratedScores;
import Integration.Integrationlogger;
import LLS.LTPSCores;
import LLS.Score;
import Prediction.NetworkPrediction;
import Prediction.Validation;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pfinnetwork.BuildingBioGrid;
import pfinnetwork.PFINNetWork;
import pfinnetwork.Pair;
import pfinnetwork.ParsingBioGrid;
import pfinnetwork.Triple;

/**
 *
 * @author aoeshaalsobhe
 */
public class Prediction {
    public void predict()throws IOException{
     
             DisGeNET2018 disgenet=new DisGeNET2018();
             ParsingBioGrid PDG=disgenet.parsedisgenet2020();
             BuildingBioGrid BuildDisGENET=disgenet.buildbiodata(PDG);  
            goldstandardparsing gs = new goldstandardparsing();
            goldstandard gss =gs.ParsingGS(BuildDisGENET.getGS());
            System.out.print("sources:"+PDG.getSource()+"\n");
            System.out.print("PubmedID:"+PDG.getIds().size()+"\n");
            System.out.print("HTP size"+BuildDisGENET.getBio().size()+"\n");
            System.out.print("LTP size"+BuildDisGENET.getGS().size()+"\n");
            disgenet.writetoFile(BuildDisGENET);
            disgenet.writetoFile2(BuildDisGENET);
            Score S = new Score();
            LTPSCores LTP = new LTPSCores();
            Map<String,Double>LLSScore=S.ScoredData(gss,BuildDisGENET);
           
            IntegratedScores IS = new IntegratedScores();
            Map<Pair,Double>IntegratedS=IS.doIntegration(LLSScore,LLSScore,Collections.<Double>reverseOrder(),BuildDisGENET);
          Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("integrated.txt",IntegratedS);
         ParsingBioGrid PDG2=disgenet.parsedisgenet2020();
         Map<Pair,Double>network=new HashMap<Pair,Double>();
             BuildingBioGrid BuildDisGENET2=disgenet.buildbiodata(PDG2);
             for(String i:BuildDisGENET2.getBio().keySet()){
                 for(Pair p:BuildDisGENET2.getBio().get(i)){
                     network.put(p,1.00);
                 
                 
                 }
             
             }
             Map<Pair,Double>IntegratedS2=new HashMap<Pair,Double>();
          NetworkPrediction NP = new NetworkPrediction();
        for(Pair p2:IntegratedS.keySet()){
        
            if(IntegratedS.get(p2)>30){
            
                IntegratedS2.put(p2, IntegratedS.get(p2));
            
            }
        }
       Map<String,Set<Triple>>GeneNeighbour=NP.GNeighbours(IntegratedS2);
       System.out.print("network size"+"\t"+IntegratedS2.size()+"\n");
        Map<String,Set<String>>DNeighbour=NP.DNeighbours(IntegratedS2);
       
        Map<Pair,Set<String>>Common=NP.CommonNeighbours(IntegratedS2,DNeighbour, GeneNeighbour);
        System.out.print("common pair"+Common.size()+"\n");
       Map<Pair,Double>Predicatedassociation=NP.Predict(IntegratedS2,GeneNeighbour,DNeighbour,Common);
         NP.writetoFilePrediction(Predicatedassociation);
   
         Validation vd = new Validation();
         List<Double>AUC=vd.NetworkValidat(network,0);
           
    }
        
}
