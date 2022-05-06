/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiseaseNetwork;
import pfinnetwork.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class AverageSharedGenes {
       public void Jaccardsimilarity()throws IOException{
        Parsing Parse=new Parsing();
        HashMap<Pair,Set<String>>CGenes=Parse.CommonGenes();
        SharedGenes SD=new SharedGenes();
        SD.sharedgenes("cluster189.txt",CGenes);
        HashMap<String,Set<String>>AllGenes=Parse.DiseasesGenes();
        GeneticSimilarityJaccardIndex  GSJC=new GeneticSimilarityJaccardIndex ();
        double JaccardAcerage=GSJC.JaccardSimilarity("cluster189.txt",CGenes,AllGenes);

}
}