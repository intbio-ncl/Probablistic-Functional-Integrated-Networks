/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pfinnetwork;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class Pair {

    String D;
    String G;
    String data;
    double score;
    public Pair(String D, String G){
    this.D = D;
    this.G = G;
    }
   /* public Pair( String data, double score){
    
    this.data = data;
    this.score = score;
    }*/
    void setD(String D){
    this.D = D;
    }
    void setG(String G){
    this.G = G;
    }
    /*void setdata(String data){
    this.data = data;
    
    }*/
   /* void setScore(double score){
    this.score = score;
    }*/
    @Override
     public String toString(){
     return D + "," + G;
     
     }
    public String getD(){
    return D;
    }
    public String getG(){
    return G ;
    }
    public String getdata(){
    return data ;
    
    }
    public double getScore(){
    return score;
    }
    @Override
    public int hashCode(){
        return D.hashCode() + G.hashCode();
    }
    
    @Override
    public boolean equals(Object anotherPair){
    if (this == anotherPair){
        return true;
    }
    
    if (anotherPair == null){
        return false;
    }
    if(!(anotherPair instanceof Pair)){
        return false;}
    Pair anotherPairCast = (Pair) anotherPair;
    return D.equals(anotherPairCast.getD()) && G.equals(anotherPairCast.getG())|| D.equals(anotherPairCast.getG()) && G.equals(anotherPairCast.getD());
    }
    
}


