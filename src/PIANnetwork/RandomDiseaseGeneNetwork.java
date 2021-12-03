/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIANnetwork;

import DiseaseNetwork.NodesDistribution;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.*;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import pfinnetwork.Pair;

/**
 *
 * @author aoeshaalsobhe
 */
public class RandomDiseaseGeneNetwork {
    public void CreateRN(Map<Pair,Double>network)throws IOException{
        List<Pair>Edges=new ArrayList<Pair>();
        List<String>DiseaseNodeList=new ArrayList<String>();
        List<String>GeneNodeList=new ArrayList<String>();
        List<Double>Scores=new ArrayList<Double>();
          Set<Pair>Edges2=new HashSet<Pair>();
          Set<String>DiseaseNodes=new HashSet<String>();
          Set<String>GeneNodes=new HashSet<String>();
         for(Pair p: network.keySet()){
             Edges.add(p);
             DiseaseNodes.add(p.getD());
             GeneNodes.add(p.getG());
             Scores.add(network.get(p));
            } 
        for(String S: DiseaseNodes){
                DiseaseNodeList.add(S);
                      }
        for(String S: GeneNodes){
                GeneNodeList.add(S);
                      }
        System.out.print(DiseaseNodes.size()+"\n");
        System.out.print(GeneNodes.size()+"\n");
        NodesDistribution N= new NodesDistribution();
        NodeDegree ND=N.NodesDistributionn(Edges2);
        Map<String,Integer>DiseaseDegree=ND.getGeneDegree();
        Map<String,Integer>GeneDegree=ND.getDiseaseDegree();
        System.out.print("number of nodes"+ND.getDiseaseDegree().size());
        System.out.print("number of nodes"+ND.getGeneDegree().size());
           HashMap<Pair,Double>newnetwork=new HashMap<Pair,Double>();
           int counter =Scores.size()-1;
            for(String d:DiseaseDegree.keySet()){
                System.out.print(d+"\n");
              
                List<String>temp=new ArrayList<String>();
                temp.addAll(DiseaseNodeList);
                 int nodedegree=DiseaseDegree.get(d);
                   System.out.print("DegreeeD"+nodedegree+"\n");
                 while(nodedegree>0){
                    
                        Random random = new Random();
                        System.out.print(temp.size());
                         
                          int randomNumber = random.nextInt(temp.size());
                          
                          
                        System.out.print("Randonnumber"+"\t"+randomNumber+"\n");
                        System.out.print("randomnode"+"\t"+temp.get(randomNumber)+"\n");
                         int randomnodedegree=GeneDegree.get(temp.get(randomNumber));
                         System.out.print("Nodedegrr"+"\t"+randomnodedegree+"\n");
                        if(!newnetwork.containsKey(new Pair(d,temp.get(randomNumber)))&&randomnodedegree>0){
                            newnetwork.put(new Pair(d,temp.get(randomNumber)),Scores.get(counter));
                            nodedegree--;
                            randomnodedegree--;
                             counter--;
                             
                            
                            
                         //int degreeCounter=Degree.get(NodesList.get(randomNumber));
                         // degreeCounter=degreeCounter-1;
                            if(randomnodedegree==1)
                               {
                           
                            GeneDegree.remove(temp.get(randomNumber));
                            DiseaseNodeList.remove(temp.get(randomNumber));
                            
        
                               }
                            
                            else {
                               GeneDegree.put(temp.get(randomNumber),randomnodedegree);
                            }
                            temp.remove(randomNumber);
                            System.out.print("random degree"+randomnodedegree+"\n");
                            System.out.print("genedegreemap"+GeneDegree.size()+"\n");
                            System.out.print("Nodelistsize"+DiseaseNodeList.size()+"\n");
}

                       
                        
                        
                        
                        }   
 
                 
         //NodesList.remove("d");
        
                   


        
        Set<Pair>edges=new HashSet<Pair>(); 
        Set<String>diseases=new HashSet<String>();
         Set<String>genes=new HashSet<String>();
        for(Pair d1:newnetwork.keySet()){
                  diseases.add(d1.getD());
                  genes.add(d1.getG());
                  edges.add(d1);
             }
            System.out.print("\n"+genes.size()+"\n");
            System.out.print(edges.size()+"\n");
            System.out.print(newnetwork.size()+"\n");
           PrintWriter out1 = null;
        try {
            String outFileName ="RandomNetwork.txt";
            out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            
            for (Pair pair : newnetwork.keySet()) {
                 //for(String S2:CG.get(s)){
                    out1.append(pair.getD()+"\t"+pair.getG()+"\t"+newnetwork.get(pair)+"\n");
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
}}
    public Map<Pair,Double> CreateRN2(Map<Pair,Double>network)throws IOException{
        List<Pair>Edges=new ArrayList<Pair>();
        List<String>DiseaseNodeList=new ArrayList<String>();
        List<String>GeneNodeList=new ArrayList<String>();
        List<Double>Scores=new ArrayList<Double>();
        Map<Pair,Double>randomnetwork=new HashMap<Pair,Double>();
       
         for(Pair p: network.keySet()){
             
             Edges.add(p);
             DiseaseNodeList.add(p.getD());
             GeneNodeList.add(p.getG());
             Scores.add(network.get(p));
         }
         int counter = (Scores.size()-1);
         System.out.print("counter"+counter+"\n");
         
          
             while(!Edges.isEmpty()){
                Random random = new Random();
              int randomNumber = random.nextInt(Edges.size());
              int randomNumber2 = random.nextInt(Edges.size());
              Pair edge=Edges.get(randomNumber);
               Pair edge2=Edges.get(randomNumber2);
              if(!edge.equals(edge2)&&!edge.getD().equals(edge2.getD())&&!edge.getG().equals(edge2.getG())){
                 Pair randedge1=new Pair(edge2.getD(),edge.getG());
                 Pair randedge2=new Pair(edge.getD(),edge2.getG());
                 if(!network.containsKey( randedge1)&&!network.containsKey(randedge2)){
                 Edges.remove(edge2);
                 Edges.remove(edge);
                 //network.remove(p);
                 //network.remove(edge);
                 
                 randomnetwork.put(randedge1, Scores.get(counter));
                 counter--;
                 randomnetwork.put(randedge2, Scores.get(counter));
                 counter--;
          }
              
          }}
             
       
          Set<String>G=new HashSet<String>();
          Set<String>D=new HashSet<String>();
          Set<Pair>link=new HashSet<Pair>();
          for(Pair p:randomnetwork.keySet()){
              G.add(p.getG());
              D.add(p.getD());
              link.add(p);
          }
              System.out.print(randomnetwork.size()+"\n");
              System.out.print(G.size()+"\n");
              System.out.print(D.size()+"\n");
          
          
           PrintWriter out1 = null;
        try {
            String outFileName ="RandomNetwork.txt";
            out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            
            for (Pair pair : randomnetwork.keySet()) {
                 //for(String S2:CG.get(s)){
                    out1.append(pair.getD()+"\t"+pair.getG()+"\t"+randomnetwork.get(pair)+"\n");
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

     return randomnetwork;
}
}

