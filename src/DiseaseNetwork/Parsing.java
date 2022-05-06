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
public class Parsing{
 public HashMap<Pair,Set<String>>CommonGenes() throws IOException {
            File bioFile = new File("integrated.txt");
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            String line;
          HashMap<String,Set<String>>DiseaseGenes=new HashMap<String, Set<String>>();
          HashMap<Pair,Set<String>>SharedGenes=new HashMap<Pair,Set<String>>();
            Set<String>D= new HashSet<String>();
            Set<String>G= new HashSet<String>();
            Set<Pair>edge=new HashSet<Pair>();
            while ((line = in.readLine()) != null) {
                String[] colums = line.split("\t");
                D.add(colums[0]);
                G.add(colums[1]);
                edge.add(new Pair(colums[0],colums[1]));
}
        
            for(String d: D){
                Set<String>dgenes=new HashSet<String>();
                for(Pair f: edge){
                    if(f.getD().equals(d)){
                        dgenes.add(f.getG());

}


}
                      DiseaseGenes.put(d,dgenes);
}
                    
                   for(String d1:DiseaseGenes.keySet()){
                       Set<String>genes=DiseaseGenes.get(d1);
                        for(String d2: DiseaseGenes.keySet()){
                            Set<String>sharedG=new HashSet<String>();
                             //if(!d1.equals(d2)){
                                
                                 Set<String>genes2=DiseaseGenes.get(d2);
                                  for(String g: genes){
                                      if(genes2.contains(g)){
                                         sharedG.add(g);
//}
}

}
                 if(!sharedG.isEmpty()){
               SharedGenes.put(new Pair(d1,d2),sharedG);
}}
}
PrintWriter out1 = null;
        try {
            String outFileName = "DiseaseGeneticSimilarity.txt";
            out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            
            for (Pair s : SharedGenes.keySet()) {
                out1.println(s.getD()+"\t"+s.getG()+"\t"+SharedGenes.get(s));
            }
            
            
           
            out1.close();
}
         finally {
            if (out1 != null) {
                out1.close();
                
            }
        }
    


return SharedGenes; 

}

public HashMap<String,Set<String>>DiseasesGenes() throws IOException {
            File bioFile = new File("integrated.txt");
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            String line;
          HashMap<String,Set<String>>DiseaseGenes=new HashMap<String, Set<String>>();
          HashMap<Pair,Set<String>>SharedGenes=new HashMap<Pair,Set<String>>();
            Set<String>D= new HashSet<String>();
            Set<String>G= new HashSet<String>();
            Set<Pair>edge=new HashSet<Pair>();
            while ((line = in.readLine()) != null) {
                String[] colums = line.split("\t");
                D.add(colums[0]);
                G.add(colums[1]);
                edge.add(new Pair(colums[0],colums[1]));
}
        
            for(String d: D){
                Set<String>dgenes=new HashSet<String>();
                for(Pair f: edge){
                    if(f.getD().equals(d)){
                        dgenes.add(f.getG());

}


}
                      DiseaseGenes.put(d,dgenes);
}
                    
                   
PrintWriter out1 = null;
        try {
            String outFileName = "DiseaseGenes.txt";
            out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            
            for (String s : DiseaseGenes.keySet()) {
                out1.println(s+"\t"+DiseaseGenes.get(s).size());
            }
            
            
           
            out1.close();
}
         finally {
            if (out1 != null) {
                out1.close();
                
            }
        }
    


return DiseaseGenes; 

}
public HashMap<Pair,Integer>CommonDrugs() throws IOException {
            File bioFile = new File("DCh-Miner_miner-disease-chemical.tsv");
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            String line;
            in.readLine();
            Conversion CV = new Conversion();
            HashMap<String,String>ConvertedID=CV.MeSHtoUMLS();
          //System.out.print(ConvertedID+"This Mapper\n");
          HashMap<String,Set<String>>Diseasedrugs=new HashMap<String, Set<String>>();
          HashMap<Pair,Integer>SharedDrugs=new HashMap<Pair,Integer>();
            Set<String>DrugID= new HashSet<String>();
            Set<String>Disease= new HashSet<String>();
            Set<Pair>edge=new HashSet<Pair>();
             Set<Pair>edge1=new HashSet<Pair>();
              in.readLine();
            while ((line = in.readLine()) != null) {
                String[] split = line.split("\t");
                    if(split[0].startsWith("MESH")){
                            
                           String[] parts = split[0].split(":");
                            String part1 = parts[0]; // MESH
                            String DiseaseID = parts[1]; // 034556
                             String drugID = split[1];
                    if(ConvertedID.containsKey(DiseaseID)){
                //System.out.print(colums[0]+"\n");
                //System.out.print(colums[1]+"\n");
                edge.add(new Pair(ConvertedID.get(DiseaseID),drugID));
}
  }

}


  for(Pair e: edge){
        edge1.add(e);
        }
     for(Pair e: edge1){
         if(e.getG().equals("Null")||e.getD().equals("Null")){
          edge.remove(e);



}
     

}




//System.out.print(edge);

    for(Pair e1: edge){
         DrugID.add(e1.getG());
         Disease.add(e1.getD());


}



       // System.out.print(DrugID.size()+"\n");
       // System.out.print(Disease.size()+"\n");
            for(String d: Disease){
                Set<String>ddrugs=new HashSet<String>();
                for(Pair f: edge){
                    if(f.getD().equals(d)){
                        ddrugs.add(f.getG());



}}
                      Diseasedrugs.put(d,ddrugs);
}
                    System.out.print(Diseasedrugs.size());
                   for(String d1:Diseasedrugs.keySet()){
                       Set<String>drugs=Diseasedrugs.get(d1);
                        for(String d2: Diseasedrugs.keySet()){
                            Set<String>shareddrugs=new HashSet<String>();
                             //if(!d1.equals(d2)){
                                
                                 Set<String>drugs2=Diseasedrugs.get(d2);
                                  for(String g: drugs){
                                      if(drugs2.contains(g)){
                                         shareddrugs.add(g);
//}
}

}
                 if(!shareddrugs.isEmpty()){
               SharedDrugs.put(new Pair(d1,d2),shareddrugs.size());
}}
}
PrintWriter out1 = null;
        try {
            String outFileName = "DiseasetreatmentSimilarity.txt";
            out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            
            for (Pair s : SharedDrugs.keySet()) {
                out1.println(s.getD()+"\t"+s.getG()+"\t"+SharedDrugs.get(s));
            }
            
            
           
            out1.close();
}
         finally {
            if (out1 != null) {
                out1.close();
                
            }
        }
    
   

return SharedDrugs; 


}


public HashMap<String,Set<String>>DiseaseDrugs() throws IOException {
            File bioFile = new File("DCh-Miner_miner-disease-chemical.tsv");
            BufferedReader in = new BufferedReader(new FileReader(bioFile));
            String line;
            in.readLine();
            Conversion CV = new Conversion();
            HashMap<String,String>ConvertedID=CV.MeSHtoUMLS();
          //System.out.print(ConvertedID+"This Mapper\n");
          HashMap<String,Set<String>>Diseasedrugs=new HashMap<String,Set<String>>();
          HashMap<Pair,Integer>SharedDrugs=new HashMap<Pair,Integer>();
            Set<String>DrugID= new HashSet<String>();
            Set<String>Disease= new HashSet<String>();
            Set<Pair>edge=new HashSet<Pair>();
             Set<Pair>edge1=new HashSet<Pair>();
              in.readLine();
            while ((line = in.readLine()) != null) {
                String[] split = line.split("\t");
                    if(split[0].startsWith("MESH")){
                            
                           String[] parts = split[0].split(":");
                            String part1 = parts[0]; // MESH
                            String DiseaseID = parts[1]; // 034556
                             String drugID = split[1];
                    if(ConvertedID.containsKey(DiseaseID)){
                //System.out.print(colums[0]+"\n");
                //System.out.print(colums[1]+"\n");
                edge.add(new Pair(ConvertedID.get(DiseaseID),drugID));
}
  }

}


  for(Pair e: edge){
        edge1.add(e);
        }
     for(Pair e: edge1){
         if(e.getG().equals("Null")||e.getD().equals("Null")){
          edge.remove(e);



}
     

}




//System.out.print(edge);

    for(Pair e1: edge){
         DrugID.add(e1.getG());
         Disease.add(e1.getD());


}



       // System.out.print(DrugID.size()+"\n");
       // System.out.print(Disease.size()+"\n");
            for(String d: Disease){
                Set<String>ddrugs=new HashSet<String>();
                for(Pair f: edge){
                    if(f.getD().equals(d)){
                        ddrugs.add(f.getG());
}}
                      if(!ddrugs.isEmpty())
                      Diseasedrugs.put(d,ddrugs);
}
                    
PrintWriter out1 = null;
        try {
            String outFileName = "DiseasetreatmentSimilarity.txt";
            out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            
            for (String s : Diseasedrugs.keySet()) {
                out1.println(s+"\t"+Diseasedrugs.get(s)+"\n");
            }
            
            
           
            out1.close();
}
         finally {
            if (out1 != null) {
                out1.close();
                
            }
        }
    
   

return Diseasedrugs; 


}
                 
}