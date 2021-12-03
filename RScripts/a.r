txt <- gsub("[c(),]", "", readLines("/Users/aoeshagaedmalsobhe/outfile1.8.txt"))
Y=lapply(txt, function(x) scan(text = x, what = "character", quiet = TRUE))
Z = lengths(Y)# vector of clusters sizes
min(Z)
max(Z)
frequency <- hist(Z, breaks=seq(min(Z),max(Z),l=4000), plot=FALSE)
plot(frequency$count, log="y", type='h', lwd=10, lend=2)
dd <- read.table("/Users/aoeshagaedmalsobhe/integrated.Curated-Overlap.txt")
library(igraph)
gg <- graph.data.frame(dd, directed=FALSE)
plot(gg)
dd

dc_1 <- induced.subgraph(gg,Y[[38]])
 plot(dc_1, layout=layout_randomly)
l <- layout.fruchterman.reingold(dc_1, niter=5000, area=vcount(dc_1^4*10)


for(i in 1: length(Y)){
+ dc_1 <- induced.subgraph(gg,Y[[i]])
+ plot(dc_1, layout =layout_with_fr)
 }

        