
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfinnetwork;

import LLS.GDAScore;
import java.util.*;
import PIANnetwork.*;
import Parsing.ParsingMonogenic;
import Mapper.*;




/**
 *
 * @author aoeshagaedmalsobhe
 */
public class Main {
    public static void main(String[] args) throws Exception {
           // AverageSharedDrugs ASD=new AverageSharedDrugs();
           // ASD.Jaccardsimilarity();
            
            //DiseaseClusters DC=new DiseaseClusters();
            //HashMap<String,String>Names=DC.DiseaseNames();
           // Prediction p = new Prediction();
            //p.predict();
           // PIAN network=new PIAN();
          // Map<Pair,Double>Network=network.producenetwork();
          /* RandomDiseaseGeneNetwork RDGN =new RandomDiseaseGeneNetwork();
           Map<Pair,Double>network2=RDGN.CreateRN2(Network);*/
          // UniprotscoredPIAN un= new UniprotscoredPIAN();
         // Map<Pair,Double>Network=un.produceUniprotNetwork();
           //Conversion c=new Conversion();
          //Map<String,String>Mapping=c.OMIMtoUMLS();
          /* AverageSharedGenes gg=new AverageSharedGenes();
           gg.Jaccardsimilarity();
           AverageSharedDrugs sd= new AverageSharedDrugs();
           sd.Jaccardsimilarity();
           /*Parsing Parse=new Parsing();
        HashMap<Pair,Set<String>>CGenes=Parse.CommonGenes();
        System.out.print(CGenes);
        SharedGenes SD=new SharedGenes();
        SD.sharedgenes("cluster189.txt",CGenes);*/
           
         // UniprotscoredPIAN uniprot=new UniprotscoredPIAN();
         //Map<Pair,Double>network=uniprot.produceUniprotNetwork();
        // OMIMScoredNetwork OMIM=new OMIMScoredNetwork();
        //OMIM.parsingOMIM();
        // OMIMasGoldStandard omim=new OMIMasGoldStandard();
       // Map<Pair,Double>network= omim.produceUniprotNetwork();
          /*SimpleNetwork sn=new SimpleNetwork();
          Map<Pair,Double>networ=sn.simplenetwork();*/
         //OverlapedDataScoredDatasets OVL=new OverlapedDataScoredDatasets();
         //Map<Pair,Double>Network=OVL.producenetwork();*/
         /*ELScoredData EL=new ELScoredData();
         Map<Pair,Double>network=EL.ProduceNetwork();
        NetworkAnalysis NT=new NetworkAnalysis();
        NT.networkthreshold(network);
       // NT.MCLInflationvalue(network);
       /* Map<Pair,Double>network2=new HashMap<Pair,Double>();
        network2.putAll(network);
         Set<Pair>Edges2=new HashSet<Pair>();
         for(Pair p:network.keySet()){
            Edges2.add(p);
         }*/
         //ClusterAnalysis CA=new ClusterAnalysis();
        // Map<String,Set<String>>clusterset=CA.clusters("outfile1.5");
         //CA.ClusterAverage(network, clusterset);*/
      // MonogenicScoredNetwork MS=new MonogenicScoredNetwork();
      // Map<Pair,Double>network=MS.ProduceMonogenicScoredNetwork();
         // NetworkAnalysis NT=new NetworkAnalysis();
         // NT.MCLInflationvalue(network);
        //NT.networkthreshold(network);
          /* NodesDistribution N= new NodesDistribution();
        NodeDegree ND=N.NodesDistributionn(Edges2);
        Map<String,Integer>GeneDegree=ND.getGeneDegree();
        Map<String,Integer>DiseaseDegree=ND.getDiseaseDegree();
         for(Pair p:network.keySet()){
             if(DiseaseDegree.get(p.getD())==1&&GeneDegree.get(p.getG())==1){
                network2.remove(p);
             
             
             }
             if(DiseaseDegree.get(p.getD())<1&&GeneDegree.get(p.getG())==1){
                network2.remove(p);
             
             
             }
         }*/
        //OverlapedDataScoredDatasets OVL=new OverlapedDataScoredDatasets();
        // Map<Pair,Double>Network=OVL.producenetwork();
       // LOOCV tcv=new LOOCV();
       //tcv.predictDGA(network);
        //NetworkAnalysis NT=new NetworkAnalysis();
        // NT.MCLInflationvalue(Network);
        //NT.networkthreshold(Network);
       /* PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();*/
         GDAScore GDAS=new GDAScore();
         GDAS.colorrationbetweenscore();
        //ParsingGAD pGAD=new ParsingGAD();
        //pGAD.parseGAD();
       /* Ploysearch poly=new Ploysearch();
        poly.parsedata();
        Conversion c=new Conversion();
        c.MeSHtoUMLS();*/
        //ParsingCOMAGC com=new ParsingCOMAGC();
        //com.xml();
        //Diseaseattributes p=new Diseaseattributes();
       // p.parsediseaseattributes();
       
    
       
        /* MonoGenenicDisease M=new MonoGenenicDisease();
         Map<Pair,Double>Network=M.producenetwork();*/
          
        /*OMIMScoredNetwork OMIM=new OMIMScoredNetwork();
        OMIM.parsingOMIM();
       OMIMasGoldStandard omim=new OMIMasGoldStandard();
       omim.produceUniprotNetwork();*/
        
       
          
}
}