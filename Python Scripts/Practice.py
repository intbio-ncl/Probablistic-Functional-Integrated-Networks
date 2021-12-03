import networkx as nx
import networkx as nx
import random
import numpy as np
import pandas as pd
import scipy as sc
import matplotlib.pyplot as plt
from sklearn import metrics
from scipy.optimize import curve_fit
from tqdm import tqdm
from networkx.algorithms import bipartite
EdgeList=[]
Source=[]
target=[]
with open('integratedD-DPFINS.txt','r') as f:
    row = f.readlines()
    for line in row:
        triplets = line.split("\t")
        Source.append(triplets[0])
        target.append(triplets[1])
        Tupe=(triplets[0], triplets[1], triplets[2])
        EdgeList.append(Tupe)
f.close()
print(len(EdgeList))
G = nx.Graph()
G.add_weighted_edges_from(EdgeList)
B = nx.Graph()
# Add nodes with the node attribute "bipartite"
B.add_nodes_from(Source, bipartite=0)
B.add_nodes_from(target, bipartite=1)
# Add edges only between nodes of opposite node sets
B.add_weighted_edges_from(EdgeList)
sorted(B.edges(data=True))
#nx.draw(DG)
#plt.show()
print(len(list(nx.connected_components(G))))
print(len(max(list(nx.connected_components(G)))))
for component in list(nx.connected_components(G)):
    if len(component)!=6876:
        for node in component:
            G.remove_node(node)
nx.write_weighted_edgelist(G, 'test.weighted.edgelist.tsv', delimiter='\t', encoding='utf-8')
print(len(G))
print(len(list(nx.connected_components(G))))
print(G.number_of_edges())
print(G.number_of_nodes())
print(list(nx.connected_components(G)))
data = pd.DataFrame(G.edges())
data.to_csv(r'oo.csv',sep = ",", index = False)
data=pd.read_csv('test.weighted.edgelist.tsv', sep='\t')
print(data)
"""import community

partition = community.best_partition(G)
print(bipartite.is_bipartite(G))"""
D=nx.double_edge_swap(G,nswap=6875)
nx.write_weighted_edgelist(D, 'test.weighted.edgelistSwap.tsv', delimiter='\t', encoding='utf-8')
data=pd.read_csv('test.weighted.edgelistSwap.tsv', sep='\t')
data.to_csv(r'Random.txt', sep='\t', index = False)
nx.draw(D)
plt.show()
