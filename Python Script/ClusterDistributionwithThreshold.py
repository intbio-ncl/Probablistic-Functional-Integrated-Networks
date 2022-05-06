import pandas as pd
import networkx as nx
import csv
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import pylab

data= pd.read_csv("ThresholdAnalysis 5.txt", delimiter = "\t")
f, ax = plt.subplots()
df_gb = data.groupby('weight').count()
medians = data.groupby(['Threshold'])['weight'].median()
Average = data.groupby(['Threshold'])['weight'].mean()
minumim = data.groupby(['Threshold'])['weight'].max()
caps = data.groupby(['Threshold'])['weight'].min()
##ax.set_yscale("log")
y_values = data["weight"].values
##data.boxplot(column='Size', by='Threshold', showmeans=True ,showfliers=True)"""
box_plot=sns.boxplot(data=data, x='Threshold', y='weight',showmeans=True,showfliers=False)

print(medians)
print(Average)
print(minumim)

ax = box_plot.axes
lines = ax.get_lines()
categories = ax.get_xticks()

for cat in categories:
    # every 4th line at the interval of 6 is median line
    # 0 -> p25 1 -> p75 2 -> lower whisker 3 -> upper whisker 4 -> p50 5 -> upper extreme value
    y = round(lines[4+cat*6].get_ydata()[0],1) 

    ax.text(
        cat, 
        y, 
        f'{y}', 
        ha='center', 
        va='center', 
        fontweight='bold', 
        size=5,
        color='white',
        bbox=dict(facecolor='#445A64'))
plt.xlabel('Threshold')
plt.ylabel('Integrated score(Edge weight)')
plt.title('Integrated score distribution at different threshold')
plt.show()
