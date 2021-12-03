library(GOSE)
 txt <- gsub("[c(),]", "", readLines("/Users/aoeshagaedmalsobhe/NetBeansProjects/PFINNetWork/SharedGenes.txt"))
Y=lapply(txt, function(x) scan(text = x, what = "character", quiet = TRUE))

for(i in 1: length(Y)){
	my_vector <- vector(mode="numeric")
     my_vector2 <- vector(mode="numeric")
	su = 0
	summ=0
     Average=0
	for(j in 1: lengths(Y[i])){
	   for(k in j+1 : lengths(Y[i])){
	   	   if(j!=k){
		      s <- goSim(Y[[i]][j], Y[[i]][k], measure="Jiang") 
		  
              if(!is.na(s)) {
              	#print(s)
         	  my_vector <- append(my_vector, s)

         	  
         	}
             
         		  }
		  		  }
		  		  
		  		  
		  		  }
		  		  su=cumsum(my_vector)
		  		  print(max(su))
		  		  print(length(my_vector))
		  		  Average=max(su)/length(my_vector)
		  		  my_vector2 <- append(my_vector2, Average)
		  		  lapply(my_vector2, write, "test6.txt", append=TRUE)



		  		  }
		  		  
		  		  
		  		  
		  		  

		  
		  