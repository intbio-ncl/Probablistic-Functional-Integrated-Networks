import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from scipy.stats import pearsonr
import seaborn as sns
from scipy.stats import spearmanr
import matplotlib.ticker as mtick
from matplotlib.ticker import PercentFormatter
data1 = pd.read_csv('EL.txt', sep="\t")

plt.hist(data1["EL"],alpha=0.9,log=True)
plt.xlabel("Datasets high Evidence Level Percentage")
plt.ylabel("Number of datasets")

plt.title("Datasets high Evidence Level Percentage Distribution")
#plt.gca().yaxis.set_major_formatter(PercentFormatter(1))
plt.show()

