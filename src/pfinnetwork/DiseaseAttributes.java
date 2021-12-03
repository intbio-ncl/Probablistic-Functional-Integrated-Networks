/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pfinnetwork;
import java.util.*;
import java.io.*;
/**
 *
 * @author aoeshagaedmalsobhe
 */
public class DiseaseAttributes {
    public Map<String,String>DiseasesAttributes(Set<String>Diseases,Map<String,Set<String>>Cluster)throws IOException{
     Map<String,String> Symbol= new HashMap<String,String>();
     Map<String,Set<String>> ClusterAttributes= new HashMap<String,Set<String>>();
    Set<String>set=new HashSet<String>();
     Set<String>set2=new HashSet<String>();
     String filename = "diseasesattributes.tsv";
     File bioFile = new File(filename);
        BufferedReader in = new BufferedReader(new FileReader(bioFile));
        in.readLine();
        String line;
        Map<String,String>ClusterAtt=new HashMap<String,String>();
        while ((line = in.readLine()) != null) {
            String[] colums = line.split("\t");
            String ID = colums[1];
            String Name = colums[2];
            ClusterAtt.put(ID,Name);
            set.add(Name);
            set2.add(ID);
}               
            //System.out.print(Mapping);
           //System.out.print(Nodes.size()+"\n");
           //System.out.print(set.size()+"\n");
           //System.out.print(set2.size()+"\n");
            for(String s: Diseases){
                if(ClusterAtt.containsKey(s)){
        
                 Symbol.put(s,ClusterAtt.get(s));
        
        }
          } 
            //System.out.print(Symbol.size());

          /* BufferedWriter out = null;
         try {
         
         out = new BufferedWriter(new FileWriter(filename));
         for(String s : Symbol.keySet()){
         out.write(s + " \t" + Symbol.get(s) + "\n");}
         }
         catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }*/
                    for(String i : Cluster.keySet()){
                          Set<String> set1 =Cluster.get(i);
                          Set<String> diseaseset = new HashSet<String>();
                          for(String j : set1){
                          
                             if(ClusterAtt.containsKey(j)){
        
                               diseaseset.add(ClusterAtt.get(j));
                          
                          }
                      }
                      ClusterAttributes.put(i,diseaseset);
                      
                      }
        PrintWriter bw10 = null;
        try {
                  
            
                 bw10 = new PrintWriter(new FileWriter(new File("ClusterMapping.txt")));
                      for(String i : ClusterAtt.keySet()){
                   bw10.println(i + "\t"+ ClusterAtt.get(i));
                      }
                   bw10.close();
                              }
        catch(Exception e){
           e.printStackTrace();

                           }   
                
            finally {
            if (bw10 != null) {
                try {
                    bw10.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
         



         
      
         return Symbol;
}

public Map<String,String> GenesAttributes(Set<String>Genes)throws IOException{
     Map<String,String> Symbol2= new HashMap<String,String>();
    Set<String>set=new HashSet<String>();
     Set<String>set2=new HashSet<String>();
     String filename = "genesattribute.tsv";
     File bioFile = new File(filename);
        BufferedReader in = new BufferedReader(new FileReader(bioFile));
        in.readLine();
        String line;
        Map<String,String>GAttributes=new HashMap<String,String>();
        while ((line = in.readLine()) != null) {
            String[] colums = line.split("\t");
            String ID = colums[0];
            String Name = colums[2];
            GAttributes.put(ID,Name);
            //set.add(Name);
            //set2.add(ID);
}               
            //System.out.print(Mapping);
           //System.out.print(Nodes.size()+"\n");
           //System.out.print(set.size()+"\n");
           //System.out.print(set2.size()+"\n");
            for(String s: Genes){
                if(GAttributes.containsKey(s)){
        
                 Symbol2.put(s,GAttributes.get(s));
        
        }
          } 
          return Symbol2;
}
}

