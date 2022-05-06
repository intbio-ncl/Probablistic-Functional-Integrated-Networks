import pandas as pd
import matplotlib.pyplot as plt
data1 = pd.read_csv('ClusteAverageAllNetwork.txt', sep="\t")
networks=["MC scored network", "LTP scored network","MG scored network","HEL scored network", "OMIM scored network", "UniProt scored network"]
marker=["o", "*", "P", "d", "p", "v", "<", ">", "h", "x",".", ",", "4", "3"] 
colours=["red", "blue", "green", "black", "brown", "orange", "purple", "yellow","green", "darkblue", "teal","magenta","darkred"]
counter=0
for i in networks:
    
        plt.plot(data1['Threshold'], data1[str(i)], color=colours[counter], marker=marker[counter],label = str(i))
        counter+=1
##plt.title('The cohesiveness of the network clusters with different edge weight threshold', fontsize=10)
plt.xlabel('Edge Weight Threshold', fontsize=10)
plt.ylabel('The average of Clusters Cohesiveness', fontsize=10)
plt.grid(True)
plt.legend(loc = "lower right",bbox_to_anchor = (1.0, -0.001))

plt.show()
