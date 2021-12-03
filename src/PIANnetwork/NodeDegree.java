/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIANnetwork;
import java.util.*;

/**
 *
 * @author aoeshaalsobhe
 */
public class NodeDegree {
    Map<String,Integer>DiseaseDegree;
    Map<String,Integer>GeneDegree;
    public NodeDegree(Map<String,Integer>DiseaseDegree,Map<String,Integer>GeneDegree){
     this.DiseaseDegree=DiseaseDegree;
     this.GeneDegree=GeneDegree;
    }
    public Map<String,Integer>getDiseaseDegree(){
    return DiseaseDegree;
    }
    public Map<String,Integer> getGeneDegree(){
    return GeneDegree;
    }
    
}
