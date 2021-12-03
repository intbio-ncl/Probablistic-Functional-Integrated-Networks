/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prediction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pfinnetwork.Pair;
import java.io.*;
import pfinnetwork.BuildingBioGrid;


/**
 *
 * @author aoeshagaedmalsobhe
 */
public class GetMatrix {
    

    //********method to produce a matrix of scores for each pair*****/
    public File getMatrix(Map<String, Double> scores, Map<String, Double> relScores, int version, BuildingBioGrid bio) {
        //todo proper filename
        String filename = "V" + version + "Matrix" ;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

            Set<Pair> pairs = bio.getAllPairs();
            //to store the datasets
            Map<String, Set<Pair>> datasets = bio.getAlldatasets();

            System.out.println("Ranking datasets...");

            List<Double> vals = new ArrayList<Double>(relScores.values());
            Map<Integer, String> ordered = new HashMap<Integer, String>();//to store the datasets in ranked order

            Collections.sort(vals);//sort the values
            int ranking = 1;//the rank
            for (Double d : vals)//go through the values in sorted order
            {
                for (String s : relScores.keySet())//then look for the dataset with the score
                {
                    if (relScores.get(s).equals(d) && scores.keySet().contains(s)) {
                        ordered.put(ranking, s);
                        ranking++; 
                    }
                }
            }

            for (ranking = 1; ranking <= ordered.size(); ranking++) {
                System.out.println("Rank " + ranking + "\t" + ordered.get(ranking) + "\t" + scores.get(ordered.get(ranking)));
            }

            bw.write("Orf\tOrf\t");//the pair column spaces

            for (int rank = 1; rank <= ordered.size(); rank++) {
                String dataset = ordered.get(rank);
                bw.write(dataset + "\t");//print the dataset headers           
            }

            //now create the matrix
            System.out.println("writing matrix to " + filename);
            bw.write("\n");
            System.out.println("number of pairs " + pairs.size());
            for (Pair p : pairs)//for each pair get the dataset scores
            {
                bw.write(p.getD() + "\t" + p.getG() + "\t");

                for (int rank = 1; rank <= ordered.size(); rank++)//for each dataset
                {
                    String dataset = ordered.get(rank);
                    double score = 0.0; //initialise
                    Double d = scores.get(dataset);
                    Set<Pair> check = datasets.get(dataset);//the dataset

                    for (Pair checkpair : check) {

                        if (checkpair.equals(p))//if the pair is in the dataset
                        {
                            score = d;
                        }
                    }

                    if(rank < ordered.size())
                    {
                    bw.write(score + "\t");
                    }
                    else
                    {
                        bw.write(Double.toString(score));
                    }
                }
                bw.write("\n");
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println("done :)");
        return new File(filename);
    }
    //END OF GETMATRIX
}

