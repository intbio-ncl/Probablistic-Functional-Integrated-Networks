/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HistoricalEvaluation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pfinnetwork.BioGridList;
import pfinnetwork.BuildingBioGrid;
import pfinnetwork.OverlapPubmedID;
import pfinnetwork.Pair;
import pfinnetwork.ParsingBioGrid;
import pfinnetwork.Triple;

/**
 *
 * @author aoeshaalsobhe
 */
public class DisGeNET2018 {
    public ParsingBioGrid parsedisgenet2018()throws IOException{
           File bioFile = new File("DisGeNET_2018.tsv");
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            in.readLine();
            String line;
            Set<String> ids = new HashSet<String>();
            Set<String> Sources = new HashSet<String>();
            List<BioGridList> Lines = new ArrayList<BioGridList>();
            while ((line = in.readLine()) != null) {
                String[] colums = line.split("\t");
                String D = colums[1];
                String G = colums[2];
                double GDAscore=Double.parseDouble(colums[9]);
              //  System.out.print(D+"\t"+ G+"\n");
               
                String source = colums[3];
                String pubmedID = colums[7]; // pubmedID
                String association = colums[11];
                 //System.out.print(source +pubmedID+association);
                BioGridList tmp = new BioGridList(D, G, pubmedID, source, association,GDAscore);
                ids.add(pubmedID);
                Sources.add(source);
                Lines.add(tmp);
            
        }
            
         return new ParsingBioGrid(ids,Sources,Lines);
}
    public ParsingBioGrid parsedisgenet2020()throws IOException{
           File bioFile = new File("DisGeNET_2020.tsv");
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            in.readLine();
            String line;
            Set<String> ids = new HashSet<String>();
            Set<String> Sources = new HashSet<String>();
            List<BioGridList> Lines = new ArrayList<BioGridList>();
            while ((line = in.readLine()) != null) {
                String[] colums = line.split("\t");
                String D = colums[1];
                String G = colums[2];
               // System.out.print(D+"\t"+G+"\n");
                String source = colums[3];
                String pubmedID = colums[7]; // pubmedID
                String association = colums[11];
                double GDAscore=Double.parseDouble(colums[9]);
                BioGridList tmp = new BioGridList(D, G, pubmedID, source, association,GDAscore);
                ids.add(pubmedID);
                Sources.add(source);
                Lines.add(tmp);
            
        }
            
         return new ParsingBioGrid(ids,Sources,Lines);
}
    public ParsingBioGrid parsedisgenet2014()throws IOException{
           File bioFile = new File("DisGeNET_2014.tsv");
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            in.readLine();
            String line;
            Set<String> ids = new HashSet<String>();
            Set<String> Sources = new HashSet<String>();
            List<BioGridList> Lines = new ArrayList<BioGridList>();
            while ((line = in.readLine()) != null) {
                String[] colums = line.split("\t");
                String D = colums[1];
                String G = colums[2];
                String source = colums[5];
                String pubmedID = colums[6]; // pubmedID
                String association = colums[3];
                double GDAscore=Double.parseDouble(colums[9]);
                BioGridList tmp = new BioGridList(D, G, pubmedID, source, association,GDAscore);
                ids.add(pubmedID);
                Sources.add(source);
                Lines.add(tmp);
            
        }
            
         return new ParsingBioGrid(ids,Sources,Lines);
}
    
public BuildingBioGrid buildbiodata(ParsingBioGrid ds)throws IOException {
    
    Map<String, List<BioGridList>> genediseasepairbystudy = new HashMap<String, List<BioGridList>>();
    Map<String, Set<Pair>> HTP = new HashMap<String, Set<Pair>>();//set of Pairs
    Map<String, Set<Pair>> AllStudy = new HashMap<String, Set<Pair>>();
    Map<String, Set<Pair>> LTP = new HashMap<String, Set<Pair>>();//set of Pairs
    Map<String, Set<Pair>> GS = new HashMap<String, Set<Pair>>();
    Map<String, Set<Pair>> AllDatasets = new HashMap<String, Set<Pair>>();
    Map<String, Map<Pair,String>> datasetsasso = new HashMap<String, Map<Pair,String>>();
        for (String pubmedid : ds.getIds()) {
            genediseasepairbystudy.put(pubmedid, new ArrayList<BioGridList>());
        }
       
        for (BioGridList e1 : ds.getLines()) {
            genediseasepairbystudy.get(e1.getPubmedID()).add(e1);
            
        }

         Set<Pair> allPairs = new HashSet<Pair>();
        
        
             
          for (String id : genediseasepairbystudy.keySet()) {
            List<BioGridList> pairList = genediseasepairbystudy.get(id);
            Set<Pair> pairs = new HashSet<Pair>();
            
              for (BioGridList e2 : pairList) {
                    Pair P = new Pair(e2.getD(), e2.getG());
                     pairs.add(P);
                     allPairs.add(P);
            }
              
              AllStudy.put(id, pairs);
              
            if (pairs.size()>1) {
                String dsName = pairList.get(0).getPubmedID();
                HTP.put(dsName, pairs);
              
            } else {
                String Name = pairList.get(0).getPubmedID()+"_"+pairList.get(0).getAssociation();
                GS.put(Name,pairs);
                
            }
        }
          
         //System.out.print("number of study"+"\t"+AllStudy.size()+"\n");
                Set<String>allgenes=new HashSet<String>();
                Set<String>alldiseases=new HashSet<String>();
               
         
        

             for(Pair P :allPairs){
                allgenes.add(P.getG());
                alldiseases.add(P.getD());
             }
          //System.out.print(HTP.size()+"\t"+GS.size()+"\n"); 
        return new BuildingBioGrid(HTP, allgenes, alldiseases, allPairs, datasetsasso,GS,AllDatasets);
    
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
            Set<Pair> totaluniquePair = new HashSet<Pair>();
            for (String s : bio.getBio().keySet()) {
                for(Pair p:bio.getBio().get(s) ){
                totaluniquePair.add(p);
                
            }}
            out.println(totaluniquePair.size());
            
            
            out.close();
        } finally {
            if (out != null) {
                out.close();
                
            }
        }
    }
public void writetoFile2(BuildingBioGrid bio) throws IOException {
        PrintWriter out = null;
        try {
            String outFileName = "ExtractionGS.txt";
            out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            out.println("number of Dataset = " + bio.getGS().size());
           Set<Pair> totaluniquePair = new HashSet<Pair>();
           for (String s : bio.getGS().keySet()) {
                
                out.println(s + "\t" + bio.getGS().get(s).size());
            }
            for (String s : bio.getGS().keySet()) {
                for(Pair p:bio.getGS().get(s) ){
                totaluniquePair.add(p);
                
            }}
            out.println(totaluniquePair.size());
            
            
            
            out.close();
        } finally {
            if (out != null) {
                out.close();
                
            }
        }
    }
    
    
    
    }

