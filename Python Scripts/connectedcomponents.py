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
with open('Predicted new association.txt','r') as f:
    row = f.readlines()
    for line in row:
        triplets = line.split("\t")
        Source.append(triplets[0])
        target.append(triplets[1])
        Tupe=(triplets[0], triplets[1], 1)
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
print(G.number_of_nodes())
print(len(list(nx.connected_components(G))))
print(len(max(list(nx.connected_components(G)))))
for component in sorted(nx.connected_components(G), key=len, reverse=True):
    print(len(component))
    if len(component)==310:
        G3 = G.subgraph(component)
        print(G3.number_of_nodes())
        print(G3.number_of_edges())
        """for node in component:
            G.remove_node(node)"""
nx.write_weighted_edgelist(G, 'test.weighted.edgelist.tsv', delimiter='\t', encoding='utf-8')
print(len(G))
print(len(list(nx.connected_components(G))))
print(G.number_of_edges())
print(list(nx.connected_components(G)))
data = pd.DataFrame(G.edges())
data.to_csv(r'oo.csv',sep = ",", index = False)
data=pd.read_csv('test.weighted.edgelist.tsv', sep='\t')
print(data)
print(bipartite.is_bipartite(G))
data.to_csv(r'loo.csv', index = False)
# Generate connected components and select the largest:
largest_component = max(nx.connected_components(G), key=len)
print(len(largest_component))
# Create a subgraph of G consisting only of this component:
G2 = G.subgraph(largest_component)
print(G2.number_of_nodes())
print(G2.number_of_edges())

