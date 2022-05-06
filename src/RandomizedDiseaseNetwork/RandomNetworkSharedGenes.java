/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RandomizedDiseaseNetwork;
import DiseaseNetwork.*;
import java.util.*;
import java.io.*;
import pfinnetwork.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class RandomNetworkSharedGenes {
public double RandomNetworkAverage2()throws IOException{
            Parsing Parse=new Parsing();
            HashMap<Pair,Set<String>>CGenes=Parse.CommonGenes();
            HashMap<String,Set<String>>AllGenes=Parse.DiseasesGenes();
            List<Double> AverageList=new ArrayList<Double>();
             double sum =0.0;
             double RandomAverage=0;
             for( int i =0; i<100; i++){
                
        GeneticSimilarityJaccardIndex  GSJC=new GeneticSimilarityJaccardIndex ();
        double JaccardAcerage=GSJC.JaccardSimilarity("outRandomcluster"+i,CGenes,AllGenes);
                  System.out.print(JaccardAcerage+"\n");
                  AverageList.add(JaccardAcerage);
                  }
                for(int j=0; j<AverageList.size();j++){
                    sum+=AverageList.get(j);
                    

}
    RandomAverage=sum/100;
System.out.print("\n"+RandomAverage+"\n");
return RandomAverage;
  
}
}
