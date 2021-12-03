import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

data = pd.read_csv('cluster1JaccardSimilarity.txt', sep = "\t")
print (data)
x = data['D1'].unique().tolist()
print(len(x))
heatmap1_data = pd.pivot_table(data, values='Score', 
                     index=("D1"), 
                     columns='D2')
x = heatmap1_data.index.unique().tolist()
print(len(x))
#print(heatmap1_data)
#heatmap1_data.to_csv(r'Path where you want to store the exported CSV file\File Name.tsv')
#sns.heatmap(heatmap1_data)
#sns.heatmap(heatmap1_data, cmap = 'Blues')
#heatmap1_data.style.background_gradient(cmap='Blues')
sns.heatmap(heatmap1_data, annot=False,linewidth=5, vmin=0, vmax=1,cmap = 'Reds')
#sns.heatmap(heatmap1_data, annot=True)
#ax = sns.heatmap(heatmap1_data, linewidth=5, vmin=0, vmax=1,cmap = 'Blues')
#fig, ax = plt.subplots(figsize=(10,10))
#sns.heatmap(data.corr(), ax=ax)
plt.show()
