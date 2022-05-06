import pandas as pd
import csv
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
Thresholds = []
values = [0.90, 0.88, 0.99,0.90, 0.88, 0.99,0.90, 0.88, 0.99]
numbers = [0.16,0.13,0.55,0.16,0.13,0.55,0.16,0.13,0.55]



print(values)
plt.plot(numbers,values)
plt.plot([0,1],[0,1],'r--')
plt.xlim([0.0,1.0])
plt.ylim([0.0,1.05])
plt.show()
