import pandas as pd
import matplotlib.pyplot as plt
from scipy.stats import pearsonr
import seaborn as sns
# prepare data
data= pd.read_csv('LLS.txt', sep="\t")
data[["Size", "LLS"]].corr()
corr, _ = pearsonr(data["LLS"], data["Size"])
print(corr)
# summarize
"""print('data1: mean=%.3f stdv=%.3f' % (mean(data1), std(data1)))
print('data2: mean=%.3f stdv=%.3f' % (mean(data2), std(data2)))"""
# plot
plt.scatter(data["Size"], data["LLS"])
plt.xlabel("dataset size")
plt.ylabel("LLS score")
plt.savefig("High resoltion.png",dpi=300)
plt.show()

