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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import pfinnetwork.BuildingBioGrid;
import pfinnetwork.PFINNetWork;
import pfinnetwork.Pair;
import pfinnetwork.ParsingBioGrid;

/**
 *
 * @author aoeshaalsobhe
 */
public class UniprotscoredPIAN {
    public Map<Pair,Double> produceUniprotNetwork()throws IOException{
       

        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        BuildingBioGrid B = PFIN.buildbiodataUniprotGS(P);
        PFIN.writetoFile(B) ;
//        PFIN.writetoFile2(B);
        B.getBio().remove("UNIPROT");
        goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(B.getGS());
        Score S = new Score();
        Map<String,Double>LLSScore=S.ScoredData(gss, B);
         IntegratedScores IS = new IntegratedScores();
          Map<Pair,Double>IntegratedS=IS.doIntegration(LLSScore,LLSScore,Collections.<Double>reverseOrder(),B);
          Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("UNIPRPTintegrated.txt",IntegratedS);
          //Network net = new Network();
         // Map<Pair,Double>IntegratedS=net.networkPurn();
           System.out.print("size of network"+"\t"+IntegratedS.size()+"\n");
            /*Map<Pair,Double>IntegratedS2=new HashMap<Pair,Double>();
             for(Pair p2:IntegratedS.keySet()){
        
            if(IntegratedS.get(p2)>19){
            
                IntegratedS2.put(p2, IntegratedS.get(p2));
            
            }
        }
             
             System.out.print(IntegratedS2.size()+"\n");*/
      return IntegratedS;     
}
   public Map<String,Double> UniProtLLS()throws IOException{
       

        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        BuildingBioGrid B = PFIN.buildbiodataUniprotGS(P);
        PFIN.writetoFile(B) ;
//        PFIN.writetoFile2(B);
        B.getBio().remove("UNIPROT");
        goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(B.getGS());
        Score S = new Score();
        Map<String,Double>LLSScore=S.ScoredData(gss, B);  
        
    
    return LLSScore;
}
   public void OverlapGSUniProt()throws IOException{
        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        BuildingBioGrid B = PFIN.buildbiodataUniprotGS(P);
        PFIN.writetoFile(B) ;
//        PFIN.writetoFile2(B);
        B.getBio().remove("UNIPROT");
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