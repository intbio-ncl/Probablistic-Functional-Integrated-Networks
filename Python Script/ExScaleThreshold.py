import pandas as pd
import matplotlib.pyplot as plt
data1 = pd.read_csv('ThresholdAnalysis 3.txt', sep="\t")
print(data1)
print(data1["Threshold"])
print(data1["Size"])


labels = data1["Threshold"].tolist()
LTP_N = data1["Size"].tolist()
"""LTP_G = data1["G"].tolist()
LTP_D=data1["D"].tolist()
LTP_Ass=data1["Ass"].tolist()
HTP_N = data1["HTP"].tolist()
HTP_G = data1["HTPG"].tolist()
HTP_D=data1["HTPD"].tolist()
HTP_Ass=data1["HTPAss"].tolist()"""

fig, ax = plt.subplots()
ax.plot(labels, LTP_N)
"""ax.plot(labels, LTP_G,label='Genes')
ax.plot(labels, LTP_D,label='Diseases')
ax.plot(labels, LTP_Ass,label='Associations')
ax.plot(labels, HTP_N,label='HTPN')
ax.plot(labels, HTP_G,label='HTPG')
ax.plot(labels, HTP_D,label='HTPD')
ax.plot(labels, HTP_Ass,label='HTPAss')"""
# Add some text for labels, title and custom x-axis tick labels, etc.
ax.set_ylabel('Network Size')
ax.set_xlabel("Threshold to distingushe between LTP && HTP")

ax.legend()



plt.show()
