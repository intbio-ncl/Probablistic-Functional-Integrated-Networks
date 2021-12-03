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
with open ("SharedGeneswithnflationValues.txt", "r") as f:
    reader = csv.reader(f, delimiter = "\t")
    
    for i in reader:
     names.append(i[0])
     values.append(int(i[1]))
     numbers.append(float(i[2]))
     




df = pd.DataFrame({'Number of clusters': values,
                   "Average of shared genes": numbers}, index=names)
df.plot.bar(log = True)
plt.legend(loc='upper center', bbox_to_anchor=(0.5, -0.05),
          fancybox=True, shadow=True, ncol=5)
plt.show()
