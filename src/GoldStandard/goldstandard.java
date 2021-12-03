/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoldStandard;
import java.util.Set;
import pfinnetwork.*;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class goldstandard {
    
    public Set<Pair>  positive;
    public Set<String> genes;
     public Set<String> diseases;
    public goldstandard(Set<Pair> positive, Set<String> genes, Set<String>diseases){
        this.positive=positive;
        this.genes=genes;
        this.diseases=diseases;
    }
    public int numberOfNegative(){
    int gs = genes.size();
    int ps = positive.size();
    int ds = diseases.size();
    return (int) Math.pow(gs * 1.0, ds * 1.0) - ps;
    
    }
    public void setpositive(Set<Pair>positive){
    this.positive=positive;
    }
    public Set<Pair> getPositive(){
    return positive;
    }
    public void SetGenes(Set<String> genes){
    
    this.genes=genes;
    }
    public Set<String> getGenes(){
    return genes;
    }
public void SetDiseases(Set<String> diseases){
    
    this.diseases=diseases;
    }
    public Set<String> getDiseases(){
    return diseases;
    }
    
}


