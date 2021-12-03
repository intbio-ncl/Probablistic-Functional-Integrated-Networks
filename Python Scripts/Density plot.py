import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from scipy.stats import pearsonr
import seaborn as sns
from scipy.stats import spearmanr
data1 = pd.read_csv('GDA.txt', sep="\t")
list1=data1["A"].tolist()
list2=data1["B"].tolist()
list3=[]
list3.append(list1)
list3.append(list2)


sns.distplot(data1["B"], hist = False, kde = True, label = "GDA without Redundancy")
##    3sns.distplot(data1['B'], hist = False)
 
# Plot formatting

plt.title('Density Plot for GDA scores without redundancy')
plt.xlabel('GDA Score for Gene-Disease association')
plt.ylabel('Density')
plt.show()

      
