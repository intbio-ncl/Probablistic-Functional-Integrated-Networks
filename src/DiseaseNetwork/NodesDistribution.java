/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiseaseNetwork;
import PIANnetwork.ELScoredData;
import PIANnetwork.MonogenicScoredNetwork;
import java.io.*;
import java.util.*;
import pfinnetwork.*;
import PIANnetwork.NodeDegree;
import PIANnetwork.OMIMScoredNetwork;
import PIANnetwork.OMIMasGoldStandard;
import PIANnetwork.OverlapedDataScoredDatasets;
import PIANnetwork.PIAN;
import PIANnetwork.UniprotscoredPIAN;

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
           // String D2 =colums[1];
            set.add(D);
            //set.add(D2);
            list.add(D);
            //list.add(D2);
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
public NodeDegree NodesDistribution2()throws IOException{
     String filename = "integratednetwork.txt";
     File bioFile = new File(filename);
        BufferedReader in = new BufferedReader(new FileReader(bioFile));
        //in.readLine();
        String line;
        Set<String>Geneset=new HashSet<String>();
        List<String>Genelist=new ArrayList<String>();
        Set<String>Diseaseset=new HashSet<String>();
        List<String>Diseaselist=new ArrayList<String>();
        HashMap<String,Integer>freq=new HashMap<String,Integer>();
        HashMap<String,Integer>GeneDegree=new HashMap<String,Integer>();
         HashMap<String,Integer>DiseaseDegree=new HashMap<String,Integer>();
        while ((line = in.readLine()) != null) {
            String[] colums = line.split("\t");
            String G = colums[1];
            String D=colums[0];
            Geneset.add(G);
            Genelist.add(G);
            Diseaseset.add(D);
            Diseaselist.add(D);
}
       System.out.print(Geneset.size()+"\n");
       System.out.print(Genelist.size()+"\n");
       System.out.print(Diseaseset.size()+"\n");
       System.out.print(Diseaselist.size()+"\n");
        String outfile = "GenesDistribution.txt";
          try{
         BufferedWriter out = new BufferedWriter(new FileWriter(outfile));
             for (String key : Geneset) {
                  
                    out.write(key+"\t"+ Collections.frequency(Genelist, key)+"\n");
                    GeneDegree.put(key,Collections.frequency(Genelist, key));
        }
        out.close();
    }
     catch(IOException ex){
          ex.printStackTrace();
         System.out.println("Somthins gone horroblliy");
     }   String outfile2 = "DiseaseDistribution.txt";
          try{
         BufferedWriter out2 = new BufferedWriter(new FileWriter(outfile2));
             for (String key : Diseaseset) {
                  
                    out2.write(key+"\t"+ Collections.frequency(Diseaselist, key)+"\n");
                    DiseaseDegree.put(key,Collections.frequency(Diseaselist, key));
        }
        out2.close();
    }
     catch(IOException ex){
          ex.printStackTrace();
         System.out.println("Somthins gone horroblliy");
     }   
       
    return new NodeDegree(DiseaseDegree,GeneDegree);
         

}
public NodeDegree NodesDistributionn(Set<Pair>Network)throws IOException{
     // try{
        // BufferedWriter out = new BufferedWriter(new FileWriter("GeneDistribution.txt"));
        Set<String>Geneset=new HashSet<String>();
        List<String>Genelist=new ArrayList<String>();
        Set<String>Diseaseset=new HashSet<String>();
        List<String>Diseaselist=new ArrayList<String>();
        HashMap<String,Integer>freq=new HashMap<String,Integer>();
        HashMap<String,Integer>GeneDegree=new HashMap<String,Integer>();
         HashMap<String,Integer>DiseaseDegree=new HashMap<String,Integer>();
        for(Pair p:Network){
            Geneset.add(p.getG());
            Genelist.add(p.getG());
            Diseaseset.add(p.getD());
            Diseaselist.add(p.getD());
}
       System.out.print("Genes"+Geneset.size()+"\n");
       System.out.print("Genenlist"+Genelist.size()+"\n");
       System.out.print("Diseases"+Diseaseset.size()+"\n");
       System.out.print("Diseases"+Diseaselist.size()+"\n");
        String outfile = "GenesDistribution.txt";
         
           for (String key : Geneset) {
                  
                    //out.write(key+"\t"+ Collections.frequency(Genelist, key)+"\n");
                    GeneDegree.put(key,Collections.frequency(Genelist, key));
        }
       /* out.close();
    }
     catch(IOException ex){
          ex.printStackTrace();
         System.out.println("Somthins gone horroblliy");
     }   /*String outfile2 = "DiseaseDistribution.txt";
          try{
         BufferedWriter out2 = new BufferedWriter(new FileWriter(outfile2));*/
             for (String key : Diseaseset) {
                  
                    //out2.write(key+"\t"+ Collections.frequency(Diseaselist, key)+"\n");
                    DiseaseDegree.put(key,Collections.frequency(Diseaselist, key));
        }
        /*out2.close();
    }
     catch(IOException ex){
          ex.printStackTrace();
         System.out.println("Somthins gone horroblliy");
     }  */ 
        // System.out.print("number of diseases"+DiseaseDegree.size());
    return new NodeDegree(DiseaseDegree,GeneDegree);
         

}
public void AllNetworknodedistribution()throws IOException {

        try{
         BufferedWriter out = new BufferedWriter(new FileWriter("GeneDistribution.txt"));
         Map<String, Set<Pair>>Networks=new HashMap<String,Set<Pair>>();
         NodesDistribution ND=new NodesDistribution();
         
          PIAN network=new PIAN();
        Map<Pair,Double>LTP=network.producenetwork();
        Set<Pair>LTPset=new HashSet<Pair>();
        LTPset.addAll(LTP.keySet());
        MonogenicScoredNetwork MG=new MonogenicScoredNetwork();
        Map<Pair,Double>MGN=MG.ProduceMonogenicScoredNetwork();
         Set<Pair>MGset=new HashSet<Pair>();
         MGset.addAll(MGN.keySet());
        ELScoredData EL=new ELScoredData();
        Map<Pair,Double>HEL=EL.ProduceNetwork();
         Set<Pair>ELset=new HashSet<Pair>();
         ELset.addAll(HEL.keySet());
        OMIMScoredNetwork OMIM=new OMIMScoredNetwork();
        OMIM.parsingOMIM();
        OMIMasGoldStandard omim=new OMIMasGoldStandard();
        Map<Pair,Double>OMIMLLS=omim.produceOmimNetwork();
         Set<Pair>OMIMset=new HashSet<Pair>();
         OMIMset.addAll(OMIMLLS.keySet());
        UniprotscoredPIAN uniprot=new UniprotscoredPIAN();
        Map<Pair,Double>UniProtLLS=uniprot.produceUniprotNetwork();
         Set<Pair>UniProtset=new HashSet<Pair>();
         UniProtset.addAll(UniProtLLS.keySet());
        OverlapedDataScoredDatasets MC=new OverlapedDataScoredDatasets();
        Map<Pair,Double>MCLLS=MC.producenetwork();
         Set<Pair>MCset=new HashSet<Pair>();
           MCset.addAll(MCLLS.keySet());
            Networks.put("LTP_GS",LTPset);
            Networks.put("HEL_GS",ELset);
            Networks.put("MG_GS",MGset);
            Networks.put("OMIM_GS",OMIMset);
            Networks.put("UniProt_GS",UniProtset);
              Networks.put("MC_GS",MCset);
              for(String s:Networks.keySet()){
                  NodeDegree D=ND.NodesDistributionn(Networks.get(s));
                  Map<String,Integer>DDegree=D.getGeneDegree();
                    for(String d: DDegree.keySet()){
                       System.out.print(s+"\t"+d+"\t"+DDegree.get(d)+"\n");
                            out.append(s+"\t"+d+"\t"+DDegree.get(d)+"\n");
                    
                    
                    
                    
                    }
              }
        out.close();
    }
     catch(IOException ex){
          ex.printStackTrace();
         System.out.println("Somthins gone horroblliy");
     }  

}


}

