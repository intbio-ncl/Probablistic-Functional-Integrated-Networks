/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiseaseNetwork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import pfinnetwork.Pair;

/**
 *
 * @author aoeshaalsobhe
 */
public class ClusterDrugSimilarity {
    
public void drugsimilarity()throws IOException{
    
         HashMap<String,Set<Pair>>SharedDrugs=new HashMap<String, Set<Pair>>();
            for(int i=1; i<1302;i++){
            File bioFile = new File("cluster"+i+"JaccardSimilarity.txt");
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            String line;
          
            Set<Pair>edges= new HashSet<Pair>();
            
            while ((line = in.readLine()) != null) {
                String[] colums = line.split("\t");
                
                edges.add(new Pair(colums[0],colums[1]));
}
                 SharedDrugs.put("Cluster"+i, edges);
}
                for(String k:SharedDrugs.keySet()){
                    for(Pair p:SharedDrugs.get(k) ){
                    if(p.getD().equals("C0079301")||p.getG().equals("C0079301")){
                        
                           System.out.print(k+"\n");
                    
                    }
                
                
                
}

}   Set<String>genes=new HashSet<String>();
                for(Pair t:SharedDrugs.get("Cluster114")){
                genes.add(t.getD());
                genes.add(t.getG());
                }
                
                System.out.print(genes);
                 PrintWriter out = null;
        try {
            String outFileName = "cluster114.txt";
            out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            
            for (String s : genes) {
                if(!s.equals("D1")&&!s.equals("D2")){
                     out.print(s + "\t" );
                
            }}
            
            out.close();
        } finally {
            if (out != null) {
                out.close();
                
            }
        }
    }
}
