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
public class Conversion {

 public void DOIDtoUMLS() throws IOException {
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


}System.out.print("number of disease in DO"+DiseaseinDO.size()+"\n");
        DiseaseClusters DC=new DiseaseClusters();
        HashMap<String,Set<String>>DCL=DC.clusters("cluster189.txt");
        HashMap<String,Set<String>>newDCL=new HashMap<String,Set<String>>();
        for(String i : DCL.keySet()){
            Set<String>DOIDSet=new HashSet<String>();
            Set<String>clusterdisease=DCL.get(i);
            
                   
                for(Pair p: DOIDterms){
                    if(clusterdisease.contains(p.getG())){
                      DOIDSet.add(p.getD());
                   
}
}   if(DOIDSet.size()>1){
       newDCL.put(i,DOIDSet); 
}
}
        



 System.out.print(newDCL.keySet().size());
               System.out.print(newDCL);
        int c=0;
    for(String i:newDCL.keySet()){
        
        
        if(newDCL.get(i).size()!=1){
            c++;


}

}  
             Set<String>DiseaseinCluster=new HashSet<String>();
             for(String i : DCL.keySet()){
                  for(String d: DCL.get(i))
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
            String outFileName = "UMLS to DOID.txt";
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
public HashMap<String,String>MeSHtoUMLS() throws IOException {
            String filenamee="MRCONSO.RRF";
            File bioFile = new File(filenamee);
            Conversion C = new Conversion();
            //HashMap<String,String>OMIMtoUMLS=C.ParsingMiner();
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            in.readLine();
            String line;
            
            
          HashMap<String,String>OMIMtoUMLS=new HashMap<String,String>();
            while ((line = in.readLine()) != null) {
                            String[] split = line.split("\\|");
                            String UMLS=split[0];
                            String Source = split[11];
                            String UI = split[13];
                           if (Source.equals("OMIM")&&!UI.equals("NOCODE")) {
                                OMIMtoUMLS.put(UI, UMLS);
                                //System.out.print(UMLS+"\t"+"\t"+UI+"\n");
                                //System.out.print(split[4]+"\n");
                           }
                          
                                //if(DDPair.containsKey(MeSH)){
                              // DDUMLS.put(MeSH,UMLSID);


           // if(OMIMtoUMLS.containsKey("")){System.out.print("yes");}
//}    
              
            }                        
        System.out.print("Mapp"+OMIMtoUMLS.size()+"\n");           

return OMIMtoUMLS;
             
  
}

public HashMap<String,String>ParsingMiner() throws IOException {
            String filenamee="DCh-Miner_miner-disease-chemical.tsv";
            File bioFile = new File(filenamee);
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            in.readLine();
            String line;

          HashMap<String,String>DDPairs=new HashMap<String,String>();
            while ((line = in.readLine()) != null) {
                             in.readLine();
                            String[] split = line.split("\t");
                             if(split[0].startsWith("MESH")){
                            String[] parts = split[0].split(":");
                            String part1 = parts[0]; // MESH
                            String Disease = parts[1]; // 034556
                             String Drug = split[1];
                            //System.out.print(Disease+"\t"+Drug+"\n");
                            
                   DDPairs.put(Disease,Drug); 
                   //System.out.print(DDPairs) ;     
         }           
}

return DDPairs;
  
}
    public Map<String,String>OMIMtoUMLS() throws IOException {
        String filenamee = "DOID.txt";
        File bioFile = new File(filenamee);
        BufferedReader in = new BufferedReader(new FileReader(bioFile));
        in.readLine();
        String line;
        String DOID;
        String Source;
        String UMLSID;
        Set<Pair> DOIDterms = new HashSet<Pair>();
        Map<String,String>DOIDtoOMIM=new HashMap<String,String>();
        Map<String,String>DOIDtoUMLS=new HashMap<String,String>();
         Map<String,String>UMLStoOMIM=new HashMap<String,String>();
        while ((line = in.readLine()) != null) {

            String[] split = line.split("\t");
            DOID = split[0];
            Source = split[2];
            UMLSID = split[3];
            if (Source.equals("UMLS")) {
                DOIDtoUMLS.put(DOID, UMLSID);
            }
            else if(Source.equals("OMIM")){
                    DOIDtoOMIM.put(DOID, UMLSID);
                        
                        
                        }
            }
        
           for(String d1:DOIDtoUMLS.keySet()){
               if(DOIDtoOMIM.containsKey(d1)){
                  UMLStoOMIM.put(DOIDtoOMIM.get(d1),DOIDtoUMLS.get(d1));
               }
        }
           if(UMLStoOMIM.containsValue("")){System.out.print("yes");}
       //System.out.print(UMLStoOMIM);
      return UMLStoOMIM; 
}
    public String mappingtoUMLS(Map<String,String>UMLStoOMIM,String disease){
           String diseaseIDUMLS="";
           if(UMLStoOMIM.containsKey(disease)){
                    diseaseIDUMLS=UMLStoOMIM.get(disease);
            
            }
                
                else{
                    diseaseIDUMLS=null;
    }
    return diseaseIDUMLS;
}
    
    
}
