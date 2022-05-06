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
public class RandomNetworkSharedDrugs {

public double RandomNetworkAverage()throws IOException{
              Parsing PG =new Parsing();
        HashMap<Pair,Integer>CSharedDrugs=PG.parsedrugcentral();
             List<Double> AverageList=new ArrayList<Double>();
             double sum =0.0;
             double RandomAverage=0;
             for( int i =0; i<100; i++){
                 SharedGenes SG=new SharedGenes();
                 String outfile="outRandom"+i;
                  double Average=SG.sharedgenes(outfile,CSharedDrugs);
                  AverageList.add(Average);
                  }
                for(int j=0; j<AverageList.size();j++){
                    sum+=AverageList.get(j);
                    

}
    RandomAverage=sum/100;
System.out.print("\n"+RandomAverage+"\n");
return RandomAverage;

}
}


