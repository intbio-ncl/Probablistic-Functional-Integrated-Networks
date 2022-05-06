/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiseaseNetwork;
import java.util.*;
import java.io.*;
import pfinnetwork.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class GeneticSimilarityJaccardIndex {
    public double JaccardSimilarity(String outfile,HashMap<Pair,Set<String>>CSharedgenes,Map<String,Set<String>>DiseaseGenes)throws IOException{
        HashMap<String,Double>CG=new HashMap<String,Double>();
        DiseaseClusters DC=new DiseaseClusters();
        HashMap<String,Set<String>>Clusters=DC.clusters(outfile);
        HashMap<String, Set<Pair>>ClustersPairs= DC.ClusterPairs(Clusters);
        HashMap<String, Set<Pair>>ClustersPairs2= DC.ClusterPairs(Clusters);
        System.out.print("number of clusters"+ClustersPairs);
        //ParsingGWASDB PG =new ParsingGWASDB();
       // HashMap<Pair,Integer>CSharedDrugs=PG.parsedrugcentral();    

        System.out.print(ClustersPairs.size()+"\n");
        //System.out.print(CSharedG.size());
        Set<String>ClusterDisease=new HashSet<String>();
        Set<String>DrugDiseases2=new HashSet<String>();
          for(String i : ClustersPairs.keySet()){
            for(Pair p:ClustersPairs.get(i)){
               ClusterDisease.add(p.getD());
                ClusterDisease.add(p.getG());
       }}

        for(Pair j:CSharedgenes.keySet()){
           
               DrugDiseases2.add(j.getD());
                DrugDiseases2.add(j.getG());
       }
      for(String i : ClustersPairs.keySet()){
          int count=0;
         
               if(CSharedgenes.keySet().contains(ClustersPairs.get(i))){
                   count++;
                   
}

      

}


        for(Pair p:CSharedgenes.keySet()){
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
          PrintWriter outi = null;
        try {
            String outFileName = i+"JaccardSimilarity.txt";
              outi = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
              outi.append("D1"+"\t"+"D2"+"\t"+"Score"+"\n");
           Set<String>ClusterSharedGenes=new HashSet<String>();
           double sum=0.0;
           double Average=0.0;
            //System.out.print("before"+ClustersPairs.get(i).size()+"\n");
             for(Pair p:ClustersPairs.get(i)){
                 
                 if(CSharedgenes.keySet().contains(p)){
                     Set<String>All=new HashSet<String>();
                     for(String dis:DiseaseGenes.get(p.getD())){

                          All.add(dis);
}
        for(String dis2:DiseaseGenes.get(p.getG())){

                          All.add(dis2);
}
                     //System.out.print("Common"+CSharedgenes.get(p).size()+"\n");
                     
                      //System.out.print("ALL"+ALL+"\n");
                      double pairaverage=(double)CSharedgenes.get(p).size()/All.size();
                 //for(String S2:CG.get(s)){
                    outi.append(p.getD()+"\t"+p.getG()+"\t"+pairaverage+"\n");
                   
                    //for(String sg:CSharedDrugs.get(p) ){
                      // System.out.print("JC"+pairaverage+"\n");
                     sum+=pairaverage;
                     
                    //ClusterSharedGenes.add(sg);
            //}
                //out1.append("\n");
            }
           
         
//}
            else if(!CSharedgenes.keySet().contains(p)&&(DrugDiseases2.contains(p.getD()))&&DrugDiseases2.contains(p.getG())){
                     sum+=0;
                    outi.append(p.getD()+"\t"+p.getG()+"\t"+0+"\n");

}
          else if(!CSharedgenes.keySet().contains(p)&&(!DrugDiseases2.contains(p.getD())||!DrugDiseases2.contains(p.getG()))){

                  ClustersPairs2.get(i).remove(p);
          
                 
}
                  
     
  }  //System.out.print("after"+ClustersPairs2.get(i).size()+"\n");

         if(ClustersPairs2.get(i).size()!=0){


         //if(ClusterSharedGenes.size()>1){

           // System.out.print(sum+"\n"); 
              //System.out.print(size+"\n"); 
              Average= sum/ClustersPairs2.get(i).size();
                     
                        CG.put(i,Average);           
                        //System.out.print(sum);
}
         
outi.close();
 
}
finally {
            if (i != null) {
                outi.close();
                
            }
        }
}
   
   

        


     double TotalAV=0.0; 
         double NetworkAverage=0.0;              
  for(String v:CG.keySet()){
      TotalAV+=CG.get(v);


}  NetworkAverage=TotalAV/CG.size();

  //System.out.print("TotalAV"+"\t"+TotalAV+"\n");
     //System.out.print("Average"+"\t"+TotalAV/CG.size()+"\n");
      //System.out.print("size"+"\t"+CG.size()+"\n");
     //System.out.print(CG.get("cluster33"));
PrintWriter out1 = null;
        try {
            String outFileName = "JaccardSimilarity.txt";
            out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            
            for (String s : CG.keySet()) {
                 //for(String S2:CG.get(s)){
                    out1.append(s+"\t"+CG.get(s)+"\t"+"\n");
            //}
                //out1.append("\n");
            }
           out1.append("TotalAverage"+TotalAV+"\n");
           out1.append("Number of Clusters"+CG.size()+"\n");
            out1.append("NetworkAverage"+NetworkAverage);
           
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
