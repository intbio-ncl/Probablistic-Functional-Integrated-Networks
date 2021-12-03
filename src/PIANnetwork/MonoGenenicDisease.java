/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIANnetwork;

import java.io.IOException;
import java.util.*;
import pfinnetwork.*;
import DiseaseNetwork.*;
import GoldStandard.goldstandard;
import GoldStandard.goldstandardparsing;
import Integration.IntegratedScores;
import Integration.Integrationlogger;
import LLS.LTPSCores;
import LLS.Score;

/**
 *
 * @author aoeshaalsobhe
 */
public class MonoGenenicDisease {
    public BuildingBioGrid buildbiodata(ParsingBioGrid ds)throws IOException {
        Set<Pair>diseasegeneasso=new HashSet<Pair>();
        Set<String>monogenicdiseases=new HashSet<String>();
        Map<String,Map<Pair,String>>f=new HashMap<String,Map<Pair,String>>();
        Map<String,List<BioGridList>>genepairbysource=new HashMap<String,List<BioGridList>>();
        Map<String,Set<Pair>>GS=new HashMap<String,Set<Pair>>();
        Set<Pair>monogenicdisorder=new HashSet<Pair>();
        Map<String,Set<Pair>>datasets=new HashMap<String,Set<Pair>>();
        for (String source : ds.getSource()) {
            genepairbysource.put(source, new ArrayList<BioGridList>());
        }
       
        for (BioGridList e1 : ds.getLines()) {
            genepairbysource.get(e1.getSource()).add(e1);
            
        }
        List<String> list = Arrays.asList("BEFREE","CLINVAR","LHGDN","HPO","GWASDB","CTD_rat","CTD_mouse","MGD","RGD", "GWASCAT");
         
      genepairbysource.keySet().removeAll(list);
       Map<String,Set<Triple>>CuratedOverlap=new HashMap<String,Set<Triple>>();
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
      OverlapPubmedID overlapID=new OverlapPubmedID();
      Map<String,Set<Triple>>OverlappedDatasets=overlapID.Overlap(CuratedOverlap);
      for(String i:OverlappedDatasets.keySet()){
          System.out.print(i+"\t"+OverlappedDatasets.get(i).size()+"\n");
      
      }
      Map<String,Set<Triple>>removedoverlapdatasets=overlapID.RemoveOverlap(CuratedOverlap, OverlappedDatasets);
        for(String r:removedoverlapdatasets.keySet()){
            Set<Pair>assoc=new HashSet<Pair>();
            for(Triple bio : removedoverlapdatasets.get(r)) {
                diseasegeneasso.add(new Pair(bio.getD(),bio.getG()));
                assoc.add(new Pair(bio.getD(),bio.getG()));
    }
                 datasets.put(r, assoc);
        }
            NodesDistribution ND=new NodesDistribution();
            NodeDegree nd= ND.NodesDistributionn(diseasegeneasso);
            for(String d: nd.getDiseaseDegree().keySet()){
                if(nd.getDiseaseDegree().get(d)==1){
                    monogenicdiseases.add(d);
                }
            }
            
            for(String d: monogenicdiseases){
                for(Pair p:diseasegeneasso){
                    if(p.getD().equals(d)){
                       monogenicdisorder.add(p);  
                    }
                }
            }
           /* for(String s: datasets.keySet()){
               for(Pair p:monogenicdisorder){
                   if(datasets.get(s).contains(p)){
                      datasets.get(s).remove(p);
                   
                   }
               }
            }*/
            Set<String>Allgenes=new HashSet<String>();
            Set<String>Alldiseases=new HashSet<String>();
            for(Pair p:diseasegeneasso){
              Allgenes.add(p.getG());
              Alldiseases.add(p.getD());
            
            }
            System.out.print("monogeneic diseases"+monogenicdiseases.size()+"\n");
            System.out.print("network"+"\t"+diseasegeneasso.size()+"\t"+monogenicdisorder.size());
       GS.put("GS", monogenicdisorder);
       return new BuildingBioGrid(datasets,Allgenes,Alldiseases,diseasegeneasso,f,GS,GS);
}
    public Map<Pair,Double>producenetwork()throws IOException{
        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        MonoGenenicDisease MG=new MonoGenenicDisease();
        BuildingBioGrid B = MG.buildbiodata(P);
        PFIN.writetoFile(B) ;
        PFIN.writetoFile2(B);
        
        
        goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(B.getGS()
        );
         Score S = new Score();
         LTPSCores LTP = new LTPSCores();
           Map<String,Double>LLSScore=S.ScoredData(gss, B);
         // Map<String,Double>LLSScore2=LTP.LTPScore(B);
          //LLSScore.putAll(LLSScore2);
       IntegratedScores IS = new IntegratedScores();
          Map<Pair,Double>IntegratedS=IS.doIntegration(LLSScore,LLSScore,Collections.<Double>reverseOrder(),B);
          Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("integratednetwork.txt",IntegratedS);
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
          
return IntegratedS;
    
}
}
