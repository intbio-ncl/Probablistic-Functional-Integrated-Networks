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

public class BioGridList {
    String D;
    String G;
    String pubmedID;
    String source;
    String association;
    double GDAscore;
    String year;
    public BioGridList(String D, String G, String pubmedID, String source, String association,double GDAscore,String year){
     this.D =D;
     this.G =G;
     this.pubmedID = pubmedID;
     this.source = source;
     this.association = association;
     this.GDAscore=GDAscore;
     this.year=year;
    }
    public String getD(){
    return D;
    }
    public String getG(){
    return G;
    }
    public String getSource(){
    return source;
    }
    public String getPubmedID(){
        return pubmedID;
    }
    public String getAssociation(){
        return association;
    }
    public double getGDAscore(){
        return GDAscore;
    }
    public String getyear(){
    return year;
    }
    public String toString(){
     return D + "," + G+"," + pubmedID+","+source+","+ association ;
     
     }
    @Override
    public boolean equals(Object p){
    if (p == null){
        return false;
    }
    if(!(p instanceof BioGridList)){
        return false;}
    BioGridList p1 = (BioGridList) p;
    return D.equals(p1.getD()) && G.equals(p1.getG()) || D.equals(p1.getG()) && G.equals(p1.getD());
    }
    @Override
    public int hashCode(){
        return D.hashCode() + G.hashCode();
    }
    
    
}


