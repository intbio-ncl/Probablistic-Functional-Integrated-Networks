/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pfinnetwork;
import java.util.*;
import java.io.*;
/**
 *
 * @author aoeshagaedmalsobhe
 */

public class BuildingBioGrid {
    Set<String> Allgenes;
    Set<String> Alldiseases;
    Map<String, Set<Pair>> GS = new HashMap<String, Set<Pair>>();
    Map<String, Set<Pair>> bio = new HashMap<String, Set<Pair>>();
    Map<String, Set<Pair>> Alldatasets = new HashMap<String, Set<Pair>>();
    Set<Pair> allPairs;
    Map<String, Map<Pair,String>> Asso = new HashMap<String, Map<Pair,String>>();
    public BuildingBioGrid(Map<String, Set<Pair>> bio, Set<String> Allgenes,Set<String> Alldiseases, Set<Pair> allPairs,Map<String, Map<Pair,String>> Asso,Map<String,Set<Pair>>GS,Map<String,Set<Pair>>Alldatasets) {
        this.Allgenes = Allgenes;
         this.Alldiseases = Alldiseases;
        this.allPairs = allPairs;
        this.bio = bio;
        this.Asso=Asso;
        this.GS=GS;
        this.Alldatasets=Alldatasets;
    }

    public void setAllgenes(Set<String> Allgenes) {
        this.Allgenes = Allgenes;
    }
    public void setAlldiseases(Set<String> Alldiseases) {
        this.Alldiseases = Alldiseases;
    }

    public void setAllPairs(Set<Pair> allPairs) {
        this.allPairs = allPairs;
    }

    public void setBio(Map<String, Set<Pair>> bio) {
        this.bio = bio;
    }
    public void setAlldatasets(Map<String, Set<Pair>> Alldatasets) {
        this.Alldatasets = Alldatasets;
    }
    public void setGS(Map<String, Set<Pair>> GS) {
        this.GS = GS;
    }

    public Set<String> getAllgenes() {
        return Allgenes;
    }
    public Set<String> getAlldiseases() {
        return Alldiseases;
    }
    public Set<Pair> getAllPairs() {
        return allPairs;
    }

    public Map<String, Set<Pair>> getBio() {
        return bio;
    }
    public Map<String, Set<Pair>> getAlldatasets() {
        return Alldatasets;
    }
    public Map<String, Set<Pair>> getGS() {
        return GS;
    }
   public Map<String, Map<Pair,String>> getAsso() {
        return Asso;
    }

    public int numDatasets() {
        return bio.size();

    }
}

