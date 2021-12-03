/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiseaseNetwork;
import java.io.*;
import java.util.*;
import pfinnetwork.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class DiseaseSemanticSimilarityPIDSN {
    public void diseasesemanticsimilarity()throws IOException{
         String filenamee="DOID.txt";
            File bioFile = new File(filenamee);
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            in.readLine();
            String line;
            String DOID;
            String Source;
            String UMLSID;
          Set<Pair>DOIDterms=new HashSet<Pair>();
           
            while ((line = in.readLine()) != null) {
                         
                            String[] split = line.split("\t");
                            DOID = split[0];
                            Source = split[2];
                            UMLSID = split[3];
                         if(Source.equals("UMLS")){
                          DOIDterms.add(new Pair(split[0],split[3]));
}
}
        Set<String>DiseaseinDO=new HashSet<String>();
    
        for(Pair pd:DOIDterms){
           DiseaseinDO.add(pd.getG());


}
System.out.print("number of disease in DO"+DiseaseinDO.size()+"\n");
        DiseaseClusters DC=new DiseaseClusters();
        HashMap<String,Set<String>>DCL=DC.clusters("outProbabilistic30");
        HashMap<String,Set<String>>newDCL=new HashMap<String,Set<String>>();
        for(String j : DCL.keySet()){
            Set<String>DOIDSet=new HashSet<String>();
            Set<String>clusterdisease=new HashSet<String>();
            for(String s: DCL.get(j)){
                   clusterdisease.add(s);
                for(Pair p: DOIDterms){
                    if(p.getG().contains(s)){
                      DOIDSet.add(p.getD());
                   
}
}   if(DOIDSet.size()>1){
       newDCL.put(j,DOIDSet); 
}
}
        
}


 //System.out.print(newDCL.keySet().size());
               //System.out.print(newDCL);
        int c=0;
    for(String j:newDCL.keySet()){
        
        
        if(newDCL.get(j).size()!=1){
            c++;


}

}  
             Set<String>DiseaseinCluster=new HashSet<String>();
             for(String j : DCL.keySet()){
                  for(String d: DCL.get(j))
                  DiseaseinCluster.add(d);
}
    System.out.print("Disease in clusters"+DiseaseinCluster.size()+"\n");  
    int K=0;
    for(String d: DiseaseinCluster){
        if(DiseaseinDO.contains(d)){
             K++;
}

}
   System.out.print("Common"+K+"\n");

  PrintWriter out = null;
        try {
            String outFileName = "UMLStoDOIDPISDN";
            out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
             for(String l: newDCL.keySet()){
                for(String l2: newDCL.get(l)){
                   out.append(l2+"\t");}
                    out.append("\n");
                          }
                 out.close();                      
}
            
            
            
            
            
        finally {
            if (out != null) {
                out.close();
                
            }
        }
           
         
    System.out.print(newDCL.size());

  

   
}
}


