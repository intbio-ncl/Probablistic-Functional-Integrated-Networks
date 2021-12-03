import networkx as nx
import matplotlib.pyplot as plt
G = nx.Graph()
G.add_nodes_from([1,2,3 ,4, 5, 6, 7])
nx.draw(G)
plt.show()


