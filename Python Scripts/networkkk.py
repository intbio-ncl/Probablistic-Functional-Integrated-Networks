import networkx as nx
edges = [(1,2), (3, 4), (5, 6)]
edges2 = [(1, 2), (4,6), (3, 5)]
print(edges)
G1 = nx.Graph()
G1.add_edges_from(edges)
G2 = nx.Graph()
G2.add_edges_from(edges2)
print(nx.info(G1))
x = nx.intersection(G1,G2)
print(x.number_of_edges())
