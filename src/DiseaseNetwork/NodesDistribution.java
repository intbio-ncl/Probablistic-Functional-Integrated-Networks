/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiseaseNetwork;
import java.io.*;
import java.util.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class NodesDistribution {

    public HashMap NodesDistribution1()throws IOException{
    HashMap<String,Integer>DiseaseDegree=new HashMap<String,Integer>();
     String filename = "DiseaseNetworkOneComponent copy.txt";
     File bioFile = new File(filename);
        BufferedReader in = new BufferedReader(new FileReader(bioFile));
        //in.readLine();
        String line;
        Set<String>set=new HashSet<String>();
        List<String>list=new ArrayList<String>();
        HashMap<String,Integer>freq=new HashMap<String,Integer>();
        while ((line = in.readLine()) != null) {
            String[] colums = line.split("\t");
            String D = colums[0];
            String D2 =colums[1];
            set.add(D);
            set.add(D2);
            list.add(D);
            list.add(D2);
}
       System.out.print(set.size()+"Size of nodes"+"\n");
       System.out.print(list.size()+"\n")  ;
        String outfile = "DiseasesDistribution.txt";
          try{
         BufferedWriter out = new BufferedWriter(new FileWriter(outfile));
             for (String key : set) {
                 //if(Collections.frequency(list, key)){
                out.write(key+"\t"+ Collections.frequency(list, key)+"\n");
                  DiseaseDegree.put(key,Collections.frequency(list, key));
        //}
}
        out.close();
    }
     catch(IOException ex){
          ex.printStackTrace();
         System.out.println("Somthins gone horroblliy");
     }   
       return DiseaseDegree;
  
}
public void NodesDistribution2()throws IOException{
     String filename = "integrated.txt";
     File bioFile = new File(filename);
        BufferedReader in = new BufferedReader(new FileReader(bioFile));
        //in.readLine();
        String line;
        Set<String>set=new HashSet<String>();
        List<String>list=new ArrayList<String>();
        HashMap<String,Integer>freq=new HashMap<String,Integer>();
        while ((line = in.readLine()) != null) {
            String[] colums = line.split("\t");
            String G = colums[1];
            
            set.add(G);
            list.add(G);
}
       System.out.print(set.size()+"\n");
       System.out.print(list.size()+"\n")  ;
        String outfile = "GenesDistribution.txt";
          try{
         BufferedWriter out = new BufferedWriter(new FileWriter(outfile));
             for (String key : set) {
                  
                    out.write(key+"\t"+ Collections.frequency(list, key)+"\n");
        }
        out.close();
    }
     catch(IOException ex){
          ex.printStackTrace();
         System.out.println("Somthins gone horroblliy");
     }   
       

         
}
}
