import pandas as pd
import matplotlib.pyplot as plt
data1 = pd.read_csv('ClustersDistribution.txt', sep="\t", header=None)
print(data1)
data2 = data1.T
print(data2)
print(data2[1])
boxplot = data2.boxplot()
