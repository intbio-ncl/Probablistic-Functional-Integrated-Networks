import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from scipy.stats import pearsonr
import seaborn as sns
from scipy.stats import spearmanr
data1 = pd.read_csv('ClusterAveragedistributionoverlap.txt', sep="\t")
data2=pd.read_csv('UNIPROTintegrated.txt', sep="\t")
data3=pd.read_csv('distribution.txt', sep="\t")
data4=pd.read_csv('LLS.txt', sep="\t")
print(data1)
bins_list = [0,1,2]
#plt.hist(data1["G"],alpha=0.9,log=True, bins=bins_list)
#plt.scatter(data1["D"], data1["G"])

plt.hist(data2["LLS"], label="LLS scores",log=True)
"""plt.hist(data2["F"], bins=100, alpha=0.7, label="Disease Degree Distribution",log=True)
plt.hist(data1["F"], bins=100, alpha=0.8, label="Gene Degree Distribution",log=True)
corr, _ = pearsonr(data1["A"], data1["B"])
print('Pearsons correlation: %.3f' % corr)
corr, _ = spearmanr(data1["A"], data1["B"])
print('Spearmans correlation: %.3f' % corr)
color_dict = dict({'A':'brown',
                  'B':'green',
                  'C': 'orange',
                  })
##sns.scatterplot(x="A", y="C", data=data1,palette=color_dict);"""

plt.scatter(data1["DGAS"], data1["S"],data1["Uni"],data1["MG"],data1["Overlap"],data1["EL"],data1["OMIM"])"""

plt.xlabel(" Edge weight", size=14)
plt.ylabel("Frequency", size=14)
plt.title("Edge weight distribution")

plt.show()
