library("pheatmap")
data <- read.table("HeatmapR.txt",sep="\t",header=T)
pheatmap(df, cutree_rows = 4)
