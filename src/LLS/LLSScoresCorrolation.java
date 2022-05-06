/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LLS;
import PIANnetwork.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import pfinnetwork.Pair;


/**
 *
 * @author aoeshaalsobhe
 */
public class LLSScoresCorrolation {
    public void LLSC()throws IOException{
    PIAN network=new PIAN();
    Map<String,Double>LTP=network.LTPLLS();
    MonogenicScoredNetwork MG=new MonogenicScoredNetwork();
    Map<String,Double>MGN=MG.MGLLS();
    ELScoredData EL=new ELScoredData();
    Map<String,Double>HEL=EL.ELLLS();
    Set<String>Datasets=new HashSet<String>();
     OverlapedDataScoredDatasets MC=new OverlapedDataScoredDatasets();
         Map<String,Double>MCLLS=MC.MCLLS();
    Datasets.addAll(LTP.keySet());
    Datasets.addAll(MGN.keySet());
    Datasets.addAll(HEL.keySet());
     Datasets.addAll(MCLLS.keySet());
     PrintWriter out1 = null;
              try {
                    String outFileName = "LLSCorrolation.txt";
                    out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
                    out1.append("DatasetName"+"\t"+"LLS LTP_GS"+"\t"+"LLS HEL_GS"+"\t"+"LLS MG_GS"+"\t"+"LLS MC_GS"+"\n");
                     for(String s: Datasets){
                         //if(LTP.get(s)!=null&&HEL.get(s)!=null&&MGN.get(s)!=null&&MCLLS.get(s)!=null){
                        out1.append(s+"\t"+LTP.get(s)+"\t"+HEL.get(s)+"\t"+MGN.get(s)+"\t"+MCLLS.get(s)+"\n");
                 //}
                     }
                 out1.close();
              }
            catch (Exception e) {
            e.printStackTrace();
        } 
   
   }
    
    public void LLSC2()throws IOException{
        Set<String>Datasets=new HashSet<String>();
        OMIMScoredNetwork OMIM=new OMIMScoredNetwork();
        OMIM.parsingOMIM();
        OMIMasGoldStandard omim=new OMIMasGoldStandard();
         Map<String,Double>OMIMLLS=omim.OMIMLLS();
         UniprotscoredPIAN uniprot=new UniprotscoredPIAN();
         Map<String,Double>UniProtLLS=uniprot.UniProtLLS();
         OverlapedDataScoredDatasets MC=new OverlapedDataScoredDatasets();
         Map<String,Double>MCLLS=MC.MCLLS();
      
    Datasets.addAll(OMIMLLS.keySet());
    Datasets.addAll(UniProtLLS.keySet());
    Datasets.addAll(MCLLS.keySet());
    
     PrintWriter out1 = null;
              try {
                    String outFileName = "LLSCorrolation2.txt";
                    out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
                    out1.append("DatasetName"+"\t"+"LLSOMIM_GS"+"\t"+"UniProtLLS"+"\t"+"MCLLS"+"\n");
                     for(String s: Datasets){
                        out1.append(s+"\t"+OMIMLLS.get(s)+"\t"+UniProtLLS.get(s)+"\t"+MCLLS.get(s)+"\n");
                 }
                 out1.close();
              }
            catch (Exception e) {
            e.printStackTrace();
        } 
   
   }
    
    public void LLSDistribution()throws IOException{
    PIAN network=new PIAN();
    Map<String,Double>LTPScoredNetwork=network.LTPLLS();
    MonogenicScoredNetwork MG=new MonogenicScoredNetwork();
    Map<String,Double>MGScoredNetwork=MG.MGLLS();
    ELScoredData EL=new ELScoredData();
    Map<String,Double>HELScoredNetwork=EL.ELLLS();
    OMIMScoredNetwork OMIM=new OMIMScoredNetwork();
        OMIM.parsingOMIM();
        OMIMasGoldStandard omim=new OMIMasGoldStandard();
         Map<String,Double>OMIMScoredNetwork=omim.OMIMLLS();
         UniprotscoredPIAN uniprot=new UniprotscoredPIAN();
         Map<String,Double>UniProtScoredNetwork=uniprot.UniProtLLS();
         OverlapedDataScoredDatasets MC=new OverlapedDataScoredDatasets();
         Map<String,Double>MCScoredNetwork=MC.MCLLS();
      Map<String,Map<String,Double>>AllNetworks=new HashMap<String,Map<String,Double>>();
      AllNetworks.put("LTP_GS",LTPScoredNetwork);
      AllNetworks.put("HEL_GS",HELScoredNetwork);
      AllNetworks.put("MG_GS",MGScoredNetwork);
      AllNetworks.put("OMIM_GS",OMIMScoredNetwork);
      AllNetworks.put("UniProt_GS",UniProtScoredNetwork);
       AllNetworks.put("MC_GS",MCScoredNetwork);
    
     PrintWriter out1 = null;
              try {
                    String outFileName = "LLSDistribution.txt";
                    out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
                    out1.append("NetworkName"+"\t"+"datasets"+"\t"+"LLS"+"\n");
                     for(String s: AllNetworks.keySet()){
                         for(String t:AllNetworks.get(s).keySet()){
                            out1.append(s+"\t"+t+"\t"+AllNetworks.get(s).get(t)+"\n");
                 }}
                 out1.close();
              }
            catch (Exception e) {
            e.printStackTrace();
        } 
   
   }
    public void EdgeWeightDistribution()throws IOException{
    PIAN network=new PIAN();
    Map<Pair,Double>LTPScoredNetwork=network.producenetwork();
    MonogenicScoredNetwork MG=new MonogenicScoredNetwork();
    Map<Pair,Double>MGScoredNetwork=MG.ProduceMonogenicScoredNetwork();
    ELScoredData EL=new ELScoredData();
    Map<Pair,Double>HELScoredNetwork=EL.ProduceNetwork();
    OMIMScoredNetwork OMIM=new OMIMScoredNetwork();
        OMIM.parsingOMIM();
        OMIMasGoldStandard omim=new OMIMasGoldStandard();
         Map<Pair,Double>OMIMScoredNetwork=omim.produceOmimNetwork();
         UniprotscoredPIAN uniprot=new UniprotscoredPIAN();
         Map<Pair,Double>UniProtScoredNetwork=uniprot.produceUniprotNetwork();
         OverlapedDataScoredDatasets MC=new OverlapedDataScoredDatasets();
         Map<Pair,Double>MCScoredNetwork=MC.producenetwork();
      Map<String,Map<Pair,Double>>AllNetworks=new HashMap<String,Map<Pair,Double>>();
      AllNetworks.put("LTP_GS",LTPScoredNetwork);
      AllNetworks.put("HEL_GS",HELScoredNetwork);
      AllNetworks.put("MG_GS",MGScoredNetwork);
      AllNetworks.put("OMIM_GS",OMIMScoredNetwork);
      AllNetworks.put("UniProt_GS",UniProtScoredNetwork);
       AllNetworks.put("MC_GS",MCScoredNetwork);
    
     PrintWriter out1 = null;
              try {
                    String outFileName = "EdgeWeightDistribution.txt";
                    out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
                    out1.append("Probabilistic Integrated Networks"+"\t"+"datasets"+"\t"+"Integrated score"+"\n");
                     for(String s: AllNetworks.keySet()){
                         for(Pair t:AllNetworks.get(s).keySet()){
                            out1.append(s+"\t"+t+"\t"+AllNetworks.get(s).get(t)+"\n");
                 }}
                 out1.close();
              }
            catch (Exception e) {
            e.printStackTrace();
        } 
   
   }
    
    
    
    
    
    
    
    
    
    }

