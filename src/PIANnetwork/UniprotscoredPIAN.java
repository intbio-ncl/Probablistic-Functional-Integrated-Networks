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
import LLS.LTPSCores;
import LLS.Score;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import pfinnetwork.BuildingBioGrid;
import pfinnetwork.PFINNetWork;
import pfinnetwork.Pair;
import pfinnetwork.ParsingBioGrid;

/**
 *
 * @author aoeshaalsobhe
 */
public class UniprotscoredPIAN {
    public Map<Pair,Double> produceUniprotNetwork()throws IOException{
       

        PFINNetWork PFIN = new PFINNetWork();
        ParsingBioGrid P = PFIN.parsedata();
        BuildingBioGrid B = PFIN.buildbiodataUniprotGS(P);
        PFIN.writetoFile(B) ;
//        PFIN.writetoFile2(B);
        B.getBio().remove("UNIPROT");
        goldstandardparsing gs = new goldstandardparsing();
        goldstandard gss =gs.ParsingGS(B.getGS());
        Score S = new Score();
        Map<String,Double>LLSScore=S.ScoredData(gss, B);
         IntegratedScores IS = new IntegratedScores();
          Map<Pair,Double>IntegratedS=IS.doIntegration(LLSScore,LLSScore,Collections.<Double>reverseOrder(),B);
          Integrationlogger ILogger = new Integrationlogger();
         ILogger.logToFile("UNIPRPTintegrated.txt",IntegratedS);
          //Network net = new Network();
         // Map<Pair,Double>IntegratedS=net.networkPurn();
           System.out.print("size of network"+"\t"+IntegratedS.size()+"\n");
            /*Map<Pair,Double>IntegratedS2=new HashMap<Pair,Double>();
             for(Pair p2:IntegratedS.keySet()){
        
            if(IntegratedS.get(p2)>19){
            
                IntegratedS2.put(p2, IntegratedS.get(p2));
            
            }
        }
             
             System.out.print(IntegratedS2.size()+"\n");*/
      return IntegratedS;     
}
}