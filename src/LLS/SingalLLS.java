/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LLS;
import pfinnetwork.*;
import GoldStandard.*;
import java.util.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class SingalLLS {
    public double LogScore(goldstandard LTH, Set<Pair> data){
    
    Set<Pair> gdpairs = LTH.getPositive();
    int truePosData = pairsInGoldStandard(gdpairs, data);
    int allgenesgs = LTH.getGenes().size();
    int alldiseases = LTH.getDiseases().size();
    int truePosgs = gdpairs.size();
    int possiblePairsgs = (int)Math.pow(allgenesgs * 1.0, alldiseases * 1.0);
    int trueNeggs = LTH.numberOfNegative();
    int falsePosData = pairsNotInGoldStandard(LTH, data);
    int numberofPairsData = data.size();
   /* System.out.println("Total Genes in goldS \t \t " + allgenesgs);
    System.out.println("Possible Pairs in golds \t \t" + possiblePairsgs);
    System.out.println(" tru pos gs \t \t" + truePosgs);
    System.out.println("true neg gs \t \t" + trueNeggs);
    System.out.println(" ");
    System.out.println("number pairs in dataset \t \t" + numberofPairsData + "\tof\t" + data.size());
    System.out.println("True pos data \t \t" + truePosData);
    System.out.println("false pos data \t \t" + falsePosData);*/
    double PLE = 1.0 * truePosData/data.size();
    double notPLE = 1.0 * falsePosData/data.size();
    double PL = 1.0 * truePosgs/possiblePairsgs;
    double notPL = 1.0 * trueNeggs/possiblePairsgs;
    double PLE_over_notPLE = PLE / notPLE;
    double PL_over_notPL = PL / notPL;
    double LS = PLE_over_notPLE / PL_over_notPL;
    double LLS = Math.log(LS);
    /*System.out.println("PL\t\t" + PL);
    System.out.println("notPL\t\t" + notPL);
    System.out.println("PL_over_notPL\t\t" + PL_over_notPL);
    System.out.println("PLE\t\t" + PLE);
    System.out.println("notPLE\t \t" + notPLE);
    System.out.println("PLE/notPLE\t \t" + PLE_over_notPLE);
    System.out.println("LS \t \t" +LS);
    System.out.println("LLS \t \t" + LLS);*/
    
    
    return LLS;
}
   private static int pairsInGoldStandard(Set<Pair> gspairs, Set<Pair> data) {
   
   int counter = 0;
   for(Pair pair :data){
   
   if(gspairs.contains(pair)){
    counter++;
   }

   
   }
   return counter;
   }
  private static int pairsNotInGoldStandard(goldstandard LTH, Set<Pair> data){
   int counter = 0;
   for(Pair pair : data){
        if(!LTH.getPositive().contains(pair)
           && LTH.getGenes().contains(pair.getG())
                   && LTH.getDiseases().contains(pair.getD())){
                counter++;
   }
   }
   return counter;
   }
}

