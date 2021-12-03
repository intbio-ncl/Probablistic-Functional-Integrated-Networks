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
import LLS.LTPSCores;
import LLS.Score;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import pfinnetwork.BioGridList;
import pfinnetwork.BuildingBioGrid;
import pfinnetwork.OverlapPubmedID;
import pfinnetwork.ParsingBioGrid;
import pfinnetwork.*;

/**
 *
 * @author aoeshaalsobhe
 */
public class OverlapedDataScoredDatasets {
    public BuildingBioGrid buildbiodata(ParsingBioGrid ds)throws IOException {
        Map<String,Map<Pair,String>>asso=new HashMap<String,Map<Pair,String>>();
        Map<String,List<BioGridList>>genepairbysource=new HashMap<String,List<BioGridList>>();
        for (String source : ds.getSource()) {
            genepairbysource.put(source, new ArrayList<BioGridList>());
        }
       
        for (BioGridList e1 : ds.getLines()) {
            genepairbysource.get(e1.getSource()).add(e1);
            
        }
         //System.out.print("alldatabysource"+genepairbysource.size()+"\n");
        List<String> list = Arrays.asList("BEFREE","CLINVAR","LHGDN","HPO","GWASDB","CTD_rat","CTD_mouse","MGD","RGD", "GWASCAT");
         
        genepairbysource.keySet().removeAll(list);
        //System.out.print("curated source only"+genepairbysource.keySet()+"\n");
        Map<String,Set<Triple>>CuratedOverlap=new HashMap<String,Set<Triple>>();
        Map<String,Set<Pair>>CuratedOverlappair=new HashMap<String,Set<Pair>>();
        for (String source : genepairbysource.keySet()) {
             List<BioGridList>curatedsources=genepairbysource.get(source);
             Set<Triple> Triples = new HashSet<Triple>();
              for (BioGridList e2 : curatedsources) {
                Triple T = new Triple(e2.getD(), e2.getG(), e2.getPubmedID());
                Triples.add(T);
      }
              System.out.print(source+"\t"+Triples.size()+"\n");
              CuratedOverlap.put(source, Triples);
      } 
        for(String j:CuratedOverlap.keySet()){
           Set<Pair>Pair=new HashSet<Pair>();
           for(Triple t: CuratedOverlap.get(j)){
             Pair.add(new Pair(t.getD(),t.getG()));
        
        }
           CuratedOverlappair.put(j, Pair);
        } 
        OverlapPubmedID overlapID=new OverlapPubmedID();
        Map<String,Set<Pair>>GS=new HashMap<String,Set<Pair>>();
        Map<String,Set<Pair>>removedoverlap=new HashMap<String,Set<Pair>>();
        Map<String,Set<Triple>>OverlappedDatasets=overlapID.Overlap(CuratedOverlap);
        Set<Triple>OverlapTriple=new HashSet<Triple>();
        Set<Pair>overlapped=new HashSet<Pair>();
        for(String i:OverlappedDatasets.keySet()){
            Set<Pair>gs=new HashSet<Pair>();
          for(Triple t: OverlappedDatasets.get(i)){
              gs.add(new Pair(t.getD(),t.getG()));
              OverlapTriple.add(t);
              overlapped.add(new Pair(t.getD(),t.getG()));
          }
                GS.put(i,gs);
          
          
          
          }
        System.out.print("Overlap Studies"+OverlapTriple.size()+"\n");
        System.out.print("Overlap association"+overlapped.size()+"\n");
        Set<Pair>Edges=new HashSet<Pair>();
        for(String o: GS.keySet()){
            for(Pair p:GS.get(o)){
                Edges.add(p);
            }
        }
        System.out.print(Edges.size());
        
            for(String s: CuratedOverlap.keySet()){
                for(Triple t:OverlapTriple){
                if(CuratedOverlap.get(s).contains(t)){
                    CuratedOverlap.get(s).remove(t);
                }
                    
                }
        for(String c:CuratedOverlap.keySet()){
            Set<Pair>edges=new HashSet<Pair>();
            for(Triple t:CuratedOverlap.get(c)){
                edges.add(new Pair(t.getD(),t.getG()));
            }
            removedoverlap.put(c,edges);
        }
                 
        
        
        }
        Set<String>geneset=new HashSet<String>();
        Set<String>diseaseset=new HashSet<String>();
        Set<Pair>diseasegene=new HashSet<Pair>();
        for(String s:removedoverlap.keySet()){
            for(Pair p:removedoverlap.get(s)){
                geneset.add(p.getG());
                diseaseset.add(p.getG());
                diseasegene.add(p);
            }
      
      }
      return new BuildingBioGrid(removedoverlap,geneset,diseaseset,diseasegene,asso,GS,GS);  
}
    public Map<Pair,Double>producenetwork()throws IOException{
        
        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        OverlapedDataScoredDatasets OVerlap=new OverlapedDataScoredDatasets();
        BuildingBioGrid B = OVerlap.buildbiodata(P);
        PFIN.writetoFile(B) ;
        PFIN.writetoFile2(B);
       goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(B.getGS());
         Score S = new Score();
         LTPSCores LTP = new LTPSCores();
           Map<String,Double>LLSScore=S.ScoredData(gss, B);
         // Map<String,Double>LLSScore2=LTP.LTPScore(B);
          //LLSScore.putAll(LLSScore2);
       IntegratedScores IS = new IntegratedScores();
          Map<Pair,Double>IntegratedS=IS.doIntegration(LLSScore,LLSScore,Collections.<Double>reverseOrder(),B);
         // Integrationlogger ILogger = new Integrationlogger();
        // ILogger.logToFile("integratednetworkoverlapGS.txt",IntegratedS);
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
           
       /* Map<Pair,Double>IntegratedThreshold=new HashMap<Pair,Double>();
          for(Pair p: IntegratedS.keySet()){
              if(IntegratedS.get(p)>16){
                  IntegratedThreshold.put(p,IntegratedS.get(p));
              
              }
          }
           Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("integratednetworkoverlapGS.txt",IntegratedThreshold);
    
         System.out.print("Hi Aoesha "+IntegratedThreshold.size());*/
    
    return IntegratedS;

      


}

    
}
