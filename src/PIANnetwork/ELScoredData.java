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
import pfinnetwork.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.*;
import Parsing.Nodeattributes;

/**
 *
 * @author aoeshaalsobhe
 */
public class ELScoredData {
    public ParsingBioGrid parsedata() throws IOException {
            String filename="DisGeNETupdated_2021EL.tsv";
            File bioFile = new File(filename);
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
           in.readLine();
            String line;
            Set<String> ids = new HashSet<String>();
            Set<String> Sources = new HashSet<String>();
            List<BioGridList> Lines = new ArrayList<BioGridList>();
            Map<Triple,String>evidencelevel=new HashMap<Triple,String>();
            Nodeattributes DA=new Nodeattributes();
            Map<String, String>diseaseID=DA.parsediseaseattributes();
            Map<String, String>geneID=DA.parsegeneattributes();
          while ((line = in.readLine()) != null) {
              String[] colums= line.split("\t");
               
            String D = colums[1];
            String DiseaseID=D;
            if(diseaseID.containsKey(D)){
               DiseaseID=diseaseID.get(D);
            }
            String G = colums[2];
            String GeneID=G;
            if(geneID.containsKey(G)){
               GeneID=geneID.get(G);
            }
            String source = colums[3];
            
            String pubmedID = colums[5]; // pubmedID
            
            String Evidence=colums[7];
            
           
           double score=Double.parseDouble(colums[6]);
            //int nofpubmid=Integer.parseInt(colums[13]);
            BioGridList tmp = new BioGridList(DiseaseID, GeneID, pubmedID, source,Evidence, score);
            ids.add(pubmedID);
            Sources.add(source);
          
            Lines.add(tmp);
            }
            System.out.print(ids.size());
    return new ParsingBioGrid(ids,Sources,Lines);
  
}
    
    
    public BuildingBioGrid buildbiodataEL(ParsingBioGrid ds)throws IOException {
        Map<String,List<BioGridList>>genediseasebypubmedid=new HashMap<String,List<BioGridList>>();
         Map<String,List<BioGridList>>genediseasebypubmedid2=new HashMap<String,List<BioGridList>>();
        int counter=0;
         Map<String,Double>EL=new HashMap<String,Double>();
        Map<String,Set<Pair>>ConfidenceStudies=new HashMap<String,Set<Pair>>();
        Map<String,Set<Pair>>lowconfidenceStudies=new HashMap<String,Set<Pair>>();
        Map<String,Map<Pair,String>>datasets=new HashMap<String,Map<Pair,String>>();
        Map<String,Set<Pair>>AllStudies=new HashMap<String,Set<Pair>>();
        Set<Pair>Pairs=new HashSet<Pair>();
        Set<String>Genes=new HashSet<String>();
        Set<String>Diseases=new HashSet<String>();
        
      for (String id : ds.getIds()) {
            genediseasebypubmedid.put(id, new ArrayList<BioGridList>());
            
        }
       
        for (BioGridList e1 : ds.getLines()) {
            genediseasebypubmedid.get(e1.getPubmedID()).add(e1);
           
            
        }
         genediseasebypubmedid.remove("");
        System.out.print("number of studies"+"\t"+genediseasebypubmedid.size()+"\n");
        for(String id:genediseasebypubmedid.keySet()){
           
            List<BioGridList>pairs=genediseasebypubmedid.get(id);
            String names=id+pairs.get(0).getAssociation();
            Set<Pair>interactions=new HashSet<Pair>();
            Set<Pair>originalinteractions=new HashSet<Pair>();
            Set<Pair>Unreported=new HashSet<Pair>();
            for(BioGridList L:pairs){
                originalinteractions.add(new Pair(L.getD(),L.getG()));
                if(L.getAssociation().equals("strong")||L.getAssociation().equals("definitive")){
                    Pair t=new Pair(L.getD(),L.getG());
                    interactions.add(t);
                    }
                
            }
                double inter=interactions.size();
                   double originter=originalinteractions.size();
                  double Percentage=inter/originter*100;
                  EL.put(id, Percentage);
                   if(!interactions.isEmpty()&&Percentage>=50){
                       
                    ConfidenceStudies.put(names, originalinteractions);
                   }
                   else 
                         
                       lowconfidenceStudies.put(names,originalinteractions );
                   
                    
                  
        }
        System.out.print(counter+"\n");
         System.out.print(ConfidenceStudies.size()+"\n");
        System.out.print(lowconfidenceStudies.size()+"\n");
         for(String s:lowconfidenceStudies.keySet()){
           for(Pair t:lowconfidenceStudies.get(s)){
                   Pairs.add(new Pair(t.getD(),t.getG()));
                   Genes.add(t.getG());
                   Diseases.add(t.getD());
           
           
           }
           
         }
        System.out.print("total interaction"+"\t"+Pairs.size());
       PrintWriter out = null;
        try {
            String outFileName = "EL.txt";
            out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            for (String s : EL.keySet()) {
                
                out.println(s + "\t" + EL.get(s));
            }
            
            out.close();
        } finally {
            if (out != null) {
                out.close();
                
            }
        }
        AllStudies.putAll(lowconfidenceStudies);
        AllStudies.putAll(ConfidenceStudies);
    
        return new BuildingBioGrid(lowconfidenceStudies, Genes, Diseases, Pairs,datasets,ConfidenceStudies,AllStudies);
         
}
    
    public Map<Pair,Double>ProduceNetwork()throws IOException{
        ELScoredData d=new ELScoredData();
          ParsingBioGrid ps=d.parsedata();
          BuildingBioGrid bb=d.buildbiodataEL(ps);
          PFINNetWork PFIN = new PFINNetWork();
           PFIN.writetoFile(bb) ;
           PFIN.writetoFile2(bb);
         
          goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(bb.getGS());
         Score S = new Score();
         LTPSCores LTP = new LTPSCores();
           Map<String,Double>LLSScore=S.ScoredData(gss, bb);
         Map<String,Double>LLSScore2=LTP.LTPScore(bb);
         //LLSScore.putAll(LLSScore2);
       IntegratedScores IS = new IntegratedScores();
          Map<Pair,Double>IntegratedS=IS.doIntegration(LLSScore,LLSScore,Collections.<Double>reverseOrder(),bb);
          Map<Pair,Double>IntegratedThreshold=new HashMap<Pair,Double>();
          /*for(Pair p: IntegratedS.keySet()){
              if(IntegratedS.get(p)>39){
                  IntegratedThreshold.put(p,IntegratedS.get(p));
              
              }
          }
          Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("integratedhighlevelnetwork8.txt",IntegratedS);
         System.out.print("\n"+IntegratedS.size()+"\n");
    
    */
    
    return IntegratedS;
    
    }
    
    public void writetoFile(BuildingBioGrid bio) throws IOException {
        PrintWriter out = null;
        try {
            String outFileName = "ExtractionDataSource.txt";
            out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            out.println("number of Dataset = " + bio.numDatasets());
            for (String s : bio.getBio().keySet()) {
                
                out.println(s + "\t" + bio.getBio().get(s).size());
            }
            int total = 0;
            for (String s : bio.getBio().keySet()) {
                total = total + bio.getBio().get(s).size();
                
            }
            out.println(total);
            out.println();
            out.println("There are\t" + bio.getAllPairs().size() + "unique pairs");
            out.println("There are\t" + bio.getAllgenes().size() + "unique genes");
            out.println("There are\t" + bio.getAlldiseases().size() + "unique diseases");
            out.close();
        } finally {
            if (out != null) {
                out.close();
                
            }
        }
    }
    
}
