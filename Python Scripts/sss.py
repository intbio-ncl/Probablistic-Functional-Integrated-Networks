import matplotlib.pyplot as plt
import csv

x=[]
y=[]

with open('All_LLS_Version17_Versions181.csv', 'r') as csvfile:
    plots= csv.reader(csvfile, delimiter='\t')
    for row in plots:
        x.append(row[0])
        y.append(row[1])

print(x)
plt.plot(x,y, marker='o')

plt.title('Data from the CSV File: People and Expenses')

plt.xlabel('Number of People')
plt.ylabel('Expenses')

plt.show()
