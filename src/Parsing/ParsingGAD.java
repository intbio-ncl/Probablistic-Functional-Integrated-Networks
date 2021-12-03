/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsing;

import Mapper.diseasemapping;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pfinnetwork.*;

/**
 *
 * @author aoeshaalsobhe
 */
public class ParsingGAD {
     public void parseGAD() throws IOException {
            String file="export.tsv";
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            Set<Pair>GeneDiseaseNetwork=new HashSet<Pair>();
            Set<Triple>associa=new HashSet<Triple>();
            Set<Pair>originaledges=new HashSet<Pair>();
            Set<Triple>originalassoc=new HashSet<Triple>();
            Set<String>orignialdisease=new HashSet<String>();
            Set<Pair>edges=new HashSet<Pair>();
            Set<String>disease=new HashSet<String>();
             Set<String>genese=new HashSet<String>();
            Nodeattributes p=new Nodeattributes();
             Map<String,String>Diseaseattributes=p.parsediseaseattributes();
            while ((line = in.readLine()) != null) {
                String[] colums = line.split("\t");
                String Disease = colums[2];
                String Gene1 = colums[5];
                String Gene2 = Gene1.replaceAll("^\"+|\"+$", "");
                String association = colums[2];
                String pubmedID = colums[9]; // pubmedID
                
               // System.out.print(Gene+"\n");
                if(!Gene2.equals("")&&!Disease.equals("")){
                    orignialdisease.add(Disease);
                    originaledges.add(new Pair(Disease,Gene2));
                    originalassoc.add(new Triple(Disease,Gene2,pubmedID));
                   if(Diseaseattributes.containsKey(Disease)){
                     associa.add(new Triple(Diseaseattributes.get(Disease),Gene2,pubmedID));
                     disease.add(Diseaseattributes.get(Disease));
                     
                     edges.add(new Pair(Diseaseattributes.get(Disease),Gene2));
                     GeneDiseaseNetwork.add(new Pair(Diseaseattributes.get(Disease),Gene2));
                }
        }
            
         
}                System.out.print(associa.size()+"\n");
                  System.out.print(edges.size()+"\n");
                  System.out.print(disease.size()+"\n");
                  System.out.print(originalassoc.size()+"\n");
                  System.out.print(originaledges.size()+"\n");
                  System.out.print(orignialdisease.size()+"\n");
                  for(Pair p6:edges){
                      System.out.print(p6.getD()+"\t"+p6.getG()+"\n");
                  }
}
     
}
