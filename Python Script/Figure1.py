import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from scipy.stats import pearsonr
import seaborn as sns
from scipy.stats import spearmanr
import matplotlib.ticker as mtick
from matplotlib.ticker import PercentFormatter
data1 = pd.read_csv('PubMedIDAssociations.txt', sep="\t")
plt.hist(data1["Size"],alpha=0.9,log=True,bins=range(0,200))


#ax=plt.hist(data1["Size"],weights=np.ones(len(data1["Size"])) / len(data1["Size"]),range=(1,100),log=True)
plt.xlabel("Dataset size (number of associations)", size=14)
plt.ylabel("Number of datsets", size=14)

plt.title("Datasets size distribution")
#plt.gca().yaxis.set_major_formatter(PercentFormatter(1))
plt.show()
