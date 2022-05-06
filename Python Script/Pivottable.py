import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

data = pd.read_csv('Pivot.txt', sep = "\t")
print (data)
df2  = pd.pivot_table(data,values="value", 
                     index="genes", 
                     columns='Datasets')
print(df2)
sns.heatmap(df2)
plt.show()
