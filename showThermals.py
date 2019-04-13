#!/usr/bin/env python3
import matplotlib.pyplot as plt
import numpy as np

a = np.array([[29, 21, 0, 0, 0, 0, 0, 0, 26, 24],
 [29, 13, 8, 0, 0, 0, 0, 15, 11, 24,],
 [22, 20, 6, 2, 0, 0, 8, 7, 19,16,],
 [22, 12, 14, 2, 0, 6, 2, 12, 14, 16],
 [19, 15, 9, 7, 4, 2, 9, 5, 17, 13],
 [19, 12, 12, 7, 4, 7, 4, 10, 12, 13],
 [16, 15, 10, 9, 7, 4, 6, 8, 14, 11],
 [16, 14, 11, 10, 6, 7, 3, 11, 11, 11],
 [16, 14, 11, 10, 8, 5, 11, 3, 11, 11],
 [16, 15, 10, 9, 9, 8, 8, 9, 5, 11]
 ])

total = []
fp = open("test.txt","r")
for line in fp:
	tmp = []
	for x in line.split(" ")[1:]:
		if x =='\n':
			continue
		tmp.append(int(x))
	total.append(list(tmp))
total = np.array(total)

plt.imshow(total, cmap='inferno', interpolation='nearest')
plt.ylabel("Time")
plt.show()
