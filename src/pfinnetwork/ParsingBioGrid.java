/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pfinnetwork;
import java.util.*;
/**
 *
 * @author aoeshagaedmalsobhe
 */

public class ParsingBioGrid {
Set<String> ids = new HashSet<String>();
    Set<String>Sources = new HashSet<String>();
    List<BioGridList> Lines = new ArrayList<BioGridList>();
     public ParsingBioGrid(Set<String> ids, Set<String>Sources,List<BioGridList> Lines){
     this.ids=ids;
       this.Sources = Sources;
     this.Lines =Lines;
     }
     public void setIds(Set<String> ids ){
     this.ids = ids;
     }
     public void setSource(Set<String> Sources){
         this.Sources = Sources;
     }
     public Set<String> getIds(){
     return ids;
     }
     public Set<String> getSource(){
     return Sources;
     }
     public void setLines(List<BioGridList> Lines){
     this.Lines = Lines;
     }
     public List<BioGridList> getLines(){
     return Lines;
     }
}


