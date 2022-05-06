/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pfinnetwork;
import java.io.*;
import java.util.*;
import DiseaseNetwork.*;


/**
 *
 * @author aoeshagaedmalsobhe
 */
public class Network {
public HashMap<Pair,Double>networkPurn()throws IOException{
      HashMap<Pair,Double>Purnednetwork= new HashMap<Pair,Double>();
           HashMap<Pair,Double>mapPurnednetwork= new HashMap<Pair,Double>();

      String filename = "DiseaseNetworkOneComponent copy.txt";
     File bioFile = new File(filename);
        BufferedReader in = new BufferedReader(new FileReader(bioFile));
        //in.readLine();
        String line;
        DiseaseClusters DC = new DiseaseClusters();
        HashMap<String,String>Names=DC.DiseaseNames();
        Set<String>set=new HashSet<String>();
        List<String>list=new ArrayList<String>();
        Map<String,Integer>freq=new HashMap<String,Integer>();
        String mapsource="";
        String maptarget="";
        while ((line = in.readLine()) != null) {
            String[] colums = line.split("\t");
            String source = colums[0];
            if(Names.keySet().contains(source)){
                 mapsource=Names.get(source);
}
            String target =colums[1];
            if(Names.keySet().contains(target)){
                 maptarget=Names.get(target);
}
          // System.out.print(source+"\t"+target+"\t"+colums[2]);
           double weight=Double.parseDouble(colums[2]);
          Pair edge = new Pair(source, target);
          Pair mapedge=new Pair(mapsource,maptarget);
           Purnednetwork.put(edge,weight);
           mapPurnednetwork.put(mapedge,weight);
           
}
return mapPurnednetwork;
}
}
