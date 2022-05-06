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
            String filename="DisGeNETupdated_2021.tsv";
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
            String associationstype=colums[4];
           
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
            String names=id;
            Set<Pair>interactions=new HashSet<Pair>();
            Set<Pair>originalinteractions=new HashSet<Pair>();
            Set<Pair>Unreported=new HashSet<Pair>();
            Set<Pair>LabbeledInteraction=new HashSet<Pair>();
            for(BioGridList L:pairs){
                originalinteractions.add(new Pair(L.getD(),L.getG()));
                if(!L.getAssociation().equals("")){
                   LabbeledInteraction.add(new Pair(L.getD(),L.getG()));
                }
                if(L.getAssociation().equals("strong")||L.getAssociation().equals("definitive")){
                    Pair t=new Pair(L.getD(),L.getG());
                    interactions.add(t);
                    }
                
            }
                int inter=interactions.size();
                   int originter=LabbeledInteraction.size();
                  
                  Double Percentage=(double)inter/(double)originter*100;
                 if(Percentage.isNaN()){
                     Percentage=0.0;
                 }
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
         //PFIN.writetoFile2(bb);
         
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
          }*/
          Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("HELscorednetwork.txt",IntegratedS);
         System.out.print("\n"+IntegratedS.size()+"\n");
    
    
    
    return IntegratedS;
    
    }
    public Map<String,Double>ELLLS()throws IOException{
        ELScoredData d=new ELScoredData();
          ParsingBioGrid ps=d.parsedata();
          BuildingBioGrid bb=d.buildbiodataEL(ps);
          PFINNetWork PFIN = new PFINNetWork();
           PFIN.writetoFile(bb) ;
         //  PFIN.writetoFile2(bb);
         
          goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(bb.getGS());
         Score S = new Score();
         LTPSCores LTP = new LTPSCores();
           Map<String,Double>LLSScore=S.ScoredData(gss, bb);
         Map<String,Double>LLSScore2=LTP.LTPScore(bb);
         return LLSScore;
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
    
    
    public void writetoFile2() throws IOException {
        Map<String,List<BioGridList>>genediseasebypubmedid=new HashMap<String,List<BioGridList>>();
        Map<String,Set<Pair>>pubmedidbyassociations=new HashMap<String,Set<Pair>>();
        ParsingBioGrid ds= parsedata();
        for (String id : ds.getIds()) {
            genediseasebypubmedid.put(id, new ArrayList<BioGridList>());
            
        }
       
        for (BioGridList e1 : ds.getLines()) {
            genediseasebypubmedid.get(e1.getPubmedID()).add(e1);
           
            
        }
        for (BioGridList e1 : ds.getLines()) {
            genediseasebypubmedid.get(e1.getPubmedID()).add(e1);
           
            
        }
        for(String id:genediseasebypubmedid.keySet()){
            List<BioGridList>Pairs=genediseasebypubmedid.get(id);
            Set<Pair>associations=new HashSet<Pair>();
             for(BioGridList bio:Pairs){
                associations.add(new Pair(bio.getAssociation(),bio.getSource()));
                
            }
             
             pubmedidbyassociations.put(id,associations);
        }
        Set<Integer>number=new HashSet<Integer>();
        for(String id:pubmedidbyassociations.keySet()){
            number.add(pubmedidbyassociations.get(id).size());
            if(pubmedidbyassociations.get(id).size()==12){
                System.out.print(pubmedidbyassociations.get(id));
        System.out.print(id+"\t"+pubmedidbyassociations.get(id).size()+"\t"+pubmedidbyassociations.get(id)+"\n");
            }
    }
        System.out.print(number);
    }
     public void writetoFile3() throws IOException {
        Map<Pair,List<BioGridList>>genediseasebyDGA=new HashMap<Pair,List<BioGridList>>();
        Map<Pair,Set<Triple>>pubmedidbyassociations=new HashMap<Pair,Set<Triple>>();
        ParsingBioGrid ds= parsedata();
        Set<Pair>DGAs=new HashSet<Pair>();
        for(BioGridList b:ds.getLines()){
            DGAs.add(new Pair(b.getD(),b.getG()));
        }
        for (Pair p : DGAs) {
            genediseasebyDGA.put(p, new ArrayList<BioGridList>());
            
        }
       
        for (BioGridList e1 : ds.getLines()) {
            genediseasebyDGA.get(new Pair(e1.getD(),e1.getG())).add(e1);
           
            
        }
        
        for(Pair p2:genediseasebyDGA.keySet()){
            List<BioGridList>triples=genediseasebyDGA.get(p2);
            Set<Triple>associations=new HashSet<Triple>();
             for(BioGridList bio:triples){
                associations.add(new Triple(bio.getPubmedID(),bio.getAssociation(),bio.getSource()));
                
            }
             
             pubmedidbyassociations.put(p2,associations);
        }
        Set<Integer>number=new HashSet<Integer>();
        for(Pair p3:pubmedidbyassociations.keySet()){
            number.add(pubmedidbyassociations.get(p3).size());
           //if(pubmedidbyassociations.get(p3).size()==12){
                System.out.print(p3+"\t"+pubmedidbyassociations.get(p3)+"\n");
       // System.out.print(p3+"\t"+pubmedidbyassociations.get(p3).size()+"\t"+pubmedidbyassociations.get(p3)+"\n");
            //}
    }
        //System.out.print(pubmedidbyassociations.get(p3));
        PrintWriter out = null;
        try {
            String outFileName = "ExtractionAssociationTypes.txt";
            out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            for(Pair p3:pubmedidbyassociations.keySet()){
                
                out.println(p3+"\t"+pubmedidbyassociations.get(p3));
            }
            
            out.close();
        } finally {
            if (out != null) {
                out.close();
                
            }
        }
    }
     public Map<String,Double> ELEvidence(ParsingBioGrid ds)throws IOException {
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
            String names=id;
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
    
        return EL;
         
}
    public void LLSCorrolationEL()throws IOException{
        
    ELScoredData d=new ELScoredData();
          ParsingBioGrid ps=d.parsedata();
          BuildingBioGrid bb=d.buildbiodataEL(ps);
          PFINNetWork PFIN = new PFINNetWork();
           PFIN.writetoFile(bb) ;
         //PFIN.writetoFile2(bb);
         
          goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(bb.getGS());
         Score S = new Score();
         LTPSCores LTP = new LTPSCores();
           Map<String,Double>LLSScore=S.ScoredData(gss, bb);
      String outfile = "LLSCEL.txt";
      Map<String,Double>EL=ELEvidence(ps);
    try{
    BufferedWriter out = new BufferedWriter(new FileWriter(outfile));
       
    for(String s : LLSScore.keySet()){
        
        out.write(s + "\t" + LLSScore.get(s)+"\t"+ EL.get(s)+"\n");
        }
    out.close();
    }
     catch(IOException ex){
          ex.printStackTrace();
         //System.out.println("Somthins gone horroblliy");
     }
}
    
    public void OverlapGSEL()throws IOException{
        ELScoredData d=new ELScoredData();
          ParsingBioGrid ps=d.parsedata();
          BuildingBioGrid bb=d.buildbiodataEL(ps);
          PFINNetWork PFIN = new PFINNetWork();
           PFIN.writetoFile(bb) ;
         //PFIN.writetoFile2(bb);
         
          goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(bb.getGS());
        double GOverlap=0.0;
        double DOverlap=0.0;
        double AssoOverlap=0.0;
        Set<String>OverlapedDiseases=new HashSet<String>();
         Set<String>OverlapedGenes=new HashSet<String>();
         Set<Pair>OverlapedAssociations=new HashSet<Pair>();
         Set<String>Diseases=new HashSet<String>();
         Set<String>Genes=new HashSet<String>();
         Set<Pair>Associations=new HashSet<Pair>();
         for(String ds: bb.getBio().keySet()){
             for(Pair p: bb.getBio().get(ds)){
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

    
    
    
    
