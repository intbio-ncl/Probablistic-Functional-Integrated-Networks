/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIANnetwork;

import GoldStandard.goldstandard;
import GoldStandard.goldstandardparsing;
import Integration.IntegratedScores;
import Integration.Integrationlogger;
import LLS.Score;
import java.io.IOException;
import java.util.Collections;
import java.util.*;
import pfinnetwork.BuildingBioGrid;
import pfinnetwork.PFINNetWork;
import pfinnetwork.Pair;
import pfinnetwork.ParsingBioGrid;

/**
 *
 * @author aoeshaalsobhe
 */
public class OMIMasGoldStandard {
    public Map<Pair,Double> produceUniprotNetwork()throws IOException{
       

        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        BuildingBioGrid B = PFIN.buildbiodataUniprotGS(P);
        PFIN.writetoFile(B) ;
       // PFIN.writetoFile2(B);
        goldstandardparsing gs = new goldstandardparsing();
        OMIMScoredNetwork OMIM=new OMIMScoredNetwork();
        Set<Pair>OMIMdatasets=OMIM.parsingOMIM();
        System.out.print(OMIMdatasets.size());
        Map<String,Set<Pair>>OMIMgoldStandard=new HashMap<String,Set<Pair>>();
        OMIMgoldStandard.put("OMIM", OMIMdatasets);
        goldstandard gss =gs.ParsingGS(OMIMgoldStandard);
        Score S = new Score();
        Map<String,Double>LLSScore=S.ScoredData(gss, B);
         IntegratedScores IS = new IntegratedScores();
          Map<Pair,Double>IntegratedS=IS.doIntegration(LLSScore,LLSScore,Collections.<Double>reverseOrder(),B);
          Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("OMIMintegrated.txt",IntegratedS);
          //Network net = new Network();
         // Map<Pair,Double>IntegratedS=net.networkPurn();
           System.out.print("size of network"+"\t"+IntegratedS.size()+"\n");
           return IntegratedS;
           
}
}
