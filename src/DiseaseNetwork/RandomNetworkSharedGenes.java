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
public class RandomNetworkSharedGenes {
public double RandomNetworkAverage2()throws IOException{
              Parsing PG =new Parsing();
        HashMap<Pair,Integer>CSharedgenes=PG.parseGenes();
             List<Double> AverageList=new ArrayList<Double>();
             double sum =0.0;
             double RandomAverage=0;
             for( int i =0; i<100; i++){
                 SharedGenes SG=new SharedGenes();
                 String outfile="outRandom"+i;
                  double Average=SG.sharedgenes(outfile,CSharedgenes);
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
