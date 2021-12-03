print(torch.__version__)
import torch
x = torch.rand(5, 3)
print(x)


"""import torch
from torch import nn

class GCN(nn.Module):
    def __init__(self, *sizes):
        super().__init__()
        self.layers = nn.ModuleList([
            nn.Linear(x, y) for x, y in zip(sizes[:-1], sizes[1:])
        ])
    def forward(self, vertices, edges):
        # ----- Build the adjacency matrix -----
        # Start with self-connections
        adj = torch.eye(len(vertices))
        # edges contain connected vertices: [vertex_0, vertex_1] 
        adj[edges[:, 0], edges[:, 1]] = 1 
        adj[edges[:, 1], edges[:, 0]] = 1
        
        # ----- Forward data pass -----
        for layer in self.layers:
            vertices = torch.sigmoid(layer(adj @ vertices))
 
        return vertices
    
"""
