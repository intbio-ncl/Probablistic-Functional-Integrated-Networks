import pandas as pd
import matplotlib.pyplot as plt
from scipy.stats import pearsonr
import seaborn as sns
# prepare data
data= pd.read_csv('LLSCEL.txt', sep="\t")
data[["EL", "LLS"]].corr()
corr, _ = pearsonr(data["LLS"], data["EL"])
print(corr)
# summarize
"""print('data1: mean=%.3f stdv=%.3f' % (mean(data1), std(data1)))
print('data2: mean=%.3f stdv=%.3f' % (mean(data2), std(data2)))"""
# plot
plt.scatter(data["EL"], data["LLS"])
plt.xlabel("Percentage of High Evidenc Level")
plt.ylabel("LLS score")
plt.show()
