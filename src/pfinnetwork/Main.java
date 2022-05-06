
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfinnetwork;

import LLS.*;
import java.util.*;
import PIANnetwork.*;
import Parsing.ParsingMonogenic;
import Mapper.*;
import Prediction.NetworkThreshold;
import Prediction.TenfoldCrossValidation;
import Prediction.LOOCV;
import DiseaseNetwork.NodesDistribution;





/**
 *
 * @author aoeshagaedmalsobhe
 */
public class Main {
    public static void main(String[] args) throws Exception {
         
          //IntegratedScoresCorrolation ed=new IntegratedScoresCorrolation();
          // ed.IntegratedScoreC();
           // AverageSharedDrugs ASD=new AverageSharedDrugs();
           // ASD.Jaccardsimilarity();
           // MappingOMIMtoUMLS omim=new MappingOMIMtoUMLS();
            //omim.UMLSTOOMIM();
            //DiseaseClusters DC=new DiseaseClusters();
            //HashMap<String,String>Names=DC.DiseaseNames();
           // Prediction p = new Prediction();
            //p.predict();
           // LLSScoresCorrolation lls=new LLSScoresCorrolation();
           // lls.LLSC();
         // NodesDistribution ND=new NodesDistribution();
         // ND.AllNetworknodedistribution();
  PIAN network=new PIAN();
   Map<Pair,Double>DGnetwork=network.producenetwork();
   //TenfoldCrossValidation CV=new TenfoldCrossValidation();
   //CV.crossvalidation(DGnetwork);
    // network.OverlapGSLTP();
     //Map<Pair,Double>DGnetwork=network.producenetwork();
    // LOOCV ls=new LOOCV();
    // ls.predictDGA(DGnetwork);
      // NetworkAnalysis NT=new NetworkAnalysis();
       // NT.networkthreshold(Network);
          /* RandomDiseaseGeneNetwork RDGN =new RandomDiseaseGeneNetwork();
           Map<Pair,Double>network2=RDGN.CreateRN2(Network);*/
        /*  UniprotscoredPIAN un= new UniprotscoredPIAN();
          Map<Pair,Double>Network=un.produceUniprotNetwork();
          NetworkAnalysis NT=new NetworkAnalysis();
          NT.clusterDistribution(Network);
           //Conversion c=new Conversion();
          //Map<String,String>Mapping=c.OMIMtoUMLS();
          /* AverageSharedGenes gg=new AverageSharedGenes();
           gg.Jaccardsimilarity();
           AverageSharedDrugs sd= new AverageSharedDrugs();
           sd.Jaccardsimilarity();
           Parsing Parse=new Parsing();
        HashMap<Pair,Set<String>>CGenes=Parse.CommonGenes();
        System.out.print(CGenes);
        SharedGenes SD=new SharedGenes();
        SD.sharedgenes("cluster189.txt",CGenes);
           
        UniprotscoredPIAN uniprot=new UniprotscoredPIAN();
        //uniprot.OverlapGSUniProt();
        Map<Pair,Double>network=uniprot.produceUniprotNetwork();
        TenfoldCrossValidation CV=new TenfoldCrossValidation();
      CV.crossvalidation(network);
        /* NetworkAnalysis NT=new NetworkAnalysis();
        NT.networkthreshold(network);
     // OMIMScoredNetwork OMIM=new OMIMScoredNetwork();
       // OMIM.parsingOMIM();
      OMIMasGoldStandard omim=new OMIMasGoldStandard();
     // omim.OverlapGSOMIM();
       Map<Pair,Double>network= omim.produceOmimNetwork();
       TenfoldCrossValidation CV=new TenfoldCrossValidation();
      CV.crossvalidation(network);
      /* NetworkAnalysis NT=new NetworkAnalysis();
       NT.networkthreshold(network);
          /*SimpleNetwork sn=new SimpleNetwork();
          Map<Pair,Double>networ=sn.simplenetwork();
       OverlapedDataScoredDatasets OVL=new OverlapedDataScoredDatasets();
       //OVL.OverlapGSMC();
        Map<Pair,Double>Network=OVL.producenetwork();
       /* NetworkAnalysis NT=new NetworkAnalysis();
        NT.clusterDistribution(Network);*/
     //  ELScoredData EL=new ELScoredData();
      // EL.OverlapGSEL();
     //  Map<Pair,Double>network=EL.ProduceNetwork();
      // TenfoldCrossValidation CV=new TenfoldCrossValidation();
     //  CV.crossvalidation(Network);
       //EL.LLSCorrolationEL();
       //EL.OverlapGSEL();
       //  NetworkAnalysis NT=new NetworkAnalysis();
        // NT.ClusterConnectednessAverage();
        
        
        //Map<Pair,Double>Thresholdnetwork=NT.threshold(network);
       // TenfoldCV CV=new TenfoldCV();
       //CV.crossvalidation(network);
        
        
        //  NetworkAnalysis NT=new NetworkAnalysis()
         //NT.networkthreshold(Network);
       // NT.MCLInflationvalue(network);
     // NT.inflatthreshold(Network);
     // NT.clusterDistribution(Network);
       /* Map<Pair,Double>network2=new HashMap<Pair,Double>();
        network2.putAll(network);
         Set<Pair>Edges2=new HashSet<Pair>();
         for(Pair p:network.keySet()){
            Edges2.add(p);
         }*/
         //ClusterAnalysis CA=new ClusterAnalysis();
        // Map<String,Set<String>>clusterset=CA.clusters("outfile1.5");
         //CA.ClusterAverage(network, clusterset);
    // MonogenicScoredNetwork MS=new MonogenicScoredNetwork();
     //MS.OverlapGSMG();
   // Map<Pair,Double>network=MS.ProduceMonogenicScoredNetwork();
   // System.out.print("network size"+"\t"+network.size());
    //TenfoldCrossValidation CV=new TenfoldCrossValidation();
      // CV.crossvalidation(network);
       //NetworkAnalysis NT=new NetworkAnalysis();
       // NT.networkthreshold(network);
     // NT.networkthreshold(network);
    // NT.inflatthreshold(network);
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
         }
       OverlapedDataScoredDatasets OVL=new OverlapedDataScoredDatasets();
      Map<Pair,Double>Network=OVL.producenetwork();
      OVL.OverlapGSMC();
       // LOOCV tcv=new LOOCV();
       //tcv.predictDGA(network);
      // NetworkAnalysis NT=new NetworkAnalysis();
      // NT.networkthreshold(Network);
       // NT.inflatthreshold(Network);
        // NT.MCLInflationvalue(Network);
        //NT.networkthreshold(Network);
       /* PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();*/
        //GDAScore GDAS=new GDAScore();
         //GDAS.colorrationbetweenscore();
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
       
    
       
       
        
           
       /* OMIMScoredNetwork OMIM=new OMIMScoredNetwork();
        OMIM.parsingOMIM();
       OMIMasGoldStandard omim=new OMIMasGoldStandard();
       omim.produceOmimNetwork();
       //OverlapbetweenELandMonogenic Overlap=new OverlapbetweenELandMonogenic ();
       // Overlap.overlapnetwork();*/
       
          
}
}