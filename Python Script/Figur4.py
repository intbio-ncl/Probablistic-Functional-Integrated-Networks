import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from scipy.stats import pearsonr
import seaborn as sns
from scipy.stats import spearmanr
import matplotlib.ticker as mtick
from matplotlib.ticker import PercentFormatter
data1 = pd.read_csv('EL.txt', sep="\t")

plt.hist(data1["PP"],alpha=0.9,log=True)
##ax=plt.hist(data1["PP"],weights=np.ones(len(data1["PP"])) / len(data1["PP"])*100)
plt.xlabel("High Evidence Level Percentage")
plt.ylabel("Number of datasets")

plt.title("Percentage of High Evidence Level among datasets")
#plt.gca().yaxis.set_major_formatter(PercentFormatter(1))
plt.show()

