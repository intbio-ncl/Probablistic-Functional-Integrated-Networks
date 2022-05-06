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
         Map<String,List<BioGridList>>genepairbyid=new HashMap<String,List<BioGridList>>();
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
    
    
    public BuildingBioGrid buildbiodata2(ParsingBioGrid ds)throws IOException {
        Map<String,Set<Pair>>GS=new HashMap<String,Set<Pair>>();
         Map<String,Set<Pair>>Datasets=new HashMap<String,Set<Pair>>();
           Map<String,Set<Pair>>PMIDASS=new HashMap<String,Set<Pair>>();
            Map<String,List<BioGridList>>genepairbyid=new HashMap<String,List<BioGridList>>();
        Map<String,List<BioGridList>>genepairbyPMID=new HashMap<String,List<BioGridList>>();
        for (String Id : ds.getIds()) {
            genepairbyPMID.put(Id, new ArrayList<BioGridList>());
        }
       
        for (BioGridList e1 : ds.getLines()) {
            if(!e1.getPubmedID().equals("")){
            genepairbyPMID.get(e1.getPubmedID()).add(e1);
            }
        }
        for(String i:genepairbyPMID.keySet()){
            Set<Pair>DGAs=new HashSet<Pair>();
            for(BioGridList e2:genepairbyPMID.get(i)){
                Pair p=new Pair(e2.getD(),e2.getG());
                DGAs.add(p);
            }
              PMIDASS.put(i, DGAs);
        }
        Map<String,List<BioGridList>>genepairbysource=new HashMap<String,List<BioGridList>>();
        for (String source : ds.getSource()) {
            genepairbysource.put(source, new ArrayList<BioGridList>());
        }
       
        for (BioGridList e1 : ds.getLines()) {
            genepairbysource.get(e1.getSource()).add(e1);
            
        }
         System.out.print("alldatabysource"+genepairbysource.size()+"\n");
        List<String> list = Arrays.asList("BEFREE","CLINVAR","LHGDN","HPO","GWASDB","CTD_rat","CTD_mouse","MGD","RGD", "GWASCAT");
         
        genepairbysource.keySet().removeAll(list);
        System.out.print("curated source only"+genepairbysource.keySet()+"\n");
        Set<String>Curatedpmid=new HashSet<String>();
        for(String source: genepairbysource.keySet()){
            for(BioGridList r:genepairbysource.get(source)){
                   genepairbyid.put(r.getPubmedID(), new ArrayList<BioGridList>());
            
            }
        
        }
         for(String source: genepairbysource.keySet()){
            for(BioGridList r:genepairbysource.get(source)){
                 genepairbyid.get(r.getPubmedID()).add(r);
            }
         }
         Map<String,Set<Pair>>pmidbypair=new HashMap<String,Set<Pair>>();
         for (String id : genepairbyid.keySet()) {
             List<BioGridList>curatedsources=genepairbyid.get(id);
             Set<Pair> Pairs = new HashSet<Pair>();
              for (BioGridList e2 : curatedsources) {
                Pair P = new Pair(e2.getD(), e2.getG());
                Pairs.add(P);
      }
              pmidbypair.put(id,Pairs);
         }
        Map<String,Set<String>>MultiCurated=new HashMap<String,Set<String>>();
        
        for (String source : genepairbysource.keySet()) {
             List<BioGridList>curatedsources=genepairbysource.get(source);
             Set<String> PMIDs = new HashSet<String>();
              for (BioGridList e2 : curatedsources) {
                String PMID =  e2.getPubmedID();
                if(!PMID.equals("")){
                    PMIDs.add(PMID);
      }}
              System.out.print(source+"\t"+PMIDs.size()+"\n");
              MultiCurated.put(source, PMIDs);
      } 
        Set<String>Pubmed=new HashSet<String>();
        OverlapPubmedID overlapID=new OverlapPubmedID();
       Set<String>OverlappedPMIDs=new HashSet<String>();
          for(String source:MultiCurated.keySet()) {
              for(String id:MultiCurated.get(source)){
                  Pubmed.add(id);
              }
          }    
        Map<String,Set<String>>MultiPMIDs=overlapID.OverlapPubMed(MultiCurated);
        for(String s: MultiPMIDs.keySet()){
            for(String study:MultiPMIDs.get(s)){
                 OverlappedPMIDs.add(study);
        
        }
        }
        System.out.print("Overlap Studies"+OverlappedPMIDs.size()+"\n");
     
         
            for(String pmid:OverlappedPMIDs){
                if(pmidbypair.keySet().contains(pmid)){
                    GS.put(pmid,pmidbypair.get(pmid));
                
                }
            
            
            }
            System.out.print("ID"+GS.size());
           
            
           
                for(String k : pmidbypair.keySet()){
                    if(Pubmed.contains(k)&&!OverlappedPMIDs.contains(k))
                    Datasets.put(k,pmidbypair.get(k));
                
                }
                System.out.print("\n"+Datasets.size());
            Set<Pair>DGAS=new HashSet<Pair>();
            for(String r :Datasets.keySet())
            {
                for(Pair p:Datasets.get(r)){
               DGAS.add(p);
                }
            }
        Set<String>geneset=new HashSet<String>();
        Set<String>diseaseset=new HashSet<String>();
        for(Pair pair:DGAS){
            geneset.add(pair.getG());
            diseaseset.add(pair.getD());
        }
       Map<String,Map<Pair,String>>asso=new HashMap<String,Map<Pair,String>>();
      return new BuildingBioGrid(Datasets,geneset,diseaseset,DGAS,asso,GS,GS);  
}
    
    public Map<Pair,Double>producenetwork()throws IOException{
        
        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        OverlapedDataScoredDatasets OVerlap=new OverlapedDataScoredDatasets();
        BuildingBioGrid B = OVerlap.buildbiodata2(P);
        PFIN.writetoFile(B) ;
       // PFIN.writetoFile2(B);
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
          }*/
           Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("integratednetworkoverlapGS.txt",IntegratedS);
    
         System.out.print("Hi Aoesha "+IntegratedS.size());
    
    return IntegratedS;

      


}
     public Map<String,Double>MCLLS()throws IOException{
        
        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        OverlapedDataScoredDatasets OVerlap=new OverlapedDataScoredDatasets();
        BuildingBioGrid B = OVerlap.buildbiodata2(P);
        PFIN.writetoFile(B) ;
       // PFIN.writetoFile2(B);
       goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(B.getGS());
         Score S = new Score();
         LTPSCores LTP = new LTPSCores();
           Map<String,Double>LLSScore=S.ScoredData(gss, B);

    return LLSScore;
}
      public void OverlapGSMC()throws IOException{
        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        OverlapedDataScoredDatasets OVerlap=new OverlapedDataScoredDatasets();
        BuildingBioGrid B = OVerlap.buildbiodata2(P);
        PFIN.writetoFile(B) ;
       // PFIN.writetoFile2(B);
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
        System.out.print("\n"+GOverlap+"\n");
        System.out.print(DOverlap+"\n");
        System.out.print(AssoOverlap+"\n");
    }
}