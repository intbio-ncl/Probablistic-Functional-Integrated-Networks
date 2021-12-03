/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import pfinnetwork.BioGridList;

/**
 *
 * @author aoeshaalsobhe
 */
public class diseasemapping {
    public Map<String,String> DiseaseMap() throws IOException {
            String file="POLYSEARCH2_THESAURUS_diseases.txt";
            Map<String,String>Diseases=new HashMap<String,String>();
            BufferedReader in = new BufferedReader(new FileReader(file));
            //  in.readLine();
            String line;
            Set<String> ids = new HashSet<String>();
            Set<String> Sources = new HashSet<String>();
            List<BioGridList> Lines = new ArrayList<BioGridList>();
            while ((line = in.readLine()) != null) {
                //line = a.replaceAll("\\s","");
                String[] colums = line.split("\t");
                String DiseaseID = colums[0];
                String DiseaseName = colums[1];
                //String g=colums[2];
               // System.out.print(DiseaseID+"\t"+ DiseaseName+"\n");
                Diseases.put(DiseaseName,DiseaseID);
        
                }
            System.out.print(Diseases);
            return Diseases;
        }
    public Set<String> Meshdisease(Set<String>DiseaseName,Map<String,String>Diseases){
        //System.out.print(Diseases);
           Set<String>mappeddisease=new HashSet<String>();
            for(String d: DiseaseName){
            
                 if(Diseases.containsKey(d)){
                     
                     mappeddisease.add(Diseases.get(d));
            }
        }
             //System.out.print(mappeddisease.size());
    return mappeddisease;
    
    
    }
    
}
