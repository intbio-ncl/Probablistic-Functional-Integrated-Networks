import sqlite3                                                          
import pandas as pd
import csv
conn = sqlite3.connect("disgenet_2014.db")                              
c = conn.cursor()
##result=c.execute("SELECT name FROM sqlite_master")
results = c.execute("SELECT * FROM geneDiseaseNetwork WHERE source IN ('CTD_human','PSYGENET','UNIPROT', 'ORPHANET','GENOMICS_ENGLAND','CGI', 'CLINGEN');")                 
data = pd.DataFrame(results.fetchall())
print(data)
data.to_csv(r'database.tsv',sep = "\t", index = False)
