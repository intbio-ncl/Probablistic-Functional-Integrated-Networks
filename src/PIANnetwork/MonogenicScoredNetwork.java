/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIANnetwork;

import GoldStandard.goldstandard;
import GoldStandard.goldstandardparsing;
import Integration.IntegratedScores;
import Integration.Integrationlogger;
import LLS.Score;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import pfinnetwork.BuildingBioGrid;
import pfinnetwork.PFINNetWork;
import pfinnetwork.Triple;
import pfinnetwork.Pair;
import pfinnetwork.ParsingBioGrid;
import Parsing.ParsingMonogenic;

/**
 *
 * @author aoeshaalsobhe
 */
public class MonogenicScoredNetwork {
    public Map<Pair,Double> ProduceMonogenicScoredNetwork()throws IOException{
        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        BuildingBioGrid B = PFIN.buildbiodata(P,2);
        ParsingMonogenic Monogenic=new ParsingMonogenic();
        Map<String,Set<Pair>>datasets=B.getAlldatasets();
        Map<String,Set<Pair>>datasets2=B.getAlldatasets();
        System.out.print(datasets.keySet()+"\n");
        
        Set<Triple>monogenicdiseases=Monogenic.parseMonogenicDGA();
        System.out.print(monogenicdiseases.size()+"\n");
        Set<Pair>monogenicdiseases2=new HashSet<Pair>();
        for(Triple t2:monogenicdiseases){
            monogenicdiseases2.add(new Pair(t2.getD(),t2.getG()));
        }
        Set<String>ID=datasets.keySet();
        Set<String>ID2=new HashSet<String>();
        
        goldstandardparsing gs = new goldstandardparsing();
        Map<String,Set<Pair>>GS=new HashMap<String,Set<Pair>>();
        for(Triple t:monogenicdiseases){
           ID2.add(t.getPubmedID());
           if(datasets.containsKey(t.getPubmedID())){
               datasets2.remove(t.getPubmedID());
        }
        }
        int Over=0;
        for(String s:ID){
            if(ID2.contains(s)){
                Over++;
            }
        }
        System.out.print("size with overlap"+"\t"+Over+"\t"+datasets.size()+"\t"+"size without"+"\t"+datasets2.size());
        GS.put("GS",monogenicdiseases2);
        goldstandard gss =gs.ParsingGS(GS);
        Score S = new Score();
        Map<String,Double>LLSScore=S.ScoredData(gss, B);
         IntegratedScores IS = new IntegratedScores();
          Map<Pair,Double>IntegratedS=IS.doIntegration(LLSScore,LLSScore,Collections.<Double>reverseOrder(),B);
          Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("Monogeneic scored network.txt",IntegratedS);
        /* Map<Pair,Double>IntegratedThreshold=new HashMap<Pair,Double>();
          for(Pair p: IntegratedS.keySet()){
              if(IntegratedS.get(p)>38){
                  IntegratedThreshold.put(p,IntegratedS.get(p));
              
              }
          }
          //Network net = new Network();
         // Map<Pair,Double>IntegratedS=net.networkPurn();
           System.out.print("size of network"+"\t"+IntegratedS.size()+"\n");*/
           return IntegratedS;
           
}
    public Map<String,Double> MGLLS()throws IOException{
        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        BuildingBioGrid B = PFIN.buildbiodata(P,2);
        ParsingMonogenic Monogenic=new ParsingMonogenic();
        Map<String,Set<Pair>>datasets=B.getAlldatasets();
        Map<String,Set<Pair>>datasets2=B.getAlldatasets();
        System.out.print(datasets.keySet()+"\n");
        
        Set<Triple>monogenicdiseases=Monogenic.parseMonogenicDGA();
        System.out.print(monogenicdiseases+"\n");
        Set<Pair>monogenicdiseases2=new HashSet<Pair>();
        for(Triple t2:monogenicdiseases){
            monogenicdiseases2.add(new Pair(t2.getD(),t2.getG()));
        }
        Set<String>ID=datasets.keySet();
        Set<String>ID2=new HashSet<String>();
        
        goldstandardparsing gs = new goldstandardparsing();
        Map<String,Set<Pair>>GS=new HashMap<String,Set<Pair>>();
        for(Triple t:monogenicdiseases){
           ID2.add(t.getPubmedID());
           if(datasets.containsKey(t.getPubmedID())){
               datasets2.remove(t.getPubmedID());
        }
        }
        int Over=0;
        for(String s:ID){
            if(ID2.contains(s)){
                Over++;
            }
        }
        System.out.print("size with overlap"+"\t"+Over+"\t"+datasets.size()+"\t"+"size without"+"\t"+datasets2.size());
        GS.put("GS",monogenicdiseases2);
        goldstandard gss =gs.ParsingGS(GS);
        Score S = new Score();
        Map<String,Double>LLSScore=S.ScoredData(gss, B);
        
        return LLSScore;
}
     public void OverlapGSMG()throws IOException{
        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        BuildingBioGrid B = PFIN.buildbiodata(P,2);
        ParsingMonogenic Monogenic=new ParsingMonogenic();
        Map<String,Set<Pair>>datasets=B.getAlldatasets();
        Map<String,Set<Pair>>datasets2=B.getAlldatasets();
        System.out.print(datasets.keySet()+"\n");
        
        Set<Triple>monogenicdiseases=Monogenic.parseMonogenicDGA();
        System.out.print(monogenicdiseases.size()+"\n");
        Set<Pair>monogenicdiseases2=new HashSet<Pair>();
        for(Triple t2:monogenicdiseases){
            monogenicdiseases2.add(new Pair(t2.getD(),t2.getG()));
        }
        Set<String>ID=datasets.keySet();
        Set<String>ID2=new HashSet<String>();
        
        goldstandardparsing gs = new goldstandardparsing();
        Map<String,Set<Pair>>GS=new HashMap<String,Set<Pair>>();
        for(Triple t:monogenicdiseases){
           ID2.add(t.getPubmedID());
           if(datasets.containsKey(t.getPubmedID())){
               datasets2.remove(t.getPubmedID());
        }
        }
        int Over=0;
        for(String s:ID){
            if(ID2.contains(s)){
                Over++;
            }
        }
        System.out.print("size with overlap"+"\t"+Over+"\t"+datasets.size()+"\t"+"size without"+"\t"+datasets2.size());
        GS.put("GS",monogenicdiseases2);
        goldstandard gss =gs.ParsingGS(GS);
        double GOverlap=0.0;
        double DOverlap=0.0;
        double AssoOverlap=0.0;
        Set<String>OverlapedDiseases=new HashSet<String>();
         Set<String>OverlapedGenes=new HashSet<String>();
         Set<Pair>OverlapedAssociations=new HashSet<Pair>();
         Set<String>Diseases=new HashSet<String>();
         Set<String>Genes=new HashSet<String>();
         Set<Pair>Associations=new HashSet<Pair>();
         for(String ds: B.getAlldatasets().keySet()){
             for(Pair p: B.getAlldatasets().get(ds)){
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
           System.out.print("GDatasets"+"\t"+gss.getGenes().size()+"\n");
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
        System.out.print("\n Gene"+GOverlap+"\n");
        System.out.print(DOverlap+"\n");
        System.out.print(AssoOverlap+"\n");
    }
            
}