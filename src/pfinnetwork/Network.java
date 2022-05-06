/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pfinnetwork;
import java.io.*;
import java.util.*;


/**
 *
 * @author aoeshagaedmalsobhe
 */
public class Network {
public HashMap<Pair,Double>networkPurn()throws IOException{
      HashMap<Pair,Double>Purnednetwork= new HashMap<Pair,Double>();
      String filename = "DiseaseNetworkOneComponent copy.txt";
     File bioFile = new File(filename);
        BufferedReader in = new BufferedReader(new FileReader(bioFile));
        //in.readLine();
        String line;
        Set<String>set=new HashSet<String>();
        List<String>list=new ArrayList<String>();
        Map<String,Integer>freq=new HashMap<String,Integer>();
        while ((line = in.readLine()) != null) {
            String[] colums = line.split("\t");
            String source = colums[0];
            String target =colums[1];
          // System.out.print(source+"\t"+target+"\t"+colums[2]);
           double weight=Double.parseDouble(colums[2]);
          Pair edge = new Pair(source, target);
           Purnednetwork.put(edge,weight);
           
}
return Purnednetwork;
}
}
