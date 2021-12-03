/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsing;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.*;
import pfinnetwork.*;


/**
 *
 * @author aoeshaalsobhe
 */
public class ParsingCOMAGC {
    public void COMAGCfile()throws Exception{
        File file = new File("CoMAGC.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
        .newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        org.w3c.dom.Document document = documentBuilder.parse(file);
        NodeList list = document.getElementsByTagName("annotation_unit");
        //String usr = document.getElementsByTagName("CGE value").item(0).getTextContent();
        //String pwd = document.getElementsByTagName("password").item(0).getTextContent();
        System.out.print(list);
        for (int temp = 0; temp < list.getLength(); temp++) {

              Node node = list.item(temp);

              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;
                  String id = element.getAttribute("id");
                 // System.out.print(element);
              }
        }
       
       
       }
    
    

  
  public void xml() throws Exception{

      // Instantiate the Factory
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      try {

          // optional, but recommended
          // process XML securely, avoid attacks like XML External Entities (XXE)
          //dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

          // parse XML file
          DocumentBuilder db = dbf.newDocumentBuilder();

          Document doc = db.parse(new File("CoMAGC.xml"));

          // optional, but recommended
          // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
          doc.getDocumentElement().normalize();
           Nodeattributes p=new Nodeattributes();
             Map<String,String>Diseaseattributes=p.parsediseaseattributes();
          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
          System.out.println("------");
          Set<String>Diseases=new HashSet<String>();
          Set<String>Genes=new HashSet<String>();
          Set<Triple>edges=new HashSet<Triple>();
          // get <staff>
          NodeList list = doc.getElementsByTagName("annotation_unit");
          PrintWriter out1 = null;
        try {
            String outFileName = "COMAGC.txt";
            out1 = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
            out1.append("Cancer_type"+"\t"+"Cancer_id"+"\t"+"Pubmed_id"+"\t"+"Gene"+"\t"+"Cancer_term"+"\n");
          for (int temp = 0; temp < list.getLength(); temp++) {
                   //System.out.print(list.item(0));
              Node node = list.item(temp);

              //if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;
                 
                  // get staff's attribute
                  String type = element.getAttribute("cancer_type");
                  String id = element.getAttribute("id");
                  // get text
                  NodeList sentence= element.getElementsByTagName("sentence");
                  NodeList gene = element.getElementsByTagName("gene");
                  NodeList Cancerterm = element.getElementsByTagName("cancer_term");
                 //System.out.print(Cancerterm.item(0).getNodeName());
                  String pubmedid=sentence.item(0).getAttributes().getNamedItem("pmid").getTextContent();
                  String geneid= gene.item(0).getTextContent();
                   String cancername=Cancerterm.item(0).getTextContent();
                  //System.out.println("Cancertype :"+"\t" + "id"+"\t"+"pmid"+"\t"+"gene"+"cancerterm"+"\n");
                  out1.append(type+"\t"+id+"\t"+pubmedid+"\t"+geneid+"\t"+cancername+"\n");
                  Diseases.add(type);
                  Genes.add(geneid);
                   if(Diseaseattributes.containsKey(cancername)){
                     edges.add(new Triple(Diseaseattributes.get(cancername),geneid,pubmedid));
                    // edges.add(new Triple(id,geneid,pubmedid));
                  //System.out.printf("Salary [Currency] : %,.2f [%s]%n%n", Float.parseFloat(salary), currency);
}
              //}
          }
                  System.out.print(Diseases.size()+"\n");
                  System.out.print(Genes.size()+"\n");
                  System.out.print(edges.size()+"\n");
           out1.close();
      }
              finally {
            if (out1 != null) {
                out1.close();
                
            }
        }

      } catch ( IOException e) {
          e.printStackTrace();
      }
       
  }
  
  

}


