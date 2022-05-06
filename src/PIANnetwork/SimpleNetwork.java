/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIANnetwork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.*;
import pfinnetwork.*;

/**
 *
 * @author aoeshaalsobhe
 */
public class SimpleNetwork {
    public Map<Pair,Double>simplenetwork()throws IOException{
            Map<Pair,Double>Network=new HashMap<Pair,Double>();
            Set<Pair>associations=new HashSet<Pair>();
            
            PFINNetWork PFIN = new PFINNetWork();
            ParsingBioGrid P = PFIN.parsedata();
            BuildingBioGrid B = PFIN.buildbiodata(P);
            for(String i:B.getBio().keySet()){
                for(Pair p:B.getBio().get(i)){
                Network.put(p,1.0);
                }
            }
            System.out.print(Network.size());
             PrintWriter out = null;
        try {
            String outFileName = "SimpleNetwork.txt";
            out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
                for(Pair s : Network.keySet()) {
                
                out.println(s.getD() + "\t" +s.getG());
            }
            
            
            
            out.close();
        } finally {
            if (out != null) {
                out.close();
                
            }
        }
    
       return Network;     
    }
}
