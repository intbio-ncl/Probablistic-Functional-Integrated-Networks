library(DOSE)
txt <- gsub("[c(),]", "", readLines("/Users/aoeshagaedmalsobhe/UMLStoDOIDPISDN"))
Y=lapply(txt, function(x) scan(text = x, what = "character", quiet = TRUE))
my_vector2 <- vector(mode="numeric")
for(i in 1: length(Y)){
     s <- doSim(Y[[i]], Y[[i]], measure="Wang") 
simplot(s,
        color.low="white", color.high="red",
        labs=TRUE, digits=2, labs.size=5,
        font.size=14, xlab="", ylab="")
		      		                 	}
             
         		  		  		  
		  		  
		  		 


		  		  
