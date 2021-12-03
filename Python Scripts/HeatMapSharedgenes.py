import numpy as np 
import pandas as pd
import seaborn as sns
data = pd.read_csv('cluster1133JaccardDrugSimilarity 2.txt', sep = "\t")
Index= data["D1"]
Cols = data["D2"]
print(data)
#df2 = pd(abs(data[3]), index=Index, columns=Cols)

#sns.heatmap(df, annot=True)
print(data["D1"])
#df = pd(data["Score"],index=Index, columns=Cols)
#dd = df(abs(np.random.randn(5, 4)), index=Index, columns=Cols)
sns.heatmap(data, annot=True)
