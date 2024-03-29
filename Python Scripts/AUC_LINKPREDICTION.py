import pandas as pd
import csv
import sklearn.metrics as metrics
import numpy 
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.metrics import *
fpr1=[]
tpr1=[]
fpr2=[]
tpr2=[]
fpr3=[]
tpr3=[]
fpr4=[]
tpr4=[]
fpr5=[]
tpr5=[]
fpr6=[]
tpr6=[]
fpr7=[]
tpr7=[]
with open ("ou.txt", "r") as f:
    reader = csv.reader(f, delimiter = "\t")
    for i in reader:
      tpr1.append((float(i[3])))
      fpr1.append((1.0-float(i[4])))
auc1 = metrics.auc(fpr1, tpr1)
plt.plot(fpr1,tpr1,label= 'class0 = %0.2f' % auc1,color='blue',linewidth=2)
plt.plot([0,1],[0,1],'r--',label='random')

plt.xlim([0.0,1.0])
plt.ylim([0.0,1.05])
plt.ylabel('True Positive Rate : Sensitivity' )
plt.xlabel('False Positive Rate: 1-Specificity')
plt.legend(loc="lower right")
plt.title('Receiver Operating Characteristic')
plt.show()
