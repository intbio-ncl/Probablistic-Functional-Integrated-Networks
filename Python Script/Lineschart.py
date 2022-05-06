import pandas as pd
import networkx as nx
import csv
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import pylab
names = []
values = []

data= pd.read_csv("LLS.txt", delimiter = "\t", index_col = 0)

print(data.T)
data = data.T
x = data.index
z = data["Sang L (2011).21565611"].values.astype(float)
W = data["Glatter T (2009).19156129"].values.astype(float)
#Y = data["Ewing RM (2007).17353931"].values.astype(float)
#s = data["Rual JF et al..16189514"].values.astype(float)
t = data["Glatter T (2009).19156129"].values.astype(float)
u = data["Sowa ME (2009).19615732"].values.astype(float)
i = data["Alexandru G (2008).18775313"].values.astype(float)
p = data["Cao Q (2014).24457600"].values.astype(float)
r = data["Bouwmeester T (2004).14743216"].values.astype(float)
a = data["Petschnigg J (2014).24658140"].values.astype(float)
b = data["Marechal A (2014).24332808"].values.astype(float)
c = data["Ravasi T (2010).20211142"].values.astype(float)
d = data["Lim J (2006).16713569"].values.astype(float)
e = data["Enzo E (2015).25796446"].values.astype(float)
f = data["McCracken S (2005).16159877"].values.astype(float)
g = data["Taipale M (2014).25036637"].values.astype(float)
h = data["Lehner B et al..15231747"].values.astype(float)
k = data["Singh G (2012).23084401"].values.astype(float)

plt.xticks(rotation=90)
plt.xticks((range(len(x))),x, rotation=90)
#plt.plot(x,t, label = "Glatter T (2009).19156129")
#plt.plot(x, z, label = "Sang L (2011).21565611")
#plt.plot(x, u, label = "Sowa ME (2009).19615732")
#plt.plot(x, i, label = "Alexandru G (2008).18775313")
#plt.plot(x, Y, label = "Ewing RM (2007).17353931")
#plt.plot(x, r, label = "Bouwmeester T (2004).14743216")
#plt.plot(x, p, label = "Cao Q (2014).24457600")
#plt.plot(x, W, label = "Glatter T (2009).19156129")
#plt.plot(x,s, label = "Rual JF et al..16189514")
#plt.plot(x, a, label = "Petschnigg J (2014).24658140")
#plt.plot(x, b, label = "Marechal A (2014).24332808")
#plt.plot(x, e, label = "Enzo E (2015).25796446")
#plt.plot(x, c, label = "Ravasi T (2010).20211142")
#plt.gca().legend(loc='centre left', bbox_to_anchor=(0.50, 0.50))
#plt.plot(x, d, label = "Lim J (2006).16713569")

plt.plot(x, f, label = "McCracken S (2005).16159877")
plt.plot(x, g, label = "Taipale M (2014).25036637")
plt.plot(x, h, label = "Lehner B et al..15231747")
plt.plot(x, k, label = "Singh G (2012).23084401")
plt.plot(x, u, label = "Sowa ME (2009).19615732")
plt.plot(x, i, label = "Alexandru G (2008).18775313")
#plt.plot(x, Y, label = "Ewing RM (2007).17353931")
plt.plot(x, r, label = "Bouwmeester T (2004).14743216")
plt.plot(x, p, label = "Cao Q (2014).24457600")
plt.plot(x, W, label = "Glatter T (2009).19156129")
#plt.plot(x,s, label = "Rual JF et al..16189514")
plt.plot(x, a, label = "Petschnigg J (2014).24658140")
plt.plot(x, b, label = "Marechal A (2014).24332808")
plt.plot(x, e, label = "Enzo E (2015).25796446")
plt.plot(x, c, label = "Ravasi T (2010).20211142")
plt.plot(x, z, label = "Sang L (2011).21565611")
plt.legend()
plt.xlabel('Versions')
plt.ylabel('LLS Values')
plt.title('LLS Changes')

plt.legend(loc='center left', bbox_to_anchor=(1, 0.5))
plt.show()

