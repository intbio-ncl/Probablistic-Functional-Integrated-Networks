txt <- gsub("[c(),]", "", readLines("/Users/aoeshagaedmalsobhe/clusteroutfilec"))
Y=lapply(txt, function(x) scan(text = x, what = "character", quiet = TRUE))
Z = lengths(Y)# vector of clusters sizes
min(Z)
mzx(z0)
frequency <- hist(Z, breaks=seq(min(Z),max(Z),l=4000), plot=FALSE)
plot(frequency$count, log="y", type='h', lwd=10, lend=2)
dd <- read.table("/Users/aoeshagaedmalsobhe/integrated.Curated-Overlap.txt")
library(igraph)
gg <- graph.data.frame(dd, directed=FALSE)
plot(gg)
dd

dc_1 <- induced.subgraph(gg,Y[[38]])
 plot(dc_1, layout=l)
 plot(dc_1, layout=layout_randomly)




for(i in 1: length(Y)){
+ dc_1 <- induced.subgraph(gg,Y[[i]])
+ plot(dc_1, layout =layout_with_fr)
+ 
+ }

        