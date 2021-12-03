import pandas as pd
import csv
import sklearn.metrics as metrics
import numpy as np 
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.metrics import *
fpr1_1=[]
tpr1_1=[]
precision=[]
fpr2=[]
tpr2=[]
fpr1_2=[]
tpr1_2=[]
fpr1_3=[]
tpr1_3=[]
tpr1_4=[]
fpr1_4=[]
fpr1_5=[]
tpr1_5=[]
fpr6=[]
tpr6=[]
fpr7=[]
tpr7=[]
tpr8=[]
fpr8=[]
tpr9=[]
fpr9=[]
tpr10=[]
fpr10=[]
F1=[]
with open ("fold0", "r") as f:
    reader = csv.reader(f, delimiter = "\t")
    for i in reader:
      tpr1_1.append((float(i[4])))
      fpr1_1.append((1.0-float(i[5])))
      precision.append((float(i[6])))
      #F1.append((float(i[3])))"""
      
"""with open ("AUCCommonWeighted.txt", "r") as f:
    reader = csv.reader(f, delimiter = "\t")
  
    for i in reader:
     #tpr1_1.append((float(i[0])))
     #fpr1_1.append(1-((float(i[1]))))
     tpr1_2.append(float(i[2]))
     fpr1_2.append((1-(float(i[3]))))
     tpr1_3.append(float(i[4]))
     fpr1_3.append((1-float(i[5])))
     tpr1_4.append(float(i[6]))
     fpr1_4.append((1-(float(i[7]))))
     tpr1_5.append(float(i[8]))
     fpr1_5.append((1-(float(i[9]))))
    tpr6.append(float(i[10]))
    fpr6.append((1.0-float(i[11])))
    tpr7.append(float(i[12]))
    fpr7.append((1.0-float(i[13])))
    tpr8.append((float(i[14])))
    fpr8.append((1.0-float(i[15])))
    tpr9.append((float(i[16])))
    fpr9.append((1.0-float(i[17])))
    fpr10.append((1.0-float(i[18])))
    fpr10.append((1.0-float(i[19]))
with open ("AUCCommonweighted2.txt", "r") as f:
    reader = csv.reader(f, delimiter = "\t")
    for i in reader:
     tpr6.append(float(i[0]))
     fpr6.append((1.0-float(i[1])))
     tpr7.append(float(i[2]))
     fpr7.append((1.0-float(i[3])))
     tpr8.append((float(i[4])))
     fpr8.append((1.0-float(i[5])))
     tpr9.append((float(i[6])))
     fpr9.append((1.0-float(i[7])))
     tpr10.append((float(i[8])))
     fpr10.append((1.0-float(i[9])))
     #tpr10.append((float(i[8])))
     #fpr10.append((1.0-float(i[9])))"""
    
auc1_1 = metrics.auc(fpr1_1,tpr1_1)
auc = auc(tpr1_1,precision)
"""auc1_2 = metrics.auc(fpr1_2, tpr1_2)
auc1_3 = metrics.auc(fpr1_3, tpr1_3)
auc4 = metrics.auc(fpr1_4, tpr1_4)
auc5 = metrics.auc(fpr1_5, tpr1_5)
auc1_6 = metrics.auc(fpr6, tpr6)
auc1_7 = metrics.auc(fpr7, tpr7)
auc1_8 = metrics.auc(fpr8, tpr8)
auc1_9 = metrics.auc(fpr9, tpr9)
auc1_10 = metrics.auc(fpr10, tpr10)
#auc = auc(fpr1_1,precision)"""
plt.plot(fpr1_1,tpr1_1,label= 'Treatment Similarity Disease Network using SIDER = %0.2f' % auc1_1,color='blue', linewidth=3)
plt.plot(tpr1_1,precision,label='fold5 = %0.2f' % auc,color='black', linewidth=2)
"""plt.plot(fpr1_2,tpr1_2,label='Probabilistic Integrated Associated Networks = %0.2f' % auc1_2,color='purple', linewidth=2)
plt.plot(fpr1_3,tpr1_3,label= 'fold2 = %0.2f' % auc1_3,color='red', linewidth=2)
plt.plot(fpr1_4,tpr1_4,label= 'fold3 = %0.2f' % auc4,color='black', linewidth=2)
plt.plot(fpr1_5,tpr1_5,label='fold4 = %0.2f' % auc5,color='green', linewidth=2)

plt.plot(fpr6,tpr6,label='fold5 = %0.2f' % auc1_6,color='black', linewidth=2)
plt.plot(fpr7,tpr7,label='Simple Network Integration = %0.2f' % auc1_7,color='green', linewidth=2)
plt.plot(fpr8,tpr8,label='fold7 = %0.2f' % auc1_8,color='green', linewidth=2)
plt.plot(fpr9,tpr9,label='fold8 = %0.2f' % auc1_9,color='cyan', linewidth=2)
plt.plot(fpr10,tpr10,label='fold9 = %0.2f' % auc1_10,color='black', linewidth=2)"""
plt.plot([0,1],[0,1],'r--',label='random')

plt.xlim([0.0,1.0])
plt.ylim([0.0,1.05])
plt.ylabel('True Positive Rate : Sensitivity' )
plt.xlabel('False Positive Rate: 1-Specificity')
plt.legend(loc='lower left', bbox_to_anchor=(0.20, 0.1))
plt.title('Receiver Operating Characteristic')
plt.show()

