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
public class SharedDrugs {
    public double shareddrugs(String outfile,HashMap<Pair,Integer>CSharedDrugs)throws IOException{
         HashMap<String,Double>CG=new HashMap<String,Double>();
        DiseaseClusters DC=new DiseaseClusters();
        HashMap<String,Set<String>>Clusters=DC.clusters(outfile);
        HashMap<String, Set<Pair>>ClustersPairs= DC.ClusterPairs(Clusters);
        HashMap<String, Set<Pair>>ClustersPairs2= DC.ClusterPairs(Clusters);
        //System.out.print("number of clusters"+ClustersPairs.size());
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

        for(Pair j:CSharedDrugs.keySet()){
           
               DrugDiseases2.add(j.getD());
                DrugDiseases2.add(j.getG());
       }
      for(String i : ClustersPairs.keySet()){
          int count=0;
         
               if(CSharedDrugs.keySet().contains(ClustersPairs.get(i))){
                   count++;
                   
}

      

}


        for(Pair p:CSharedDrugs.keySet()){
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
                 if(CSharedDrugs.keySet().contains(p)){
                      //System.out.print(CSharedG.get(p).size()+"\n");
                    //for(String sg:CSharedDrugs.get(p) ){
                     sum+=CSharedDrugs.get(p);
                     
                    //ClusterSharedGenes.add(sg);
//}
}
          else if(!CSharedDrugs.keySet().contains(p)&&(!DrugDiseases2.contains(p.getD())||!DrugDiseases2.contains(p.getG()))){

                  ClustersPairs2.get(i).remove(p);
                 
}
                  
}      
    //System.out.print("after"+ClustersPairs2.get(i).size()+"\n");

 if(ClustersPairs2.get(i).size()!=0){


         //if(ClusterSharedGenes.size()>1){

            //System.out.print(sum+"\n"); 
              //System.out.print(size+"\n"); 
              Average= sum/ClustersPairs2.get(i).size();
                     if(Average >=1)
                       Average=1;
                        CG.put(i,Average);           
                        //System.out.print(sum);
}
  }      double TotalAV=0.0; 
         double NetworkAverage=0.0;              
  for(String v:CG.keySet()){
      TotalAV+=CG.get(v);


}  NetworkAverage=TotalAV/CG.size();

  //System.out.print("TotalAV"+"\t"+TotalAV+"\n");
     System.out.print("Average"+"\t"+TotalAV/CG.size()+"\n");
      //System.out.print("size"+"\t"+CG.size()+"\n");
     //System.out.print(CG.get("cluster33"));
PrintWriter out1 = null;
        try {
            String outFileName = "SharedGenes.txt";
            out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            
            for (String s : CG.keySet()) {
                 //for(String S2:CG.get(s)){
                    out1.append(s+"\t"+CG.get(s)+"\t"+"\n");
            //}
                //out1.append("\n");
            }
            
           
            out1.close();
}
         finally {
            if (out1 != null) {
                out1.close();
                
            }
        }
    return NetworkAverage;
}
}


