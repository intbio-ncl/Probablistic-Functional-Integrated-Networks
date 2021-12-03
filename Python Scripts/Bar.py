import pandas as pd
import networkx as nx
import csv
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
names = []
values = []
numbers = []
attributes=[]
evidences=[]
with open ("CuratedDataStats.txt", "r") as f:
    reader = csv.reader(f, delimiter = "\t")
    
    for i in reader:
     names.append(i[0])
     values.append(int(i[1]))
     numbers.append(int(i[2]))
     attributes.append(int(i[3]))
     evidences.append(int(i[4]))




df = pd.DataFrame({'Genes': values,
                   "Diseases": numbers, "Associations":attributes, "Evidences":evidences}, index=names)
df.plot.barh(log = True)
plt.legend(loc='upper center', bbox_to_anchor=(0.5, -0.05),
          fancybox=True, shadow=True, ncol=5)
plt.show()
