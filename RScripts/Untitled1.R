> txt <- gsub("[c(),]", "", readLines("/Users/aoeshagaedmalsobhe/outfile1.8.txt"))
Y=lapply(txt, function(x) scan(text = x, what = "character", quiet = TRUE))
dd <- read.table("/Users/aoeshagaedmalsobhe/integrated.Curated-Overlap.txt")
library(igraph)
gg <- graph.data.frame(dd, directed=FALSE)
dc_1 <- induced.subgraph(gg,Y[[38]])
plot(dc_1, layout=layout_randomly)
plot(dc_1, layout =layout_with_fr)
l <- layout.fruchterman.reingold(dc_1, niter=5000, area=vcount(dc_1)^4*10)
plot(dc_1, layout =l)
plot(dc_1, layout=l, 
+      edge.arrow.size=0.5, 
+      vertex.label.cex=0.75, 
+      vertex.label.family="Helvetica",
+      vertex.label.font=2,
+      vertex.shape="circle", 
+      vertex.size=1, 
+      vertex.label.color="black", 
+      edge.width=0.5)