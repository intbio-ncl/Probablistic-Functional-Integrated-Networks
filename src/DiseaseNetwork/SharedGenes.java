/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiseaseNetwork;
import java.io.*;
import java.util.*;
import pfinnetwork.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class SharedGenes {
    public Map<String,Set<String>> sharedgenes(String outfile,HashMap<Pair,Set<String>>CommonGenes)throws IOException{
         HashMap<String,Set<String>>CG=new HashMap<String,Set<String>>();
        DiseaseClusters DC=new DiseaseClusters();
        HashMap<String,Set<String>>Clusters=DC.clusters(outfile);
        HashMap<String, Set<Pair>>ClustersPairs= DC.ClusterPairs(Clusters);
        HashMap<String, Set<Pair>>ClustersPairs2= DC.ClusterPairs(Clusters);
        System.out.print("number of clusters"+ClustersPairs);
        //ParsingGWASDB PG =new ParsingGWASDB();
       // HashMap<Pair,Integer>CSharedDrugs=PG.parsedrugcentral();    

        //System.out.print(ClustersPairs.size()+"\n");
        //System.out.print(CSharedG.size());
        Set<String>ClusterDisease=new HashSet<String>();
        Set<String>DrugDiseases2=new HashSet<String>();
          for(String i : ClustersPairs.keySet()){
            for(Pair p:ClustersPairs.get(i)){
               ClusterDisease.add(p.getD());
                ClusterDisease.add(p.getG());
       }}

        for(Pair j:CommonGenes.keySet()){
           
               DrugDiseases2.add(j.getD());
                DrugDiseases2.add(j.getG());
       }
      for(String i : ClustersPairs.keySet()){
          int count=0;
         
               if(CommonGenes.keySet().contains(ClustersPairs.get(i))){
                   count++;
                   
}

      

}


        for(Pair p:CommonGenes.keySet()){
              DrugDiseases2.add(p.getD());
              DrugDiseases2.add(p.getG());
            

}
     Set<String>common=new HashSet<String>();
     // System.out.print("\n"+ClusterDisease.size()+"\t"+DrugDiseases2.size()+"\n");
     for(String d1: ClusterDisease){
         if(DrugDiseases2.contains(d1)){
            common.add(d1);

}
}
      //System.out.print("Common"+"\t"+common.size()+"\n");
       for(String i: ClustersPairs.keySet()){
             Set<String>ClusterSharedGenes=new HashSet<String>();
           int sum=0;
           double Average=0.0;
            //System.out.print("before"+ClustersPairs.get(i).size()+"\n");
             for(Pair p:ClustersPairs.get(i)){
                 if(CommonGenes.keySet().contains(p)){
                    //System.out.print(CSharedG.get(p).size()+"\n");
                    for(String sg:CommonGenes.get(p) ){
                       // sum+=CSharedgenes.get(p);
                     
                    ClusterSharedGenes.add(sg);
//}
}}
                     //if(ClusterSharedGenes.size()>1){
                        CG.put(i,ClusterSharedGenes);           
                        //System.out.print(sum);
//}
  }}    System.out.print(CG.size());  
PrintWriter out1 = null;
        try {
            String outFileName = "SharedGenes.txt";
            out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            
            for (String s : CG.keySet()) {
                 //if(CG.get(s).size()>1){
                 for(String S2:CG.get(s)){
                    out1.append(S2+"\t");
            //}
                 }
                out1.append("\n");
            }
            
           
            out1.close();
}
         finally {
            if (out1 != null) {
                out1.close();
                
            }
        }
    return CG;
}
}

