import pandas as pd
import matplotlib.pyplot as plt
data1 = pd.read_csv('UniProtscoredThreshold.txt', sep="\t")
plt.plot(data1['cluster'], data1['Average'], color='red', marker='o')
plt.title('The cohesiveness of the network clusters with different edge weight threshold', fontsize=10)
plt.xlabel('edge weight threshold', fontsize=10)
plt.ylabel('The average of cohesiveness of the clusters.', fontsize=10)
plt.grid(True)
plt.show()
