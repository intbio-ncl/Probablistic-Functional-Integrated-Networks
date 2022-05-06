import pandas as pd
import networkx as nx
import csv
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
DataSets = []
overlap = []
with open ("OverlapCuratedData.csv", "r") as f:
    reader = csv.reader(f, delimiter = ",")
    
    for i in reader:
     DataSets.append(i[0])
     overlap.append(int(i[1]))


print(overlap)
print(DataSets)
plt.xticks(rotation=90)
plt.xticks((range(len(DataSets))),DataSets, rotation=90)
plt.tight_layout()
plt.bar(DataSets,overlap,log = True  )

plt.xlabel('Curated Data sources')
plt.ylabel('Number of Overlapped Disease-Gene Associations')
plt.title('Overlap Between Curated Data Sources')

plt.show()

