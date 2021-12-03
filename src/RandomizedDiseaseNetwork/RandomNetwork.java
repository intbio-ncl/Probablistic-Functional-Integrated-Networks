/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RandomizedDiseaseNetwork;
import pfinnetwork.*;
import DiseaseNetwork.*;
import java.util.*;
import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class RandomNetwork {

public void CreateRN(HashMap<Pair,Double>network)throws IOException{
         List<Pair>Edges=new ArrayList<Pair>();
           List<String>NodeList=new ArrayList<String>();
          List<Double>Scores=new ArrayList<Double>();
          Set<Pair>Edges2=new HashSet<Pair>();
          Set<String>Nodes=new HashSet<String>();
           
         for(Pair p: network.keySet()){
             Edges.add(p);
             Nodes.add(p.getD());
             Nodes.add(p.getG());
             Scores.add(network.get(p));
}         
      for(String S: Nodes){
         NodeList.add(S);
}
        System.out.print(Nodes.size()+"\n");
        System.out.print(Nodes.size()+"\n");
     HashMap<String,Set<String>>DiseaseDegree=new HashMap<String,Set<String>>();
            NodesDistribution N= new NodesDistribution();
             HashMap<String,Integer>Degreedistribution=N.NodesDistribution1();
              System.out.print("number of nodes"+Degreedistribution.size());
        for(int j =0; j<100; j++){
            HashMap<String,Integer>Degree=new HashMap<String,Integer>();
            Degree.putAll(Degreedistribution);
           List<String>NodesList=new ArrayList<String>();
          NodesList.addAll(NodeList);
           HashMap<Pair,Double>newnetwork=new HashMap<Pair,Double>();
           int counter =Scores.size()-1;
            for(String d:Degree.keySet()){
                 int nodedegree=Degree.get(d);
                 while(nodedegree>0&&counter>0){
                    
                        Random random = new Random();
                        int randomNumber = random.nextInt(NodesList.size());
                         int randomnodedegree=Degree.get(NodesList.get(randomNumber));
                        if(!NodesList.get(randomNumber).equals(d)&&!newnetwork.containsKey(new Pair(d,NodesList.get(randomNumber)))&&randomnodedegree>0){
                            newnetwork.put(new Pair(d,NodesList.get(randomNumber)),Scores.get(counter));
                            nodedegree--;
                            randomnodedegree--;
                             counter--;
                         //int degreeCounter=Degree.get(NodesList.get(randomNumber));
                         // degreeCounter=degreeCounter-1;
                        //if(randomnodedegree==0){NodesList.remove(randomNumber);}
                          //else 
                              Degree.put(NodesList.get(randomNumber),randomnodedegree);
}

                 
 }
         //NodesList.remove("d");
        
      }             


        /* HashMap<Pair,Double>newnetwork=new HashMap<Pair,Double>();
            while(newnetwork.size()<1307851){
                Random random = new Random();
                int randomNumber1 = random.nextInt(Edges.size());
                 Pair p1=Edges.get(randomNumber1);
                
                 int randomNumber2 = random.nextInt(Edges.size());
                 Pair p2=Edges.get(randomNumber2);
                 if(p1!=p2){
                
                if(!newnetwork.containsKey(new Pair(p1.getD(),p2.getD()))&&!p1.getD().equals(p2.getD()))
                      newnetwork.put(new Pair(p1.getD(),p2.getD()),network.get(p2));
                 if(!newnetwork.containsKey(new Pair(p1.getG(),p2.getG()))&&!p1.getG().equals(p2.getG()))
                      newnetwork.put(new Pair(p1.getG(),p2.getG()),network.get(p1));
 //Edges.remove(randomNumber2);
               }}*/
      Set<Pair>edges=new HashSet<Pair>(); 
Set<String>diseases=new HashSet<String>();
        for(Pair d1:newnetwork.keySet()){
                  diseases.add(d1.getD());
                  diseases.add(d1.getG());
                  edges.add(d1);
             }
 System.out.print(diseases.size()+"\n");
System.out.print(edges.size()+"\n");
System.out.print(newnetwork.size()+"\n");
           PrintWriter out1 = null;
        try {
            String outFileName ="RandomNetwork"+j+".txt";
;
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
}

}
}