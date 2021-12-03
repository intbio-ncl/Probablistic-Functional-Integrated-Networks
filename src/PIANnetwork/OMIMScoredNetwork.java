/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIANnetwork;

import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.*;
import pfinnetwork.BioGridList;
import java.util.regex.Matcher;
import java.util.regex.*;
import pfinnetwork.Pair;
import Parsing.ParsingMondo;
import DiseaseNetwork.Conversion;

/**
 *
 * @author aoeshaalsobhe
 */
public class OMIMScoredNetwork {
    public Set<Pair>parsingOMIM() throws IOException{
            File bioFile = new File("genemap2.txt");
            Set<Pair>diseasegeneassociation=new HashSet<Pair>();
            Set<String>OMIMdisease=new HashSet<String>();
            Set<String>OMIMgene=new HashSet<String>();
           Set<String>Gene=new HashSet<String>();
            Set<String>Disease=new HashSet<String>();
           
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            for(int i=0;i<=3;i++){
                in.readLine();
                 }
            
            String line;
            
       
            while ((line = in.readLine()) != null) {
                if (line.startsWith("#")) {
              
                      continue;
          }
                String[] colums = line.split("\t");
                if(colums.length>12){
                    //System.out.print(line+"\n");
                    String G=colums[9] ;      
                    String D = colums[12];
                    //System.out.print(D+"\n");
                    //System.out.print(D+"\n");
                    D=D.strip();
                    //System.out.print(D+"\n");
                    if(!G.equals("")&&!D.equals("")){
                    String[] phynotypes = D.split(";");
                    for(String s: phynotypes){
                       //System.out.print(s+"\n");
                    Pattern pattern = Pattern.compile("\\d{6}");
                    //System.out.print(pattern+"\n");
                    Matcher matcher = pattern.matcher(s);
                   
                    if(matcher.find()){
                         //System.out.print(matcher.group(0)+"\t"+G+"\n");
                       //
                        Pair p=new Pair(matcher.group(0),G);
                        OMIMgene.add(G);
                        OMIMdisease.add(matcher.group(0));
                      //System.out.print(G+"\t"+matcher.group(0)+"\n");
                        diseasegeneassociation.add(p);
                        
//}
            }   
            }
                    }
                }}
            for(Pair P:diseasegeneassociation){
                if(P.getD().equals("")||P.getG().equals("")){System.out.print("yes");}
                    Gene.add(P.getG());
                    Disease.add(P.getD());
        
        }
            Set<Pair>OMIMdataset=OMIMtoUML(diseasegeneassociation);
      
            
        System.out.print(diseasegeneassociation.size()+"\t"+Gene.size()+"\t"+ Disease.size()+"\n");
        return OMIMdataset;
    }
    
    
    
    public Set<Pair>OMIMtoUML(Set<Pair>OMIM)throws IOException{
        
        Set<String>MappedGene=new HashSet<String>();
        Set<String>MappedDisease=new HashSet<String>();
        ParsingMondo Mondo=new ParsingMondo();
        Set<Pair>MappedOMIM=new HashSet<Pair>();
        Map<String,String>UMLStoOMIM=Mondo.parsejson();
        //Conversion conversion = new Conversion();
        //Map<String,String>thesaurs=conversion.MeSHtoUMLS();
       //Map<String,String>OMIMtoUMLS=conversion.OMIMtoUMLS();
        
        for(Pair p:OMIM){
            
            String UMLSID=Mondo.mappingtoUMLS(UMLStoOMIM, p.getD());
           // String UMLSID=Mondo.mappingtoUMLS(UMLStoOMIM, p.getD());
             if(UMLSID!=null){
                 
                 MappedOMIM.add(new Pair(UMLSID,p.getG()));
                 
             }}
        for(Pair P:MappedOMIM){
            MappedGene.add(P.getG());
            MappedDisease.add(P.getD());
        
        }
        
        System.out.print(MappedOMIM.size()+"\t"+MappedGene.size()+"\t"+MappedDisease.size()+"\n");
       System.out.print(MappedOMIM.size());
    
    return MappedOMIM;
    }
    }//}//}
//}}