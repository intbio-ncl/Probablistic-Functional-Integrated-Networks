import pandas as pd
import matplotlib.pyplot as plt
data1 = pd.read_csv('MCScoredNetworkThresholds.txt', sep="\t")
print(data1["NetworkSize"])
print(data1["ClusterSize"])
fig, ax1 = plt.subplots()

color = 'tab:red'
ax1.set_xlabel('Edge weight threshold')
ax1.set_ylabel('Network size', color=color)
ax1.plot(data1["Threshold"], data1["NetworkSize"],marker='o', color=color)
ax1.tick_params(axis='y', labelcolor=color)

ax2 = ax1.twinx()  # instantiate a second axes that shares the same x-axis

color = 'tab:blue'
ax2.set_ylabel('Number of clusters', color=color)  # we already handled the x-label with ax1
ax2.plot(data1["Threshold"], data1["ClusterSize"],marker='*', color=color)
ax2.tick_params(axis='y', labelcolor=color)

fig.tight_layout()  # otherwise the right y-label is slightly clipped
plt.title('MC Scored network')
plt.show()
