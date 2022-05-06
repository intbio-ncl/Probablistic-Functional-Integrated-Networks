import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from scipy.stats import pearsonr
import seaborn as sns
from scipy.stats import spearmanr
import matplotlib.ticker as mtick
from matplotlib.ticker import PercentFormatter
data1 = pd.read_csv('PubMedIDAssociations.txt', sep="\t")
data2=pd.read_csv('UNIPROTintegrated.txt', sep="\t")
data3=pd.read_csv('distribution.txt', sep="\t")
data4=pd.read_csv('LLS.txt', sep="\t")
print(data1)
bins_list = [0,1,2]
plt.hist(data1["Size"],alpha=0.9,log=True)
#plt.scatter(data1["D"], data1["G"])

#ax=plt.hist(data1["Size"],weights=np.ones(len(data1["Size"])) / len(data1["Size"]),range=(1,100),log=True)
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

#plt.scatter(data1["DGAS"], data1["S"],data1["Uni"],data1["MG"],data1["Overlap"],data1["EL"],data1["OMIM"])"""

plt.xlabel("Dataset size (number of associations)", size=14)
plt.ylabel("Number of datsets", size=14)

plt.title("Datasets Size")
#plt.gca().yaxis.set_major_formatter(PercentFormatter(1))
plt.show()
