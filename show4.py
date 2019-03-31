#!/usr/bin/python3
s = 7
ts = 0
for x in range(0,4):
	ts = 0
	if(x+ts>s):
		break
	ts+=x
	for y in range(0,4):
		if(y+ts>s):
			break
		ts+=y
		for z in range(0,4):
			if(z+ts>s):
				break
			ts+=z
			for w in range(0,4):
				if(w+ts>s):
					break
				ts+=w
				print(ts," ",x,y,z,w)
			print(ts," ",x,y,z,w)
		print(ts," ",x,y,z,w)
	print(ts," ",x,y,z,w)
