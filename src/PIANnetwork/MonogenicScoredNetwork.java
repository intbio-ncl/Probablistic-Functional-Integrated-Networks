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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import pfinnetwork.BuildingBioGrid;
import pfinnetwork.PFINNetWork;
import pfinnetwork.Pair;
import pfinnetwork.ParsingBioGrid;
import Parsing.ParsingMonogenic;

/**
 *
 * @author aoeshaalsobhe
 */
public class MonogenicScoredNetwork {
    public Map<Pair,Double> ProduceMonogenicScoredNetwork()throws IOException{
        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        BuildingBioGrid B = PFIN.buildbiodata(P);
        ParsingMonogenic Monogenic=new ParsingMonogenic();
        Set<Pair>monogenicdiseaes=Monogenic.parseMonogenicDGA();
        goldstandardparsing gs = new goldstandardparsing();
        Map<String,Set<Pair>>GS=new HashMap<String,Set<Pair>>();
        GS.put("GS",monogenicdiseaes);
        goldstandard gss =gs.ParsingGS(GS);
        Score S = new Score();
        Map<String,Double>LLSScore=S.ScoredData(gss, B);
         IntegratedScores IS = new IntegratedScores();
          Map<Pair,Double>IntegratedS=IS.doIntegration(LLSScore,LLSScore,Collections.<Double>reverseOrder(),B);
          Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("OMIMintegrated.txt",IntegratedS);
        /* Map<Pair,Double>IntegratedThreshold=new HashMap<Pair,Double>();
          for(Pair p: IntegratedS.keySet()){
              if(IntegratedS.get(p)>38){
                  IntegratedThreshold.put(p,IntegratedS.get(p));
              
              }
          }
          //Network net = new Network();
         // Map<Pair,Double>IntegratedS=net.networkPurn();
           System.out.print("size of network"+"\t"+IntegratedS.size()+"\n");*/
           return IntegratedS;
           
}
}
