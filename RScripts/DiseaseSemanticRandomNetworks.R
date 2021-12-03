library(DOSE)
TotalAverage=0.0
TotalNetwork=0.0
TotalNetworkAverage=0.0
my_vector3 <- vector(mode="numeric")
for(t in 0:99){
	
txt <- gsub("[c(),]", "", readLines(gsub(" ", "", paste("/Users/aoeshagaedmalsobhe/UMLS to DOID",t))))

Y=lapply(txt, function(x) scan(text = x, what = "character", quiet = TRUE))
my_vector2 <- vector(mode="numeric")
for(i in 1: length(Y)){
	my_vector <- vector(mode="numeric")
     
	su = 0
	summ=0
     Average=0
	for(j in 1: lengths(Y[i])){
	   for(k in j+1 : lengths(Y[i])){
	   	   if(j!=k){
		      s <- doSim(Y[[i]][j], Y[[i]][k], measure="Wang") 
		  
              if(!is.na(s)) {
              	#print(s)
         	  my_vector <- append(my_vector, s)

         	  
         	}
             
         		  }
		  		  }
		  		  
		  		  
		  		  }
		  		  su=cumsum(my_vector)
		  		  #print(max(su))
		  		  #print(length(my_vector))
		  		  Average=max(su)/length(my_vector)
		  		  my_vector2 <- append(my_vector2, Average)
		  		  print(Average)
                 lapply(my_vector2, write, "geneticsimilarity.txt", append=TRUE)
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
		  