library(DOSE)
txt <- gsub("[c(),]", "", readLines("/Users/aoeshagaedmalsobhe/UMLStoDOIDPISDN"))
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
		  
              if(!is.na(s[1])) {
              	#print(s)
         	  my_vector <- append(my_vector, s[1])
              
         	  
         	}
         	else if(is.na(s[1])) {
              	#print(s)
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
                  
		  		  }
		  		  write(my_vector2, "myFile.txt")
                 su2=cumsum(my_vector2)
		  		  #print(max(su))
		  		  #print(length(my_vector))
		  		  Average2=max(su2)/length(Y)
		  		  print(Average2)
		  		  