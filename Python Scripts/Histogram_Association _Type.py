import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from collections import Counter
data = pd.read_csv('AssociationTypes.tsv', sep="\t")
print(data)
Types = data['Type'].tolist()
size = data['Type'].unique().tolist()
print(len(size)+0)
pd.Series(Types).value_counts().plot('bar')
plt.xlabel("Edge Association Type")
plt.ylabel("Frequency")
plt.title('Gene-Disease PFINS Edge Association Type Distribution')
plt.show()
