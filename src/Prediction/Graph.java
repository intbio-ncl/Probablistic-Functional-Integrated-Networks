/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prediction;
import java.util.*;
import pfinnetwork.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */

    
public class Graph {
 
   
public void Network(Map<Pair,Double>N) {
        // TODO Auto-generated method stub
     Set<String>Diseases=new HashSet<String>();
     Set<String>Genes=new HashSet<String>();
     List<String>orderDiseases=new ArrayList<String>();
     List<String>orderedGenes=new ArrayList<String>();
     for(Pair P:N.keySet()){
         Diseases.add(P.getD());
         Genes.add(P.getG());
     
     }
     for(String d:Diseases){
         orderDiseases.add(d);
     
     
     }
     for(String g:Genes){
         orderedGenes.add(g);
     
     
     }
     
     Collections.sort(orderDiseases);
     Collections.sort(orderedGenes);
    
       
   double[][] adjacentMatrix;
   int size1=orderDiseases.size();
   int size2=orderedGenes.size();
  adjacentMatrix=new double[size1][size2];
            for(Pair P:N.keySet()){
            int startIndex = orderDiseases.indexOf(P.getD());
            int endIndex = orderedGenes.indexOf(P.getG());
               
              adjacentMatrix[startIndex][endIndex] = N.get(new Pair(P.getD(),P.getG()));

        }
            System.out.print(orderDiseases.indexOf("C0009402")+"\n");
             System.out.print(orderedGenes.indexOf("CNTN1"));
            System.out.print(adjacentMatrix[354][1673]+"\n");
            System.out.print(adjacentMatrix[0].length);
           /* for (int row = 0; row < adjacentMatrix.length; row++) {
                for (int column = 0; column < adjacentMatrix[row].length; column++) {
                    System.out.print(adjacentMatrix[row][column] + " ");
                }
               
    }
    */
           Map<Integer,List<Integer>>DiseaseNeighbours=new HashMap<Integer,List<Integer>>();
           
               List<Integer> neighbors = new ArrayList<Integer>();
               for(int i=0;i<adjacentMatrix.length;i++){
                   for (int j = 0; j < adjacentMatrix[0].length; j++){
                    if(adjacentMatrix[i][j]==0.0) {
                 neighbors.add(j);
                    }
    }
                DiseaseNeighbours.put(i,neighbors);
    
    }
     System.out.print(DiseaseNeighbours);
     
     
}
}



     
        //Defining nodes

   