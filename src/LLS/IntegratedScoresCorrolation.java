/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LLS;

import PIANnetwork.ELScoredData;
import PIANnetwork.MonogenicScoredNetwork;
import PIANnetwork.OMIMScoredNetwork;
import PIANnetwork.OMIMasGoldStandard;
import PIANnetwork.OverlapedDataScoredDatasets;
import PIANnetwork.PIAN;
import PIANnetwork.UniprotscoredPIAN;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import pfinnetwork.*;

/**
 *
 * @author aoeshaalsobhe
 */
public class IntegratedScoresCorrolation {
    public void IntegratedScoreC()throws IOException{
        PIAN network=new PIAN();
        Map<Pair,Double>LTP=network.producenetwork();
        MonogenicScoredNetwork MG=new MonogenicScoredNetwork();
        Map<Pair,Double>MGN=MG.ProduceMonogenicScoredNetwork();
        ELScoredData EL=new ELScoredData();
        Map<Pair,Double>HEL=EL.ProduceNetwork();
        OMIMScoredNetwork OMIM=new OMIMScoredNetwork();
        OMIM.parsingOMIM();
        OMIMasGoldStandard omim=new OMIMasGoldStandard();
        Map<Pair,Double>OMIMLLS=omim.produceOmimNetwork();
        UniprotscoredPIAN uniprot=new UniprotscoredPIAN();
        Map<Pair,Double>UniProtLLS=uniprot.produceUniprotNetwork();
        OverlapedDataScoredDatasets MC=new OverlapedDataScoredDatasets();
        Map<Pair,Double>MCLLS=MC.producenetwork();
        Set<Pair>edges=new HashSet<Pair>();
        edges.addAll(LTP.keySet());
        edges.addAll(MGN.keySet());
        edges.addAll(HEL.keySet());
    
     PrintWriter out1 = null;
              try {
                    String outFileName = "ALLDGAScore.txt";
                    out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
                    out1.append("Pair"+"\t"+"LTP_GS"+"\t"+"HEL_GS"+"\t"+"MG_GS"+"\t"+"OMIM_GS"+"\t"+"UniProt_GS"+"\t"+"MC_GS"+"\n");
                     for(Pair s: edges){
                         if(LTP.get(s)!=null&&HEL.get(s)!=null&&MGN.get(s)!=null&&OMIMLLS.get(s)!=null&&UniProtLLS.get(s)!=null&&MCLLS.get(s)!=null){
                        out1.append(s+"\t"+LTP.get(s)+"\t"+HEL.get(s)+"\t"+MGN.get(s)+"\t"+OMIMLLS.get(s)+"\t"+UniProtLLS.get(s)+"\t"+MCLLS.get(s)+"\n");
                 }}
                 out1.close();
              }
            catch (Exception e) {
            e.printStackTrace();
        } 
   
   }
}
