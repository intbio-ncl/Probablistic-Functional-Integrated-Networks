import matplotlib.pyplot as plt
import pandas as pd
from scipy.stats import pearsonr
import seaborn as sns
# prepare data
data= pd.read_csv('ALLDGAScore.txt', sep="\t")

##data[["LTPScore", "HELScore"]].corr()
##corr, _ = pearsonr(data["LTPScore"], data["HELScore"])
##print(data.corr())
# summarize
"""print('data1: mean=%.3f stdv=%.3f' % (mean(data1), std(data1)))
print('data2: mean=%.3f stdv=%.3f' % (mean(data2), std(data2)))"""
# plot
##plt.scatter(data["LTPLLS"], data["HELLLS"], data["MGLLS"])

 
ax1 = data.plot(kind='scatter', x='OMIM_GS', y='MG_GS', color='b',label="OMIM&&MG")
ax2 = data.plot(kind='scatter', x='OMIM_GS', y='HEL_GS', color='m',label="OMIM&&HEL", ax=ax1)
ax3 = data.plot(kind='scatter', x='OMIM_GS', y='LTP_GS', color='#2C59AC',label="OMIM&&LTP", ax=ax1)
ax4 = data.plot(kind='scatter', x='OMIM_GS', y='MC_GS', color='c',label="OMIM&&MC", ax=ax1)
ax5 = data.plot(kind='scatter', x='OMIM_GS', y='UniProt_GS', color='r',label="OMIM&&UniProt", ax=ax1)

ax6 = data.plot(kind='scatter', x='UniProt_GS', y='MC_GS', color='y',label="UniProt&&MC", ax=ax1)
ax7 = data.plot(kind='scatter', x='UniProt_GS', y='MG_GS', color='g',label="UniProt&&MG", ax=ax1)
ax8 = data.plot(kind='scatter', x='UniProt_GS', y='LTP_GS', color='#CC3333',label="UniProt&&LTP", ax=ax1)
ax9 = data.plot(kind='scatter', x='UniProt_GS', y='HEL_GS', color='#76EE00',label="UniProt&&HEL", ax=ax1)
ax10 = data.plot(kind='scatter', x='HEL_GS', y='LTP_GS', color='#CD3344',label="HEL&&LTP",ax=ax1)
ax11 = data.plot(kind='scatter', x='HEL_GS', y='MG_GS', color='#BB3333',label="HEL&&MG",ax=ax1)
ax12 = data.plot(kind='scatter', x='HEL_GS', y='MC_GS', color='#4D59AB',label="HEL&&MC",ax=ax1)

ax13 = data.plot(kind='scatter', x='MC_GS', y='MG_GS', color='#3D59AC',label="MC&&MG",ax=ax1)
ax14 = data.plot(kind='scatter', x='MC_GS', y='LTP_GS', color='#BC3333',label="MC&&LTP",ax=ax1)
ax15 = data.plot(kind='scatter', x='MG_GS', y='LTP_GS', color='#BC3224', label="MG&&LTP",ax=ax1)


plt.xlabel('OMIM,UniProt,HEL,MC,MG')
plt.ylabel('MG,HEL,LTP,MC,UniProt,')


#sns.heatmap(data[["OMIM_GS", "UniProt_GS", "MC_GS","HEL_GS", "MG_GS", "LTP_GS"]].corr(), annot = True, fmt='.2g',cmap= 'coolwarm',vmin=0, vmax=1)
plt.legend(loc = "lower right",bbox_to_anchor = (1.35,0.02))
plt.show()
