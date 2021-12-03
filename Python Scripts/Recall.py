import os
import pandas as pd
import csv
import sklearn.metrics as metrics
import numpy as np 
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.metrics import *
colours=["blue", "red", "black", "green", "cyan", "yellow", "pink", "orange", "blue", "green", "black"]
counter=0
Average=0
entries = os.listdir('/Users/aoeshaalsobhe/Documents/Up')
entries = sorted(entries)
print(entries)
for i in entries:
    #print(counter)
    fpr1_i=[]
    tpr1_i=[]
    Precision=[]
    with open (i, "r",encoding='utf-8',errors='ignore') as f:
        print(i)
        reader = csv.reader(f, delimiter = "\t")
        for j in reader:
            tpr1_i.append((float(j[0])))
            fpr1_i.append((1.0-float(j[1])))
            Precision.append((float(j[2])))
    
    auc1_i = metrics.auc(tpr1_i,Precision)
    
    plt.plot(tpr1_i,label= "fold"+str(counter)+" =%0.2f"% auc1_i,color=colours[counter], linewidth=3)
    
    counter+=1
    Average+=auc1_i
plt.xlim([0.0,1.0])
plt.ylim([0.0,1.05])
plt.ylabel('True Positive Rate : Sensitivity' )
plt.xlabel('False Positive Rate: 1-Specificity')
plt.legend(loc='lower right')
plt.title('Receiver Operating Characteristic')
    
    
plt.plot([0,1],[0,1],'r--',label='random')
plt.plot(fpr1_i,tpr1_i,label= "Average =%0.2f"% 0.66,color=colours[counter], linewidth=3)
plt.show()
print("Average")
print(Average/10)

