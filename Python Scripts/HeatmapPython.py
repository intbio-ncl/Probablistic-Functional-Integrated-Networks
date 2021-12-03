import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd
data = pd.read_csv('cluster1093JaccardSimilarity.txt', sep = "\t")
#data = pd.DataFrame(data["Score"], columns=data["D1"], index = data["D2"])
print(data["Score"])
# plot heatmap
ax = sns.heatmap(data)

# turn the axis label
for item in ax.get_yticklabels():
    item.set_rotation(0)

for item in ax.get_xticklabels():
    item.set_rotation(90)

# save figure
plt.savefig('seabornPandas.png', dpi=100)
plt.show()
