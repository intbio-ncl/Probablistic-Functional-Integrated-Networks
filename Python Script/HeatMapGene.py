import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
data = pd.read_csv('cluster364JaccardDrugSimilarity 2.txt', sep = "\t")
result = data.pivot(index='D1', columns='D2', values='Score')
print(result)
sns.heatmap(result,cmap='RdYlGn_r', linewidths=0.1, annot=True)
plt.show()
