import sys
import re
import io


with open('genemap2.txt') as f:
     line = f.readlines()
     line = io.StringIO(line)
     line.readline()

    # Strip trailing new line
     line = line.strip('\n')

    # Get the values
     valueList = line.split('\t')

    # Get the fields
     chromosome = valueList[0]
     genomicPositionStart = valueList[1]
     genomicPositionEnd = valueList[2]
     cytoLocation = valueList[3]
     computedCytoLocation = valueList[4]
     mimNumber = valueList[5]
     geneSymbols = valueList[6]
     geneName = valueList[7]
     approvedGeneSymbol = valueList[8]
     entrezGeneID = valueList[9]
     ensemblGeneID = valueList[10]
     comments = valueList[11]
     phenotypeString = valueList[12]
     mouse = valueList[13]

    # Skip empty phenotypes
     

    # Parse the phenotypes
     for phenotype in phenotypeString.split(';'):

        # Clean the phenotype
        phenotype = phenotype.strip()

        # Long phenotype
        matcher = re.match(r'^(.*),\s(\d{6})\s\((\d)\)(|, (.*))$', phenotype)
        if matcher:

            # Get the fields
           phenotype = matcher.group(1)
           phenotypeMimNumber = matcher.group(2)
           phenotypeMappingKey = matcher.group(3)
           inheritances = matcher.group(5)

            

        # Short phenotype
        else:

            # Short phenotype
            matcher = re.match(r'^(.*)\((\d)\)(|, (.*))$', phenotype)
            if matcher:

                # Get the fields
               phenotype = matcher.group(1)
               phenotypeMappingKey = matcher.group(2)
               inheritances = matcher.group(3)

                
