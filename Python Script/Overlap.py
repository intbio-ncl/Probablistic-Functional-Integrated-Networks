import pandas as pd
import networkx as nx
import csv
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import pylab
names = []

data= pd.read_csv("overlap_Random.txt", delimiter = "\t")

data.boxplot(column='OVERLAP', by='DATASETS')

plt.xticks(
    rotation=45, 
    horizontalalignment='right',
    fontweight='light',
    fontsize='x-large'  
)

plt.xlabel('Source')
plt.ylabel('Overlap')
plt.title('Overlapping between data')
plt.show()
