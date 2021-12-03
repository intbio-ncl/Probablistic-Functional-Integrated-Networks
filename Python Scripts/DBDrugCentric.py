import sqlite3                                                          
import pandas as pd
import csv
conn = sqlite3.connect("example_query.sql")                              
c = conn.cursor()                                                       
results = c.execute("SELECT * FROM geneDiseaseNetwork")                 
data = pd.DataFrame(results.fetchall())
print(data)
#data.to_csv(r'Path where you want to store the exported CSV file\t//Types.tsv',sep = "\t", index = False)

