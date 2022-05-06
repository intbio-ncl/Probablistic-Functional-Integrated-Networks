/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsing;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileNotFoundException;
import pfinnetwork.*;
import pfinnetwork.ParsingBioGrid;
import Mapper.diseasemapping;

/**
 *
 * @author aoeshaalsobhe
 */
public class Ploysearch {
    public void parsedata() throws IOException {
            String file="p1_disease_gene_testset.simple.tsv";
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            Set<String> ids = new HashSet<String>();
            Set<String> Sources = new HashSet<String>();
            List<BioGridList> Lines = new ArrayList<BioGridList>();
            Set<Triple>names=new HashSet<Triple>();
            Set<String>DiseaseMap=new HashSet<String>();
            Set<String>Geneset=new HashSet<String>();
            diseasemapping mp=new diseasemapping();
            Map<String,String>Diseases=mp.DiseaseMap();
            while ((line = in.readLine()) != null) {
                String[] colums = line.split("\t");
                String Disease = colums[0];
                String Gene = colums[1];
                String association = colums[2];
                String pubmedID = colums[3]; // pubmedID
                 //System.out.print(Diseases+"\n");
                if(association.equals("Positive")){
                  DiseaseMap.add(Disease);
                   Geneset.add(Gene);
                   //if(!Mesh.equals("")){
                   Triple edge=new Triple(Disease,Gene,pubmedID);
                   //System.out.print(Mesh+"\t"+Gene+"\n");
                   names.add(edge);
                   
                
                }//}
        }Set<String> Mesh=mp.Meshdisease(DiseaseMap, Diseases);
            System.out.print(DiseaseMap+"\n");
            System.out.print(Geneset.size()+"\n");
            System.out.print(names.size()+"\n");
}
    public void parsejson() throws IOException {
            String file="POLYSEARCH-1633440458.json";
            String line;
            Set<String>Diseases=new HashSet<String>();
            BufferedReader in = new BufferedReader(new FileReader(file));
            JSONParser parser = new JSONParser();
            try{
                Object obj = parser.parse(new FileReader(file)); 
                //System.out.print(obj);
                JSONObject jsonObject = (JSONObject) obj;
               String jsonChildObject = (String)jsonObject.get("status");
               JSONObject jsonobject2=(JSONObject)jsonObject.get("hits");
               JSONArray jsonobject3=(JSONArray)jsonobject2.get("disease");
               System.out.print(jsonobject3.get(0));
               for(int i=0;i<jsonobject3.size();i++){
                   JSONObject jsonobject4=(JSONObject)jsonobject3.get(i);
                    String jsonChildObject3 = (String)jsonobject4.get("ename");
                    //System.out.print(Diseases);
                    Diseases.add(jsonChildObject3);
                    
               }  
               System.out.print(Diseases);
            }
            catch (Exception e) {
			e.printStackTrace();
		}
}
}