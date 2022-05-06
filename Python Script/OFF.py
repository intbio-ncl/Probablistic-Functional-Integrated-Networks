import networkx as nx
import random
import matplotlib.pyplot as plt
import operator
Graph = nx.gnp_random_graph(10, 0.5, directed=True)
nx.draw(Graph, with_labels=True, node_color='green') #draw the network graph 
plt.figure(figsize=(15,10))
plt.show() #to show the graph by plotting it
