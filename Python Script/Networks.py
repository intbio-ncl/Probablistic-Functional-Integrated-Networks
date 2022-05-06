import pandas as pd
import matplotlib.pyplot as plt
data1 = pd.read_csv('ClusteAverageAllNetwork.txt', sep="\t")
inflation=["MG scored network"]
marker=["o", "*", "P", "d", "p", "v", "<", ">", "h", "x",".", ",", "4", "3"] 
colours=["red", "blue", "green", "black", "brown", "orange", "purple", "yellow","green", "darkblue", "teal","magenta","darkred"]
counter=0
for i in inflation:
    
        plt.plot(data1['Threshold'], data1[str(i)], color=colours[counter], marker=marker[counter],label = str(i))
        counter+=1
plt.title('The cohesiveness of the network clusters with different edge weight threshold', fontsize=10)
plt.xlabel('edge weight threshold', fontsize=10)
plt.ylabel('The average of cohesiveness of the clusters.', fontsize=10)
plt.grid(True)
plt.legend(loc = "lower right",bbox_to_anchor = (1.0, -0.001))

plt.show()
