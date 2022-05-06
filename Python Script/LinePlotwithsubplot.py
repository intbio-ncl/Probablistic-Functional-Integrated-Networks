import pandas as pd
import matplotlib.pyplot as plt
data1 = pd.read_csv('ClusteAverageAllNetwork.txt', sep="\t")
networks=["MG scored network", "HEL scored network", "MC scored network", "LTP scored network"]
marker=["o", "*", "P", "d", "p", "v", "<", ">", "h", "x",".", ",", "4", "3"] 
colours=["red", "blue", "green", "black", "brown", "orange", "purple", "yellow","green", "darkblue", "teal","magenta","darkred"]
counter=0
x=[0,1]
y=[0,1]
fig, axs = plt.subplots(2,3)

for x1 in x:
    for y1 in y:
        for i in networks:
    
            axs[0,0].plot(data1['Threshold'], data1["MG scored network"])
            axs[0,1].plot(data1['Threshold'], data1["MC scored network"])
            axs[1,0].plot(data1['Threshold'], data1["HEL scored network"])
            axs[1,1].plot(data1['Threshold'], data1["LTP scored network"])
            axs[0,2].plot(data1['Threshold'], data1["OMIM scored network"])
            axs[1,2].plot(data1['Threshold'], data1["UniProt scored network"])
            counter+=1


plt.show()
