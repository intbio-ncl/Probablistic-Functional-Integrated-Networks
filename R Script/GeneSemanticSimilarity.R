library(GOSemSim)
hsGO2 <- godata('org.Hs.eg.db', keytype = "SYMBOL", ont="MF", computeIC=FALSE) 
TotalAverage=0.0
TotalNetwork=0.0
TotalNetworkAverage=0.0
for(t in 0:99){
my_vector3 <- vector(mode="numeric")
txt <- gsub("[c(),]", "", readLines("/Users/aoeshagaedmalsobhe/NetBeansProjects/PFINNetWork/SharedGenes.txt"))
Y=lapply(txt, function(x) scan(text = x, what = "character", quiet = TRUE))
my_vector2 <- vector(mode="numeric")
for(i in 1: length(Y)){
	
	my_vector2 <- vector(mode="numeric")
	my_vector <- vector(mode="numeric")
    print("ii")
    print(i)
	su = 0
	summ=0
     Average=0
	for(j in 1: lengths(Y[i])){
	   for(k in j+1 : lengths(Y[i])){
	   	   if(j!=k){
S=geneSim(Y[[i]][j], Y[[i]][k], semData=hsGO2, measure="Wang", combine="BMA") 
		  
              if(!is.na(S[1])) {
              	#print(s)
         	  my_vector <- append(my_vector, S[1])

         	  
         	}
             
         		  }
		  		  }
		  		  
		  		  
		  		  
		  		  su=cumsum(my_vector)
		  		  #print(max(su))
		  		  #print(length(my_vector))
		  		  Average=max(su)/length(my_vector)
		  		  my_vector2 <- append(my_vector2, Average)
		  		  print(Average)
		  		  lapply(my_vector2, write, "SemanticSimilarityBetweenGeness.txt", append=TRUE)
		  		  
		  		  
		  		  }
		  		  Aver=cumsum(my_vector2)
             #print(Aver)
             print(max(Aver))
             print(length(my_vector2))
             TotalAverage=max(Aver)/length(my_vector2)
             my_vector3 <- append(my_vector3, TotalAverage)

}
TotalNetwork=cumsum(my_vector3)
TotalNetworkAverage=max(TotalNetwork)/100
		print(TotalNetworkAverage) 
		  
		  		  

		  
		  
		  