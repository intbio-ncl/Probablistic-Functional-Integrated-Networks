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

public class OverlapPubmedID {

    public Map<String,Set<Triple>>Overlap(Map<String,Set<Triple>>datasets1){
        Map<String,Set<Triple>>OVERLAP=new HashMap<String,Set<Triple>>();
           
        try {
            BufferedWriter bw = null;
            
            bw = new BufferedWriter(new FileWriter(new File("overlap_Pubmed_G-D association.txt")));
            //bw.append("GENOMICS_ENGLAND"+"\n");
            for(Triple t:datasets1.get("GENOMICS_ENGLAND"))
                bw.append(t+"\n");
            for(String source : datasets1.keySet()) {
                
                Set<Triple> Associations = datasets1.get(source);
                for(Triple t:Associations){
                    
                }
                
                Set<Triple> OVERLAPSET = new HashSet<Triple>();
                double total = 0.0;
                for (Triple s : Associations) {
                    int overlap = 0;
                    for(String source2 : datasets1.keySet()) {
                        if (!source.equals(source2)) {
                        Set<Triple> Associations2 = datasets1.get(source2);
                            if (Associations2.contains(s)) {
                                overlap++;
                                
                                 
                            }
                              
                        }
                           
                    }
                if(overlap>0){
                   OVERLAPSET.add(s);
                 }
                
               bw.append(source + "\t" + s+ "\t"+ overlap + "\n");
              
                   }
                   OVERLAP.put(source, OVERLAPSET); 

                           }               
                bw.close();
                              }
        catch(Exception e){
           e.printStackTrace();

                           }  
        return OVERLAP;     
}
    public Map<String,Set<String>>OverlapPubMed(Map<String,Set<String>>datasets1){
        Map<String,Set<String>>OVERLAP=new HashMap<String,Set<String>>();
           
        try {
            BufferedWriter bw = null;
            
            bw = new BufferedWriter(new FileWriter(new File("overlap_Pubmed.txt")));
            bw.append("CLINGEN"+"\n");
            for(String p :datasets1.get("CLINGEN"))
                bw.append(p+"\n");
            for(String source : datasets1.keySet()) {
                
                Set<String> Associations = datasets1.get(source);
                for(String p:Associations){
                    
                }
                
                Set<String> OVERLAPSET = new HashSet<String>();
                double total = 0.0;
                for (String s : Associations) {
                    int overlap = 0;
                    for(String source2 : datasets1.keySet()) {
                        if (!source.equals(source2)) {
                        Set<String> Associations2 = datasets1.get(source2);
                            if (Associations2.contains(s)) {
                                overlap++;
                                
                                 
                            }
                              
                        }
                           
                    }
                if(overlap>0){
                   OVERLAPSET.add(s);
                 }
                
               // bw.append(source + "\t" + s+ "\t"+ overlap + "\n");
              
                   }
                   OVERLAP.put(source, OVERLAPSET); 

                           }               
                bw.close();
                              }
        catch(Exception e){
           e.printStackTrace();

                           }  
        return OVERLAP;     
}
    public Map<String,Set<Triple>>RemoveOverlap(Map<String,Set<Triple>>OVERLAP,Map<String,Set<Triple>>datasets1){
        for(String source : OVERLAP.keySet()) {
            Set<Triple> Associations = OVERLAP.get(source);
                for (Triple s : Associations) {
                    List<String> OVERLAPSET = new ArrayList<String>();
                    for(String source2 : datasets1.keySet()) {
                         
                            Set<Triple> Associations2 = datasets1.get(source2);
                            if (Associations2.contains(s)) {
                                OVERLAPSET.add(source2);
                                 
                            }
                              
                              }
                    
                         
                        while(OVERLAPSET.size()>1) {
                            
                            Random random = new Random();
                            int randomNumber = random.nextInt(OVERLAPSET.size());
                            datasets1.get(OVERLAPSET.get(randomNumber)).remove(s);
                            OVERLAPSET.remove(randomNumber);
                            

                    }
                        
              }        


}

        return datasets1;

}
}