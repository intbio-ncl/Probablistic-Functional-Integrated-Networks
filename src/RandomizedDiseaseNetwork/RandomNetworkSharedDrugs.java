/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package RandomizedDiseaseNetwork;
import java.io.*;
import java.util.*;
import pfinnetwork.*;
import DiseaseNetwork.DrugSimilarityJaccardIndex;
import DiseaseNetwork.Parsing;
/**
 *
 * @author aoeshagaedmalsobhe
 */
public class RandomNetworkSharedDrugs {

public double RandomNetworkAverage()throws IOException{
            Parsing Parse=new Parsing();
            HashMap<Pair,Integer>CDrugs=Parse.CommonDrugs();
            HashMap<String,Set<String>>AllDrugs=Parse.DiseaseDrugs();
            List<Double> AverageList=new ArrayList<Double>();
             double sum =0.0;
             double RandomAverage=0;
             for( int i =0; i<100; i++){
                
        DrugSimilarityJaccardIndex  GSJC=new DrugSimilarityJaccardIndex ();
        double JaccardAcerage=GSJC.JaccardDrugSimilarity("outRandomcluster"+i,CDrugs,AllDrugs);
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


