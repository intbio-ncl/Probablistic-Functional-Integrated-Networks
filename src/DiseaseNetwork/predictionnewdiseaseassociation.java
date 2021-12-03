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
public class predictionnewdiseaseassociation {
      public void predictnewassociation()throws IOException{
            DiseaseClusters DC = new DiseaseClusters();
            HashMap<String,Set<String>>Clusters=DC.clusters("outProbabilistic");
            HashMap<String,Set<Pair>>Diseaseincluster=DC.ClusterPairs(Clusters);
            Network NK = new Network();
            HashMap<Pair,Double>diseasenetwork=NK.networkPurn();
            Set<Pair>confirmedassociations=new HashSet<Pair>();
            System.out.print("\n"+Diseaseincluster.size());
            for(String i: Diseaseincluster.keySet()){
                for(Pair p: Diseaseincluster.get(i)){
                    if(diseasenetwork.keySet().contains(p)){
                       Diseaseincluster.remove(p);
                       confirmedassociations.add(p);
}

}
 

}
          Set<Pair>newassociations=new HashSet<Pair>();
            for(String j:Diseaseincluster.keySet()){
                for(Pair pair:Diseaseincluster.get(j)){
                    newassociations.add(pair);



}


}
            System.out.print("\n new association"+newassociations.size()+"\n"); 
              System.out.print("\n confirmed"+confirmedassociations.size()+"\n"); 

 PrintWriter bw100 = null;
        try {
                  
            
                 bw100 = new PrintWriter(new FileWriter(new File("Predicted new association.txt")));
                      for(Pair pair1 : newassociations){
                   bw100.println(pair1.getD() + "\t"+ pair1.getG());
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





}
}
