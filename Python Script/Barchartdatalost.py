import pandas as pd
import networkx as nx
import csv
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import pylab

data= pd.read_csv("ThresholdAnalysis 9.txt", delimiter = "\t")
#df = pd.DataFrame({"lost data":data["P"].tolist()
                  # }, index=data["Threshold"].tolist())
#ax=df.plot.bar(log = False)
plt.plot(data["Threshold"],data["Average"],marker='o')


plt.xlabel('Threshold')
plt.ylabel('Cluster Average')

#plt.legend(loc='upper center', bbox_to_anchor=(0.5, -0.05),
          #fancybox=True, shadow=True, ncol=5)
plt.show()
