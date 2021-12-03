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
import java.util.*;
import pfinnetwork.BioGridList;
import pfinnetwork.Triple;

/**
 *
 * @author aoeshaalsobhe
 */
public class Nodeattributes {
       public Map<String,String>parsediseaseattributes() throws IOException {
            String file="diseasesattributes.tsv";
            Map<String,String>DiseasesID=new HashMap<String,String>();
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                String[] colums = line.split("\t");
                String NodeID = colums[0];
                String DiseaseID = colums[1];
                //System.out.print(NodeID+"\t"+DiseaseID+"\n");
               DiseasesID.put(NodeID,DiseaseID);
}
             //System.out.print(DiseasesID.size()+"\n");
             return DiseasesID;
}
       
       public Map<String,String>parsegeneattributes() throws IOException {
            String file="geneattributes.tsv";
            Map<String,String>genesID=new HashMap<String,String>();
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                String[] colums = line.split("\t");
                String NodeID = colums[0];
                String geneID = colums[1];
                //System.out.print(NodeID+"\t"+geneID+"\n");
               genesID.put(NodeID,geneID);
}
            // System.out.print(genesID.size()+"\n");
             return genesID;
}
}

