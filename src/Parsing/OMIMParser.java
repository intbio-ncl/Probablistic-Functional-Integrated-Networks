/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.*;
import pfinnetwork.Pair;

/**
 *
 * @author aoeshaalsobhe
 */
public class OMIMParser {
    public Set<Pair> OMIMGD() throws IOException{
            File bioFile = new File("aoesha_gdi_unique_mapped.tsv");
            Map<String,String>NCBItoENS=new HashMap<String,String>();
            Set<Pair>omimtoumls=new HashSet<Pair>();
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            String line;
            while ((line = in.readLine()) != null) {
                
                String[] colums = line.split("\t");
                
                    String Gene=colums[0] ; 
                     String[] GeneNCBI = Gene.split("\\.");
                     //System.out.print(OMIMID[1]+"\n");
                     String GeneEntreze=GeneNCBI[1];
                     String Disease = colums[1];
                     
                    String [] DiseaseUMLS=Disease.split("\\.");
                   String DiseaseID=DiseaseUMLS[1];
                  omimtoumls.add(new Pair(DiseaseID, GeneEntreze));
    }
                    System.out.print("size of mapped association"+omimtoumls.size()+"\n");
            return omimtoumls;
    }
}
