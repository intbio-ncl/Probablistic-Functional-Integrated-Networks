/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import pfinnetwork.Pair;
import pfinnetwork.Triple;
import Mapper.*;

/**
 *
 * @author aoeshaalsobhe
 */
public class ParsingMonogenic {
    public Set<Triple>parseMonogenicDGA() throws IOException {
        String file="Gene-RD-Provenance_V2.txt";
        Map<String,String>monogenicDGA=new HashMap<String,String>();
        Set<Pair>association=new HashSet<Pair>();
        Set<Pair>mappedassociation=new HashSet<Pair>();
        Set<Triple>mappedassociationstudies=new HashSet<Triple>();
       List<Pair>association1=new ArrayList<Pair>();
        List<Pair>mappedassociation1=new ArrayList<Pair>();
        BufferedReader in = new BufferedReader(new FileReader(file));
         ENSTONCBIGeneID mapp=new ENSTONCBIGeneID();
        Map<String,String> NCBItoENS=mapp.NCBItiENS();
        ParsingMondo mondomap=new ParsingMondo();
        Map<String,String>OMIMtoUMLS=mondomap.parsejson();
        System.out.print("size of mondo map"+OMIMtoUMLS.size()+'\n');
        int i=0;
        String line;
            while ((line = in.readLine()) != null) {
              
                String[] colums = line.split("\t");
                if(colums.length>4){
                String GeneStableID = colums[0];
                String DiseaseID=colums[3];
                if(!GeneStableID.equals("")&&!DiseaseID.equals("")){
                    i++;
                    
                  association1.add(new Pair(DiseaseID,GeneStableID));
                   association.add(new Pair(DiseaseID,GeneStableID));
                    String NCBI=mapp.GeneNCBIID(GeneStableID, NCBItoENS);
                    if(NCBI!=null&&!NCBI.equals("")){
                    String GeneSymbol = NCBI;
                    
                
                    String UMLSdisease=mondomap.mappingtoUMLS(OMIMtoUMLS, DiseaseID);
                    if(UMLSdisease!=null){
                   // System.out.print(NCBI+"\t"+DiseaseID+"\n");
                    monogenicDGA.put(UMLSdisease,NCBI);
                   // System.out.print(monogenicDGA.size()+"\n");
                 mappedassociation1.add(new Pair(UMLSdisease,NCBI));
                  mappedassociation.add(new Pair(UMLSdisease,NCBI));
                  mappedassociationstudies.add(new Triple(UMLSdisease,NCBI,colums[2]));
                   
            }
            }
            }
            }
            }
            Set<String>Diseases=new HashSet<String>();
            Set<String>Genes=new HashSet<String>();
            for(Pair p:mappedassociation){
                Diseases.add(p.getD());
                Genes.add(p.getG());
            
            }
            System.out.print("Mapped disease"+Diseases.size()+"\n");
            System.out.print("Mapped genes"+Genes.size()+"\n");
            System.out.print("Mapped associations"+mappedassociation1.size()+"\n");
           System.out.print(association1.size()+"\n");
            System.out.print(i+"\n");
             return mappedassociationstudies;
}
}
