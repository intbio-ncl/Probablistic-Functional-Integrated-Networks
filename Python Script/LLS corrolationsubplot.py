import pandas as pd
import matplotlib.pyplot as plt
from scipy.stats import pearsonr
import seaborn as sns
# prepare data
data= pd.read_csv('LLSCorrolation.txt', sep="\t")
##data[["LTPScore", "HELScore"]].corr()
##corr, _ = pearsonr(data["LTPScore"], data["HELScore"])
##print(data.corr())
# summarize
"""print('data1: mean=%.3f stdv=%.3f' % (mean(data1), std(data1)))
print('data2: mean=%.3f stdv=%.3f' % (mean(data2), std(data2)))"""
# plot
"""ax1 = data.plot(kind='scatter', x='LLS MC_GS', y='LLS MG_GS', color='c',label="LLS MC_GS & LLS MG_GS")
ax2 = data.plot(kind='scatter', x='LLS MC_GS', y='LLS HEL_GS', color='r',label="LLS MC_GS & LLS HEL_GS ", ax=ax1)
ax3 = data.plot(kind='scatter', x='LLS MC_GS', y='LLS LTP_GS', color='b',label="LLS MC_GS & LLS LTP_GS", ax=ax1)
ax4 = data.plot(kind='scatter', x='LLS HEL_GS', y='LLS MG_GS', color='y',label="LLS HEL_GS & LLS MG_GS", ax=ax1)
ax5=data.plot(kind='scatter', x='LLS HEL_GS', y='LLS LTP_GS', color='g',label="LLS HEL_GS & LLS LTP_GS", ax=ax1)
ax6=data.plot(kind='scatter', x='LLS LTP_GS', y='LLS MG_GS',color='m',label="LS LTP_GS & LLS MG_GS", ax=ax1)"""

fig, axs = plt.subplots(2,3)
fig, axes= plt.subplots(nrows=2, ncols=3)
   
ax3 = data.plot(kind='scatter', x='LLS MC_GS', y='LLS MG_GS', color='b', ax=axes[0][0])
ax5 = data.plot(kind='scatter', x='LLS MC_GS', y='LLS HEL_GS', color='g', ax=axes[0][1])
ax9 = data.plot(kind='scatter', x='LLS MC_GS', y='LLS LTP_GS', color='r', ax=axes[1][1])
ax10 = data.plot(kind='scatter', x='LLS HEL_GS', y='LLS LTP_GS', color='c',ax=axes[1][0])
ax11 = data.plot(kind='scatter', x='LLS HEL_GS', y='LLS MG_GS', color='y', ax=axes[0][2])
ax12 = data.plot(kind='scatter', x='LLS MG_GS', y='LLS LTP_GS', color='m', ax=axes[1][2])
plt.legend(loc = "lower right",bbox_to_anchor = (1.40,0.02))
"""plt.xlabel('LLS_MC_GS,LLS_HEL_GS, LLS_LTP_GS')
plt.ylabel('LLS_MG_GS, LLS_HEL_GS, LLS_LTP_GS')"""

plt.show()
