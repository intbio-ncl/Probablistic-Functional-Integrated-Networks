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

public class Triple {
    String D;
    String G;
    String PubmedID;
    public Triple(String D, String G, String PubmedID){
     this.D =D;
     this.G = G;
     this.PubmedID =PubmedID;
    }
    void setD(String D){
    this.D = D;
    }
    void setG(String G){
    this.G = G;
    }
    public String getD(){
    return D;
    }
    public String getG(){
    return G;
    }
    
    public String getPubmedID(){
    return PubmedID;
    }
    
    public void setPubmedID(String PubmedID){
    this.PubmedID = PubmedID;
    }
    
    
    public String toString(){
     return D + "," + G+ ","+ PubmedID;
     
     }
    public int hashCode(){
        return D.hashCode() + G.hashCode()+ PubmedID.hashCode();
    }
    @Override
    public boolean equals(Object p){
    if (p == null){
        return false;
    }
    if(!(p instanceof Triple)){
        return false;}
    Triple p2 = (Triple) p;
    return D.equals(p2.getD()) && G.equals(p2.getG())&&PubmedID.equals(p2.getPubmedID()) ;
    }
    
    
    
}




