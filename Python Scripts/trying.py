import pandas as pd
import csv
import sklearn.metrics as metrics
import numpy 
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.metrics import *
fpr1=[]
tpr1=[]

with open ("trying.csv", "r") as f:
    reader = csv.reader(f, delimiter = ",")
    next(f)
    for i in reader:
     tpr1.append(float(i[0]))
     fpr1.append((1.0-float(i[1])))
     
auc1 = metrics.auc(fpr1, tpr1)

plt.plot(fpr1,tpr1,label= 'D1:AUC = %0.2f' % auc1,color='blue', linewidth=2)

plt.plot([0,1],[0,1],'r--',label='random')

plt.xlim([0.0,1.0])
plt.ylim([0.0,1.05])
plt.ylabel('True Positive Rate : Sensitivity' )
plt.xlabel('False Positive Rate: 1-Specificity')
plt.legend(loc="lower right")
plt.title('Receiver Operating Characteristic')
plt.show()

