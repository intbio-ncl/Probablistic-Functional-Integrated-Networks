import networkx as nx
import networkx as nx
import random
import numpy as np
import pandas as pd
import scipy as sc
import matplotlib.pyplot as plt
from sklearn import metrics
from scipy.optimize import curve_fit
#from tqdm import tqdm
from networkx.algorithms import bipartite
EdgeList=[]
Source=[]
target=[]
with open('OMIMintegrated.txt','r') as f:
    row = f.readlines()
    for line in row:
        triplets = line.split("\t")
        Source.append(triplets[0])
        target.append(triplets[1])
        Tupe=(triplets[0], triplets[1], triplets[2])
        EdgeList.append(Tupe)
f.close()

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
print(G.number_of_edges())
print(G.number_of_nodes())
for component in list(nx.connected_components(G)):
    if len(component)<=3:
       for node in component:
            G.remove_node(node)
    
nx.write_weighted_edgelist(G, 'test.weighted.edgelist.tsv', delimiter='\t', encoding='utf-8')

"
