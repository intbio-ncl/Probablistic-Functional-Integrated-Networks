library(igraph)
# read graph from csv file
G<-read.graph("DiseaseNetworkOneComponent.txt", format="ncol")
fgreedy<-fastgreedy.community(G,merges=TRUE, modularity=TRUE)
memberships <-community.to.membership(G, fgreedymerges, steps=which.max(fgreedy$modularity)-1)
print(paste('Number of detected communities=',length(memberships$csize)))
    # Community sizes
    print(memberships$csize)
# modularity:
max(fgreedy$modularity)
