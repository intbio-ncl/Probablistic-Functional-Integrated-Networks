import pandas as pd
import matplotlib.pyplot as plt
data1 = pd.read_csv('ClusterAverageALL.txt', sep="\t")
inflation=[1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0, 6.5,7.0, 7.5]
network=["HEL" ]
marker=["o", "*", "P", "d", "p", "v", "<", ">", "h", "x",".", ",", "4", "3"] 
counter=0


colours=["red", "blue", "pink", "green", "black", "brown", "orange", "purple", "darkcyan", "yellow","darkgreen","magenta","darkred"]
for i in network:
   plt.plot(data1['Inflation'], data1[i],color=colours[counter], marker=marker[counter],label = i)
   counter+=1
##   plt.plot(data1['cluster'], data1['Average2'], color='blue', marker='o',label = "line 2")
plt.title('The cohesiveness of the network clusters with different inflation value', fontsize=10)
plt.xlabel('Inflation value', fontsize=10)
plt.ylabel('The average of cohesiveness of the clusters.', fontsize=10)
plt.grid(True)
plt.legend(loc = "lower right",bbox_to_anchor = (1,0.70))
plt.show()
