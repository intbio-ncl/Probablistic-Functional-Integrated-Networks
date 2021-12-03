/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prediction;

/**
 *
 * @author aoeshagaedmalsobhe
 */
public class JaccardIndex {
    
    
 double Dtotalweight;
 double Gtotalweight;
 
 public JaccardIndex(double Dtotalweight, double Gtotalweight){
     this.Dtotalweight=Dtotalweight;
     this.Gtotalweight=Gtotalweight;
 }
 
 public double getDtotal(){
   
     return Dtotalweight;
 
 }
 public double getGtotalweight(){
     
     return Gtotalweight;
 }
}
