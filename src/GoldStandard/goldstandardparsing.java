/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoldStandard;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import pfinnetwork.*;
import java.util.*;
/**
 *
 * @author aoeshagaedmalsobhe
 */
public class goldstandardparsing {
    public goldstandard ParsingGS(Map<String,Set<Pair>>GS)throws IOException{
        
        Set<Pair>AllPairs= new HashSet<Pair>();
        Set<String>Allgenes=new HashSet<String>();
        Set<String>Alldiseases=new HashSet<String>();
        for(String i : GS.keySet()){
        for(Pair P : GS.get(i)){
            AllPairs.add(P);
            Allgenes.add(P.getG());
            Alldiseases.add(P.getD());
        }
        } //System.out.print(AllPairs.size()+"\t"+Alldiseases.size()+"\n");
        return new goldstandard(AllPairs,Allgenes,Alldiseases);
    
    }
}
