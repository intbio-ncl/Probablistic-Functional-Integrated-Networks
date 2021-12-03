/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import pfinnetwork.Pair;
import java.util.*;

/**
 *
 * @author aoeshaalsobhe
 */
public class ENSTONCBIGeneID {
    public Map<String,String> NCBItiENS() throws IOException{
            File bioFile = new File("genemap2.txt");
            Map<String,String>NCBItoENS=new HashMap<String,String>();
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
                    String GNCBI=colums[9] ;      
                    String GENS = colums[10];
                    if(!GNCBI.equals("")&&!GENS.equals("")){
                   //System.out.print(GNCBI+"\t"+GENS+"\n");
                   NCBItoENS.put(GENS,GNCBI);
                }}
            }
            System.out.print("gene mapp"+NCBItoENS.size()+"\n");
            return NCBItoENS;
    }
    
    
    public String GeneNCBIID(String id,Map<String,String>NCBItoENS)throws IOException{
        String mapid="";
        
          
               if(NCBItoENS.containsKey(id)&&!NCBItoENS.get(id).equals("")){
                  mapid= NCBItoENS.get(id);
                  
               
               }
           return mapid;
    
    }

}