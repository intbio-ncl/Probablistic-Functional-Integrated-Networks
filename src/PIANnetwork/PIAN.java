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
import java.util.*;
import DiseaseNetwork.*;


/**
 *
 * @author aoeshagaedmalsobhe
 */
public class PIAN {


public Map<Pair,Double>producenetwork()throws IOException{
       
       PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        BuildingBioGrid B = PFIN.buildbiodata(P);
        PFIN.writetoFile(B) ;
        PFIN.writetoFile2(B);
       goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(B.getGS());
         Score S = new Score();
         LTPSCores LTP = new LTPSCores();
           Map<String,Double>LLSScore=S.ScoredData(gss, B);
          Map<String,Double>LLSScore2=LTP.LTPScore(B);
          //LLSScore.putAll(LLSScore2);
       IntegratedScores IS = new IntegratedScores();
          Map<Pair,Double>IntegratedS=IS.doIntegration(LLSScore,LLSScore,Collections.<Double>reverseOrder(),B);
          Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("integratednetworfewerassociation.txt",IntegratedS);
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

}
