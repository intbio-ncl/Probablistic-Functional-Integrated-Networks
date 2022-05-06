/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIANnetwork;

import Parsing.ParsingMonogenic;
import java.util.*;
import pfinnetwork.Pair;
import java.io.*;
import pfinnetwork.BuildingBioGrid;
import pfinnetwork.ParsingBioGrid;

/**
 *
 * @author aoeshaalsobhe
 */
public class OverlapbetweenELandMonogenic  {
    public void overlapELMG() throws IOException{
            Set<Pair>Overlapedassociations=new HashSet<Pair>();
            Set<String>OverlappedGenes=new HashSet<String>();
            Set<String>OverlappedDiseases=new HashSet<String>();
            Set<String>MGGenes=new HashSet<String>();
            Set<String>MGDiseases=new HashSet<String>();
            Set<String>ELGenes=new HashSet<String>();
            Set<String>ELDiseases=new HashSet<String>();
            ParsingMonogenic Monogenic=new ParsingMonogenic();
            Set<Pair>monogenicdiseaes=Monogenic.parseMonogenicDGA();
            ELScoredData d=new ELScoredData();
            ParsingBioGrid ps=d.parsedata();
            BuildingBioGrid bb=d.buildbiodataEL(ps);
            Set<Pair>ELData=new HashSet<Pair>();
            Map<String,Set<Pair>>EL=bb.getGS();
            for(String s: EL.keySet()){
                for(Pair p:EL.get(s)){
                    ELData.add(p);
                    ELGenes.add(p.getG());
                    ELDiseases.add(p.getD());
                }
            }
            for(Pair pMG:monogenicdiseaes){
                MGGenes.add(pMG.getG());
                MGDiseases.add(pMG.getD());
            
            }
            for(Pair p: ELData){
                if(monogenicdiseaes.contains(p)){
                   Overlapedassociations.add(p);
                
                }
            }
            for(String g: MGGenes){
                if(ELGenes.contains(g)){
                    OverlappedGenes.add(g);
                
                }
            }
            for(String di: MGDiseases){
                if(ELDiseases.contains(di)){
                   OverlappedDiseases.add(di);
                
                }
            }
            
            System.out.print("monogenic association"+"\t"+monogenicdiseaes.size()+"\n");
            System.out.print("EL association"+"\t"+ELData.size()+"\n");
            System.out.print("monogenic genes"+"\t"+MGGenes.size()+"\n");
            System.out.print("monogenic diseases"+"\t"+MGDiseases.size()+"\n");
            System.out.print("EL diseases"+"\t"+ELDiseases.size()+"\n");
            System.out.print("EL genes"+"\t"+ELGenes.size()+"\n");
             System.out.print("Overlapped genes"+"\t"+OverlappedGenes.size()+"\n");
              System.out.print("Overlapped Diseases"+"\t"+OverlappedDiseases.size()+"\n");
               System.out.print("Overlapped Pair"+"\t"+Overlapedassociations.size()+"\n");
}
    
    public void overlapnetwork() throws IOException{
            Set<Pair>Overlapedassociations=new HashSet<Pair>();
            Set<String>OverlappedGenes=new HashSet<String>();
            Set<String>OverlappedDiseases=new HashSet<String>();
            Set<String>MGGenes=new HashSet<String>();
            Set<String>MGDiseases=new HashSet<String>();
            Set<String>ELGenes=new HashSet<String>();
            Set<String>ELDiseases=new HashSet<String>();
            MonogenicScoredNetwork MS=new MonogenicScoredNetwork();
             Map<Pair,Double>networkMG=MS.ProduceMonogenicScoredNetwork();
            ELScoredData EL=new ELScoredData();
            Map<Pair,Double>networkEL=EL.ProduceNetwork();
            Set<Pair>ELData=new HashSet<Pair>();
             Set<Pair>MGData=new HashSet<Pair>();
            for(Pair edge: networkEL.keySet()){
                
                    ELData.add(edge);
                    ELGenes.add(edge.getG());
                    ELDiseases.add(edge.getD());
                
            }
            for(Pair edge2:networkMG.keySet()){
                MGData.add(edge2);
                MGGenes.add(edge2.getG());
                MGDiseases.add(edge2.getD());
            
            }
            for(Pair p: ELData){
                if(MGData.contains(p)){
                   Overlapedassociations.add(p);
                
                }
            }
            for(String g: MGGenes){
                if(ELGenes.contains(g)){
                    OverlappedGenes.add(g);
                
                }
            }
            for(String di: MGDiseases){
                if(ELDiseases.contains(di)){
                   OverlappedDiseases.add(di);
                
                }
            }
            
            System.out.print("monogenic association"+"\t"+MGData.size()+"\n");
            System.out.print("EL association"+"\t"+ELData.size()+"\n");
            System.out.print("monogenic genes"+"\t"+MGGenes.size()+"\n");
            System.out.print("monogenic diseases"+"\t"+MGDiseases.size()+"\n");
            System.out.print("EL diseases"+"\t"+ELDiseases.size()+"\n");
            System.out.print("EL genes"+"\t"+ELGenes.size()+"\n");
             System.out.print("Overlapped genes"+"\t"+OverlappedGenes.size()+"\n");
              System.out.print("Overlapped Diseases"+"\t"+OverlappedDiseases.size()+"\n");
               System.out.print("Overlapped Pair"+"\t"+Overlapedassociations.size()+"\n");
}
    
}