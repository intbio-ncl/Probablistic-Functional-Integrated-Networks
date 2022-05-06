/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pfinnetwork;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
/**
 *
 * @author aoeshagaedmalsobhe
 */
public class ClusterAnalysis {

    
    public Map<String,Set<String>> clusters()throws IOException{

           
            HashMap<String,Set<String>>Cluster= new HashMap<String, Set<String>>();
            HashMap<String,Set<String>>ClusterMapping= new HashMap<String, Set<String>>();
            BufferedReader buf = new BufferedReader(new FileReader("outdisease"));
        //in.readLine();
        String line;
        
        int count =1;
        while ((line = buf.readLine()) != null) {
                 String[] value = line.split("\t");
                Set<String>D=new HashSet<String>();
                for(String i : value){
                  D.add(i);}
                  
                 Cluster.put("cluster"+count,D);
                count++;
                
}        
       PrintWriter bw100 = null;
        try {
                  
            
                 bw100 = new PrintWriter(new FileWriter(new File("Distribution.txt")));
                      for(String i : Cluster.keySet()){
                   bw100.println(i + "\t"+ Cluster.get(i).size());
                      }
                   bw100.close();
                              }
        catch(Exception e){
           e.printStackTrace();

                           }   
                
            finally {
            if (bw100 != null) {
                try {
                    bw100.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
                       
            

 System.out.print(Cluster.get("cluster3740"));

   //System.out.print(Cluster.get("cluster1"));
          //System.out.print("size\t"+Cluster.size());
           return Cluster;
          
}
  public void run(Map<Pair,Double>Network,Map<String,Set<String>>Cluster){
            Map<String,Integer>map=new HashMap<String,Integer>();
            Map<String,Set<String>>Listofgenes=new HashMap<String,Set<String>>();
            Set<String>genes=new HashSet<String>();
          PrintWriter bw = null;
        try {
                  
            
                 bw = new PrintWriter(new FileWriter(new File("ListofDiseases.txt")));
      
              int counter =0;
              for(Pair P:Network.keySet()){
                  genes.add(P.getD());
              
              }
              for(String i:genes){
                  Set<String>genediseases=new HashSet<String>();
                 for(Pair P2 : Network.keySet()){
                     if(P2.getD().equals(i)){
                         genediseases.add(P2.getG());
                         
                     }}
                 Listofgenes.put(i,genediseases);
                 }
            for (String source : Listofgenes.keySet()) {
                  Map<String,Integer>ListGenes=new HashMap<String,Integer>();
                  
                  List<String>clusters = new ArrayList<String>();
                 Set<String>clusterss = new HashSet<String>();
                Set<String> Genes = Listofgenes.get(source);
                
                
                   for(String s : Genes){
                    // List<String>clusters = new ArrayList<String>();
                   //Set<String>clusterss = new HashSet<String>();
                    for (String source2 : Cluster.keySet()) {
                            Set<String> nodes = Cluster.get(source2);
                           
                            if (nodes.contains(s)) {
                                clusters.add(source2);
                                clusterss.add(source2);
                            }
                        }
                      //bw.println(source + "\t"+ s+"\t"+clusterss +"\n");
                    
                    }
                      
                         //map.put(source,clusters.size());
            
                      //System.out.print(clusters+"\n");
                      //System.out.print(clusters+"\n");
                     //System.out.print(mostCommon(clusters)+"\n");
                     float A;
                     float C;
                     float B;
                     A = Genes.size();
                     if(clusters.size()==0){
                            counter++;}
                    if(clusters.size()!=0){
                    C=Collections.frequency(clusters, mostCommon(clusters));
                   B = (float)(C/(A)*100);
                     if(clusterss.size()<2){
           bw.println(source + "\t"+ Genes.size()+"\t"+clusterss.size()+"\t"+mostCommon(clusters)+"\t"+C+"\t"+B);
               }     
              }  
                }
            System.out.print("lll\n"+counter);
               
              bw.close();
                              }
        catch(Exception e){
           e.printStackTrace();

                           }   
                
            finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
          //System.out.print("pp"+map.size()+"\n"); 
               /*for(String j : Listofgenes.keySet()){

                      System.out.print(j+"\n");
                      }
                       */
                      for(String i : map.keySet()){
                            //if(map.get(i)==0)
                       //System.out.print(map.size()+"\n");}
}            
   }     
    public static <T> T mostCommon(List<T> list) {
    Map<T, Integer> map = new HashMap<>();

    for (T t : list) {
        Integer val = map.get(t);
        map.put(t, val == null ? 1 : val + 1);
    }

    Entry<T, Integer> max = null;

    for (Entry<T, Integer> e : map.entrySet()) {
        if (max == null || e.getValue() > max.getValue())
            max = e;
    }

    return max.getKey();
}






}











