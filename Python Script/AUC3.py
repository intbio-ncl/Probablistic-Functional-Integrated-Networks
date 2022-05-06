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
#fpr7=[]
#tpr7=[]

with open ("D-Values-3.csv", "r") as f:
    reader = csv.reader(f, delimiter = ",")
    next(f)
    for i in reader:
     tpr1.append(float(i[0]))
     fpr1.append((1.0-float(i[1])))
     tpr2.append(float(i[2]))
     fpr2.append((1-float(i[3])))
     tpr3.append(float(i[4]))
     fpr3.append((1.0-float(i[5])))
     tpr4.append(float(i[6]))
     fpr4.append((1.0-float(i[7])))
     tpr5.append(float(i[8]))
     fpr5.append((1.0-float(i[9])))
     tpr6.append(float(i[10]))
     fpr6.append((1.0-float(i[11])))
     #tpr7.append(float(i[12]))
    # fpr7.append((1.0-float(i[13])))
auc1 = metrics.auc(fpr1, tpr1)
auc2 = metrics.auc(fpr2, tpr2)
auc3 = metrics.auc(fpr3, tpr3)
auc4 = metrics.auc(fpr4, tpr4)
auc5 = metrics.auc(fpr5, tpr5)
auc6 = metrics.auc(fpr6, tpr6)
#auc7 = metrics.auc(fpr7, tpr7)

plt.plot(fpr1,tpr1,label= 'D1:AUC = %0.2f' % auc1,color='blue', linewidth=2)
plt.plot(fpr2,tpr2,label='D2:AUC = %0.2f' % auc2,color='purple', linewidth=2)
plt.plot(fpr3,tpr3,label= 'D3:AUC = %0.2f' % auc3,color='red', linewidth=2)
plt.plot(fpr4,tpr4,label='D4:AUC = %0.2f' % auc4,color='black', linewidth=2)
plt.plot(fpr5,tpr5,label='D5:AUC = %0.2f' % auc5,color='green', linewidth=2)
plt.plot(fpr6,tpr6,label='D6:AUC = %0.2f' % auc6,color='gray', linewidth=2)
#plt.plot(fpr7,tpr7,label='D7:AUC = %0.2f' % auc7,color='cyan', linewidth=2)
plt.plot([0,1],[0,1],'r--',label='random')

plt.xlim([0.0,1.0])
plt.ylim([0.0,1.05])
plt.ylabel('True Positive Rate : Sensitivity' )
plt.xlabel('False Positive Rate: 1-Specificity')
plt.legend(loc="lower right")
plt.title('Receiver Operating Characteristic')
plt.show()


