import sqlite3                                                          
import pandas as pd
import csv
conn = sqlite3.connect("export.db")                              
cursor = conn.cursor()

cursor.execute("SELECT * FROM sqlite_master;")
print(cursor.fetchall())
