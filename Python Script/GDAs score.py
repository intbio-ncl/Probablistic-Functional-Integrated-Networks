import pandas as pd
import matplotlib.pyplot as plt
from scipy.stats import pearsonr
import seaborn as sns
# prepare data
data= pd.read_csv('GDAScore.txt', sep="\t")
##data[["LTPScore", "HELScore"]].corr()
##corr, _ = pearsonr(data["LTPScore"], data["HELScore"])
##print(data.corr())
# summarize
"""print('data1: mean=%.3f stdv=%.3f' % (mean(data1), std(data1)))
print('data2: mean=%.3f stdv=%.3f' % (mean(data2), std(data2)))"""
# plot
print(data["DGA"])
plt.scatter(data["DGA"], data["DGARedundancy"])
plt.xlabel('GDAs Score assigned by DisGeNET')
plt.ylabel('GDAs Score with duplicate data')
##plt.title('DisGeNET GDAs Scores')
plt.show()
