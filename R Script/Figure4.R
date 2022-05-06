library(UpSetR)
data <- read.table("OverlapPubmed.txt",sep="\t",header=T)
list=as.list(data)
upset(fromList(list), order.by = "freq")