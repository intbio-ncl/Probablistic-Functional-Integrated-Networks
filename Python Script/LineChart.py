import pandas as pd
import matplotlib.pyplot as plt
data1 = pd.read_csv('UniProtScoredThreshold.txt', sep="\t")
plt.plot(data1['cluster'], data1['Average'], color='red', marker='o',label = "line 1")
plt.plot(data1['cluster'], data1['Average2'], color='blue', marker='o',label = "line 2")
plt.title('The cohesiveness of the network clusters with different edge weight threshold', fontsize=10)
plt.xlabel('edge weight threshold', fontsize=10)
plt.ylabel('The average of cohesiveness of the clusters.', fontsize=10)
plt.grid(True)
plt.legend()
plt.show()
