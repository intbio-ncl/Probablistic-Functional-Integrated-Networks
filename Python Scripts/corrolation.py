import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt
sns.set(style="ticks", color_codes=True)    
df= pd.read_csv('LLSPIAN.txt')
g = sns.pairplot(df)
plt.show()
