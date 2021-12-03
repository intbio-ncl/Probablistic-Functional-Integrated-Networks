/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author aoeshaalsobhe
 */
public class ParsingMondo {
    public Map<String,String> parsejson() throws IOException {
            String file="mondo-with-equivalents.json";
            String line;
           
            String diseaseIDUMLS="";
            Map<String,String>MapUMLStoOMIM=new HashMap<String,String>();
            Set<String>Diseases=new HashSet<String>();
            BufferedReader in = new BufferedReader(new FileReader(file));
            JSONParser parser = new JSONParser();
            try{
                Object obj = parser.parse(new FileReader(file)); 
                //System.out.print(obj);
                JSONObject jsonObject = (JSONObject) obj;
               JSONArray jsonobject1=(JSONArray)jsonObject.get("graphs");
                 PrintWriter out1 = null;
        try {
            String outFileName = "Mondo.txt";
            out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
               //JSONArray jsonobject2=(JSONArray)jsonobject1.get(0);
               //JSONObject jsonobject3=(JSONObject)jsonobject1.get(1);
                     for(int i =0;i<jsonobject1.size();i++){
                        //System.out.print(jsonobject1.get(i)+"\n"+"\n");
                        JSONObject jsonobject2=(JSONObject)jsonobject1.get(i);
                       JSONArray jsonobject3=(JSONArray)jsonobject2.get("nodes");
                       for(int j=0;j<jsonobject3.size();j++){
                           JSONObject jsonobject4=(JSONObject)jsonobject3.get(j);
                           String result = (String)jsonobject4.get("id");
                           String [] res=result.split("/");
                            String OMIMvar="";
                            String UMLSvar="";
                            //out1.append(res[4]+"\t");
                           //System.out.print(res[4]+"\t");
                           out1.append(res[4]+"\t");
                            JSONObject jsonobject5=(JSONObject)jsonobject4.get("meta");
                            if(jsonobject5!=null){
                            JSONArray jsonobject6=(JSONArray)jsonobject5.get("xrefs");
                                 if(jsonobject6!=null){
                                  for(int s=0;s<jsonobject6.size();s++){
                                      
                                      JSONObject jsonobject7=(JSONObject)jsonobject6.get(s);
                                      String var=(String)jsonobject7.get("val");
                                      //System.out.print(jsonobject7+"\t");
                                      String [] var3=var.split(":");
                                      if(var3[0].equals("OMIM")){
                                          OMIMvar=var3[1];
                                         
                                      }
                                      if(var3[0].equals("UMLS")){
                                             UMLSvar=var3[1];
                                      
                                      }
                                        
                                       out1.append(jsonobject7.get("val")+"\t");
                                       //System.out.print(jsonobject7.get("val")+"\t");
                                       //System.out.print(jsonobject7.get("val")+"\t");
                                       
                                       // JSONObject jsonobject8=(JSONObject)jsonobject7.get("val");
                                      /* String jsonobject9=(String)jsonobject8.get("OMIM");
                                       String jsonobject10=(String)jsonobject8.get("UMLS");
                                       String jsonobject11=(String)jsonobject8.get("DOID");
                                       String jsonobject12=(String)jsonobject8.get("ICD10");
                                       System.out.print(jsonobject9+"\t"+jsonobject10+"\t"+jsonobject11+"\t"+jsonobject12+"\n");*/
                     }
                              //System.out.print(OMIMvar+"\t"+UMLSvar+"\n");
                              
                                  if(!UMLSvar.equals("")&&!OMIMvar.equals("")){
                                     // System.out.print(OMIMvar+"\t"+UMLSvar+"\n");
                                        MapUMLStoOMIM.put(OMIMvar,UMLSvar);
                                  }
                                  out1.append("\n");
                                   
                                 }}}}
                     
                     //System.out.print(MapUMLStoOMIM.size()+"\n");
                     out1.close();
        }
             
              finally {
            if (out1 != null) {
                out1.close();
                
            }
        }}

            catch (Exception e) {
			e.printStackTrace();
		}
          
      return MapUMLStoOMIM;        
}
    
    
    public String mappingtoUMLS(Map<String,String>MapUMLStoOMIM,String disease){
           String diseaseIDUMLS="";
           if(MapUMLStoOMIM.containsKey(disease)){
                    diseaseIDUMLS=MapUMLStoOMIM.get(disease);
            
            }
                
                else{
                    diseaseIDUMLS=null;
    }
    return diseaseIDUMLS;
}
    
    
    
    }
    
    
