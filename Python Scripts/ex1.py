import pandas as pd
import networkx as nx
import csv
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
names = []
values = []
with open ("pairs.csv", "r") as f:
    reader = csv.reader(f, delimiter = ",")
    
    for i in reader:
     names.append(i[0])
     values.append(i[1])

print(values)     
plt.xticks(rotation=90)
plt.xticks((range(len(names))),names, rotation=90)
plt.tight_layout()
plt.bar(names,values, log = True)

plt.show()

plt.show()
