/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import pfinnetwork.*;
import java.util.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */

public class Integrationlogger {
    public void logToFile(String fileName, Map<Pair, Double> LLS)throws IOException{
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName))));
        for(Map.Entry<Pair, Double> row : LLS.entrySet()){
            
            
            pw.println(row.getKey().getD() + "\t" + row.getKey().getG()+ "\t" + row.getValue());
        
        }
        pw.flush();
        pw.close();
    }
    
}

