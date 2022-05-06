import pandas as pd
import networkx as nx
import csv
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import pylab

data= pd.read_csv("ClustersDistribution.txt", delimiter = "\t")
data.boxplot(column='Average',showmeans=True,showfliers=False, return_type='axes');
plt.show()

