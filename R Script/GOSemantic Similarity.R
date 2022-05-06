library(GOSemSim)
 txt <- gsub("[c(),]", "", readLines("/Users/aoeshagaedmalsobhe/NetBeansProjects/PFINNetWork/SharedGenes.txt"))
Y=lapply(txt, function(x) scan(text = x, what = "character", quiet = TRUE))
 my_vector2 <- vector(mode="numeric")
 AverageN=0.0
 SuN=0.0
for(i in 1: length(Y)){
	my_vector <- vector(mode="numeric")
    
	su = 0.0
	summ=0.0
     Average=0.0
	for(j in 1: lengths(Y[i])){
	   for(k in j+1 : lengths(Y[i])){
	   	   if(j!=k){
		      S=geneSim(Y[[i]][j], Y[[i]][k], semData=hsGO2, measure="Wang", combine="BMA")  
		  
              if(!is.na(S[1])) {
              	#print(S)
         	  my_vector <- append(my_vector, S[1])

         	  
         	}
         	else if(is.na(S[1])){
         	  my_vector <- append(my_vector, 0)
         		
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
		  		  lapply(Average, write, "test6.txt", append=TRUE)



		  		  }
		  		  
		  		  suN=cumsum(my_vector2)
		  		  #print(max(su))
		  		  #print(length(my_vector))
		  		  AverageN=max(suN)/length(my_vector2)
		  		  		  		  
		  		  

		  
		  