/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LLS;

import PIANnetwork.UniprotscoredPIAN;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pfinnetwork.BioGridList;
import pfinnetwork.OverlapPubmedID;
import pfinnetwork.*;
import pfinnetwork.ParsingBioGrid;
import pfinnetwork.Triple;
import PIANnetwork.*;

/**
 *
 * @author aoeshaalsobhe
 */
public class GDAScore {
    public Map<Pair,Double> calculateGDAScore(ParsingBioGrid ds)throws IOException{
        Map<String,Set<Pair>>Datasource=new HashMap<String,Set<Pair>>();
        Map<String,Set<Triple>>Datasourcepubmid=new HashMap<String,Set<Triple>>();
        Map<String,Map<String,Set<Pair>>>AllDataSources=new HashMap<String,Map<String,Set<Pair>>>();
        Map<String,Map<String,Set<Triple>>>AllDataSourcespubmid=new HashMap<String,Map<String,Set<Triple>>>();
        Map<String,Set<Pair>>CuratedData=new HashMap<String,Set<Pair>>();
        Map<String,Set<Pair>>ModelingData=new HashMap<String,Set<Pair>>();
        Map<String,Set<Pair>>InferedData=new HashMap<String,Set<Pair>>();
        Map<String,Set<Triple>>CuratedDatapubmid=new HashMap<String,Set<Triple>>();
        Map<String,Set<Triple>>ModelingDatapubmid=new HashMap<String,Set<Triple>>();
        Map<String,Set<Triple>>InferedDatapubmid=new HashMap<String,Set<Triple>>();
        Map<String,Set<Pair>>LiteratureData=new HashMap<String,Set<Pair>>();
        Map<String,List<BioGridList>>Datasources=new HashMap<String,List<BioGridList>>();
        Set<Pair>DGA=new HashSet<Pair>();
        Set<Triple>DGS=new HashSet<Triple>();
        //Set<Pair>Literature=new HashSet<Pair>();
        List<Triple>Literature=new ArrayList<Triple>();
        Map<Pair,Double>DGAScore=new HashMap<Pair,Double>();
        Map<Pair,Double>DGAScoreRedundancy=new HashMap<Pair,Double>();
        List<String> Curated = Arrays.asList("CGI", "CLINGEN", "GENOMICS_ENGLAND", "CTD_human", "PSYGENET", "ORPHANET", "UNIPROT");
        List<String> AnimalModels = Arrays.asList("MGD","RGD","CTD_mouse","CTD_rat");
        List<String> INFERRED = Arrays.asList("HPO","CLINVAR","GWASCAT","GWASDB");
            for (String source : ds.getSource()) {
                Datasources.put(source, new ArrayList<BioGridList>());
               }
       
           for (BioGridList e1 : ds.getLines()) {
                Datasources.get(e1.getSource()).add(e1);
            
             }
            for(BioGridList e2 : ds.getLines()){
               // DGS.add(new Triple(e2.getD(),e2.getG(),e2.getGDAscore()));
                DGA.add(new Pair(e2.getD(),e2.getG()));
                DGAScore.put(new Pair(e2.getD(),e2.getG()),e2.getGDAscore());
                
            }
           /* System.out.print(Datasources.keySet());
               for(BioGridList pub:Datasources.get("BEFREE")){
                    Literature.add(new Triple(pub.getD(),pub.getG(),pub.getPubmedID()));
                   
           }   
               //System.out.print(Datasources.get("BEFREE").size()+"\n");
               //System.out.print(Datasources.get("LHGDN").size()+"\n");
                for(BioGridList pub2:Datasources.get("LHGDN")){
                    Literature.add(new Triple(pub2.getD(),pub2.getG(),pub2.getPubmedID()));
                   
           }   
                //System.out.print("\n"+Literature.size()+"\n");
                //System.out.print("\n"+Literature.size()+"\n");
               for(String s: Datasources.keySet()){
               Set<Pair>pairset=new HashSet<Pair>();
               Set<Triple>pairsetpubmid=new HashSet<Triple>();
               
               for(BioGridList bi:Datasources.get(s)){
                  pairset.add(new Pair(bi.getD(),bi.getG()));
                  pairsetpubmid.add(new Triple(bi.getD(),bi.getG(),bi.getPubmedID()));
               }
               Datasource.put(s, pairset);
               Datasourcepubmid.put(s, pairsetpubmid);
           
           }
               
           for(String csource:Curated){
               if(Datasource.containsKey(csource)){
                  CuratedData.put(csource,Datasource.get(csource));
               
               }
           }
           for(String csource:Curated){
               if(Datasourcepubmid.containsKey(csource)){
                  CuratedDatapubmid.put(csource,Datasourcepubmid.get(csource));
               
               
               }
           }
            //System.out.print(CuratedDatapubmid.keySet()+"\t"+CuratedData.keySet()+"\n");
                 AllDataSources.put("CuratedData",CuratedData);
                 AllDataSourcespubmid.put("CuratedData",CuratedDatapubmid);
                //System.out.print("\n"+AllDataSourcespubmid.get("CuratedData").keySet()+"\n");
                  //System.out.print("\n"+AllDataSources.get("CuratedData").keySet()+"\n");
           for(String msource:AnimalModels){
               if(Datasource.containsKey(msource)){
                  ModelingData.put(msource,Datasource.get(msource));
               
               }
               if(Datasourcepubmid.containsKey(msource)){
                  ModelingDatapubmid.put(msource,Datasourcepubmid.get(msource));
               
               
               }
           }
           
              AllDataSources.put("ModelingData",ModelingData);
              AllDataSourcespubmid.put("ModelingData",ModelingDatapubmid);
           for(String Isource:INFERRED){
               if(Datasource.containsKey(Isource)){
                  InferedData.put(Isource,Datasource.get(Isource));
               
               }
               if(Datasourcepubmid.containsKey(Isource)){
                  InferedDatapubmid.put(Isource,Datasourcepubmid.get(Isource));
               
               
               }
           
           
           }
            AllDataSources.put("InferedData",InferedData);
            AllDataSourcespubmid.put("InferedData",InferedDatapubmid);
            // System.out.print("size of pair"+AllDataSources.size()+"\t"+"size of triple"+AllDataSourcespubmid.size()+"\n");
           // System.out.print("alldatabysource"+Datasources.keySet()+"\n"+Datasources.size()+"\n");
            for(BioGridList e2 : ds.getLines()){
               // DGS.add(new Triple(e2.getD(),e2.getG(),e2.getGDAscore()));
                DGA.add(new Pair(e2.getD(),e2.getG()));
                DGAScore.put(new Pair(e2.getD(),e2.getG()),e2.getGDAscore());
                
            }
            //System.out.print("Size of Pairs \n");
            for(String s:AllDataSources.keySet()){
                     System.out.print(s+"\t"+AllDataSourcespubmid.get(s).size()+"\n");
                
                }
            //System.out.print("Size of triple\n");
                for(String s:AllDataSourcespubmid.keySet()){
                     //System.out.print(s+"\t"+AllDataSourcespubmid.get(s).keySet()+"\n");
                
                }
            PrintWriter out1 = null;
              try {
                    String outFileName = "GDA.txt";
                    out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
                    out1.append("D"+"\t"+"G"+"\t"+"Ccounter"+"\n");
                 for(Pair p:DGAScore.keySet()){
                    int score=GDASCORES(AllDataSources, Literature,p,DGAScore);
                    int scoreR=GDASCORESRedundancy(AllDataSourcespubmid, Literature,p,DGAScore);
                     //double roundoff = (double)Math.round(score*100)/100;
                     //double roundoff2 = (double)Math.round(scoreR*100)/100;
                //DGAScoreRedundancy.put(p,score);
                    //System.out.print(DGAScore.get(p)+"\t"+roundoff+"\t"+roundoff2+"\n");
            //System.out.print(DGAScore.size()+"\n");
            //System.out.print(DGA.size());
            
                    
                    out1.append(score+"\t"+scoreR+"\n");
                 }
                 out1.close();
              }
            catch (Exception e) {
            e.printStackTrace();
        } 
              */
              
              
              
              return DGAScore;
    }
            
    
    
    
    
    
    
    public int GDASCORES(Map<String,Map<String,Set<Pair>>>AllDatasources,List<Triple>Literature,Pair DGA,Map<Pair,Double>DGAScore){
           double GDAScore=0.0;
           int Ccounter=0;
           int Mcounter=0;
           int Icounter=0;
           int Lcounter=0;
           double C=0.0;
           double I=0.0;
           double M=0.0;
           double L=0.0;
           Set<String>pubmed=new HashSet<String>();
           /* for(String dataset:AllDatasources.get("CuratedData").keySet()){
                if(AllDatasources.get("CuratedData").get(dataset).contains(DGA)){
                      Ccounter++;
                      
               }
           
           }
           // System.out.print("number of curated"+Ccounter+"\n");
            for(String dataset:AllDatasources.get("ModelingData").keySet()){
                if(AllDatasources.get("ModelingData").get(dataset).contains(DGA)){
                      Mcounter++;
               
               }
                 /*if(Mcounter>2)  {
                    break;
                 }
           }
           // System.out.print("number of modeling"+Mcounter+"\n");
           /* for(String dataset:AllDatasources.get("InferedData").keySet()){
                if(AllDatasources.get("InferedData").get(dataset).contains(DGA)){
                      Icounter++;
               
               }
            if(Icounter>0){
                 break;
            }
            }
           // System.out.print("number of Infered"+Icounter+"\n");*/
                
                for(Triple t:Literature){
                   Pair p=new Pair(t.getD(),t.getG());
                  
                   if(p.equals(DGA)){
                       pubmed.add(t.getPubmedID());
                   }
                 /* if(pubmed.size()>9){
                      break;
                      
                      
                  }*/
                }
                
                
                       Lcounter=pubmed.size();
                // System.out.print("pubmedid studies support the associations"+"\t///////////////"+Lcounter+"\n"+"////////////////");
           /* if(Ccounter>2){
                C=0.6;
            
            }
            else if(Ccounter==2){
                      C=0.5;
            
            }
            else if(Ccounter==1){
                     C=0.3;
            
            
            }
            else{
                C=0.0;
            }
            
             if(Mcounter>0){
                 M=0.2;
            
            }
            else{
                      M=0.0;
            
            }
             if(Icounter>0){
                     I=0.1;
            
            
            }
             else{
                I=0.0;
             }
             if(Lcounter>9){
                L=0.1;
            
            }
             else if(Lcounter<=9) {
              L=0.01*Lcounter;
                     //
                     //System.out.print(L+""+"\n");
             }
             
             
             GDAScore=C+M+I+L;
            //System.out.print("Pair"+"\t"+DGA+"\t"+GDAScore+"\t"+"______"+DGAScore.get(DGA)+"\n");
             
    return GDAScore;*/
         return Lcounter;
    
    }
    public int GDASCORESRedundancy(Map<String,Map<String,Set<Triple>>>AllDatasourcespubmid,List<Triple>Literature,Pair DGA,Map<Pair,Double>DGAScore){
           double GDAScore=0.0;
           int Ccounter=0;
           int Mcounter=0;
           int Icounter=0;
           int Lcounter=0;
           double C=0.0;
           double I=0.0;
           double M=0.0;
           double L=0.0;
           Set<String>pubmedusedlist=new HashSet<String>();
           Set<String>pubmed=new HashSet<String>();/*
            for(String dataset:AllDatasourcespubmid.get("CuratedData").keySet()){
                for(Triple t:AllDatasourcespubmid.get("CuratedData").get(dataset)){
                    Pair p=new Pair(t.getD(),t.getG());
                        if(p.equals(DGA)&&!pubmedusedlist.contains(t.getPubmedID())){
                               pubmedusedlist.add(t.getPubmedID());
                               Ccounter++;
                               break;
               }
                        
                }
            }
                
              /* if(Ccounter>2) {
                   break;
            }
            }
             //System.out.print("number of curated data"+Ccounter+"\n");

            
            
             //System.out.print("number of curated data"+Ccounter+"\n");
            for(String dataset:AllDatasourcespubmid.get("ModelingData").keySet()){
                for(Triple t:AllDatasourcespubmid.get("ModelingData").get(dataset)){
                    Pair p=new Pair(t.getD(),t.getG());
                        if(p.equals(DGA)&&!pubmedusedlist.contains(t.getPubmedID())){
                                Mcounter++;
                               pubmedusedlist.add(t.getPubmedID());
                               break;
               }
                }
               /* if(Mcounter>0){
                    break;
            }
           //System.out.print(Ccounter+"\n");
           
            }
             
           
           
            //System.out.print("number of modeling data"+Mcounter+"\n");
            
          /*  for(String dataset:AllDatasourcespubmid.get("InferedData").keySet()){
                for(Triple t:AllDatasourcespubmid.get("InferedData").get(dataset)){
                    Pair p=new Pair(t.getD(),t.getG());
                        if(p.equals(DGA)&&!pubmedusedlist.contains(t.getPubmedID())){
                               pubmedusedlist.add(t.getPubmedID());
                               Icounter++;
                               break;
               }
                }
           //System.out.print(Ccounter+"\n");
           
            
             
            if(Icounter>0){
                break;
            }
            }
           // System.out.print("number of Infered data"+Icounter+"\n");*/
                
                for(Triple t:Literature){
                   Pair p=new Pair(t.getD(),t.getG());
                   if(p.equals(DGA)&&!pubmedusedlist.contains(t.getPubmedID())){
                       pubmed.add(t.getPubmedID());
                       pubmedusedlist.add(t.getPubmedID());
                   }
                  /* if(pubmed.size()>9){
                      break;
                   }*/
                }
                
                 Lcounter=pubmed.size();
                 // System.out.print("number of publication data"+Lcounter+"\n");
                // System.out.print("pubmedid studies support the associations"+"\t///////////////"+Lcounter+"\n"+"////////////////");
           /* if(Ccounter>2){
                C=0.6;
            
            }
            else if(Ccounter==2){
                      C=0.5;
            
            }
            else if(Ccounter==1){
                     C=0.3;
            
            
            }
            else{
                C=0.0;
            }
            
             if(Mcounter>0){
                 M=0.2;
            
            }
            else{
                      M=0.0;
            
            }
             if(Icounter>0){
                     I=0.1;
            
            
            }
             else{
                I=0.0;
             }
             if(Lcounter>9){
                L=0.1;
            
            }
             else if(Lcounter<=9) {
              L=0.01*Lcounter;
                     //
                     //System.out.print(L+""+"\n");
             }
             
             
             GDAScore=C+M+I+L;
            //System.out.print("Pair"+"\t"+DGA+"\t"+GDAScore+"\t"+"______"+DGAScore.get(DGA)+"\n");
             
    return GDAScore;*/
              return Lcounter;

    
    }
    
    
    
    public void colorrationbetweenscore()throws IOException{
   
        PIAN network=new PIAN();
        Map<Pair,Double>NetworkS=network.producenetwork();
        UniprotscoredPIAN un= new UniprotscoredPIAN();
        Map<Pair,Double>NetworkUni=un.produceUniprotNetwork();
        MonogenicScoredNetwork MS=new MonogenicScoredNetwork();
        Map<Pair,Double>networkMG=MS.ProduceMonogenicScoredNetwork();
        OverlapedDataScoredDatasets OVL=new OverlapedDataScoredDatasets();
        Map<Pair,Double>NetworkOverlap=OVL.producenetwork();
        ELScoredData EL=new ELScoredData();
        Map<Pair,Double>networkEL=EL.ProduceNetwork();
        OMIMasGoldStandard omim=new OMIMasGoldStandard();
        Map<Pair,Double>networkOMIM= omim.produceUniprotNetwork();
        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
         GDAScore GDAS=new GDAScore();
         Map<Pair,Double>GDAScore=GDAS.calculateGDAScore(P);
        PrintWriter out1 = null;
              try {
                    String outFileName = "Corrolation.txt";
                    out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
                    out1.append("DGAS"+"\t"+"S"+"\t"+"Uni"+"\t"+"MG"+"\t"+"Overlap"+"\t"+"EL"+"\t"+"OMIM"+"\n");
                     for(Pair p:networkEL.keySet()){
                    
                
                    //System.out.print(DGAScore.get(p)+"\t"+roundoff+"\t"+roundoff2+"\n");
            //System.out.print(DGAScore.size()+"\n");
            //System.out.print(DGA.size());
            
                    
                    out1.append(GDAScore.get(p)+"\t"+NetworkS.get(p)+"\t"+NetworkUni.get(p)
                            +"\t"+networkMG.get(p)+"\t"+NetworkOverlap.get(p)+"\t"+networkEL.get(p)+"\t"+
                           networkOMIM.get(p)+"\t"+"\n");
                 }
                 out1.close();
              }
            catch (Exception e) {
            e.printStackTrace();
        } 
   
   }
    
    
    
            
}
        
        

