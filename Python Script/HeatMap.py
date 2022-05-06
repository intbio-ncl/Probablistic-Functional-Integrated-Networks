import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

data = pd.read_csv('Individualstudiesoverlapassociations 2.txt', sep = "\t")
print (data)
x = data['Datasets'].unique().tolist()
print(len(x))
heatmap1_data = pd.pivot_table(data, values='overlap', 
                     index=("Datasets"), 
                     columns='associations')
x = heatmap1_data.index.unique().tolist()
print(heatmap1_data)
#print(heatmap1_data)
#heatmap1_data.to_csv(r'Path where you want to store the exported CSV file\File Name.tsv')
#sns.heatmap(heatmap1_data)
#sns.heatmap(heatmap1_data, cmap = 'Blues')
#heatmap1_data.style.background_gradient(cmap='Blues')
sns.heatmap(heatmap1_data, annot=True)
#sns.heatmap(heatmap1_data, annot=True)
ax = sns.heatmap(heatmap1_data, linewidth=5)
#fig, ax = plt.subplots(figsize=(10,10))
#sns.heatmap(data.corr(), ax=ax)
plt.show()
