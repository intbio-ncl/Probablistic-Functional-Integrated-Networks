import sqlite3                                                          
import pandas as pd
import csv
conn = sqlite3.connect("disgenet_2020.db")                              
c = conn.cursor()                                                       
results = c.execute("SELECT * FROM geneAttributes")                 
data = pd.DataFrame(results.fetchall())
print(data)
data.to_csv('geneattributes.tsv',sep = "\t", index = False)
