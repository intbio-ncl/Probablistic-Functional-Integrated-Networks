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
public class AverageSharedDrugs {
        public void Jaccardsimilarity()throws IOException{
        Parsing Parse=new Parsing();
        HashMap<Pair,Integer>CDrugs=Parse.CommonDrugs();
        HashMap<String,Set<String>>AllDrugs=Parse.DiseaseDrugs();
        DrugSimilarityJaccardIndex  GSJC=new DrugSimilarityJaccardIndex ();
        double JaccardAcerage=GSJC.JaccardDrugSimilarity("cluster189.txt",CDrugs,AllDrugs);

}
}
