import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

data = pd.read_csv('overlap_Associations.txt', sep = "\t")
print (data)
x = data['(G-D Association,PUBMEDID)'].unique().tolist()
print(len(x))
heatmap1_data = pd.pivot_table(data, values='OVERLAPS', 
                     index=("(G-D Association,PUBMEDID)"), 
                     columns='DATASETS')
x = heatmap1_data.index.unique().tolist()
print(len(x))
#print(heatmap1_data)
#heatmap1_data.to_csv(r'Path where you want to store the exported CSV file\File Name.tsv')
#sns.heatmap(heatmap1_data)
sns.heatmap(heatmap1_data, cmap = 'Blues')
fig, ax = plt.subplots(figsize=(20,20))
#sns.heatmap(data.corr(), ax=ax)
plt.show()
