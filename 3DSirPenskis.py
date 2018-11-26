#!/usr/bin/env python3

###############################################################################
#I want to give some context to this, my teacher assigned a project at the begining
#of the class and I wrote this entire thing in lecture.
#he was cool with it.
###############################################################################
import random
import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.animation as animation #never got this to work \(;^;)/
#wanted to have it strobe through the rainbow

def mag(vect):
    vect = np.array([vect.x,vect.y,vect.z])
    return np.sqrt(vect.dot(vect))

def update(num,points):
        tmp = []
        for element in points:
            m = element
            m.color = (((m.x/800)+(num*.1)%1.0),((m.y/800)+(num*.1)%1.0),((m.z/800)+(num*.1)%1.0))
            tmp.append(m)
        return tmp

class Point(object):
    def __init__(self,x=0.0,y=0.0,z=0.0):
        self.x = x
        self.y = y
        self.z = z
        self.color = None
    @property

    def x(self):
        return self.__x
    @property

    def y(self):
        return self.__y
    @x.setter

    def x(self,x):
        self.__x = float(x)
    @y.setter

    def y(self,y):
        self.__y = float(y)

    def dist(self,point):
        distance = ((self.x-point.x)**2+(self.y-point.y)**2++(self.z-point.z)**2)**0.5
        return distance

    def midpt(self,point):
        x = (point.x + self.x)/2.0
        y = (point.y + self.y)/2.0
        z = (point.z + self.z)/2.0
        return Point(x,y,z)

    def __str__(self):
        string = "({0:.1f},{0:.1f},{0:.1f})".format(self.x,self.y,self.z)
        return string

class CoordinateSystem():
    figf = plt.figure()
    mainplot = figf.add_subplot(1,1,1,projection="3d")
    points = []

    def __init__(self):
        pass

    def plotPoints(self,n):
        v1 = Point(0,0,0)
        CoordinateSystem.mainplot.scatter(v1.x,v1.y,v1.z,c="red",marker="o")
        v2 = Point(400,800,0)
        CoordinateSystem.mainplot.scatter(v2.x,v2.y,v2.z,c="red",marker="o")
        v3 = Point(800,0,0)
        CoordinateSystem.mainplot.scatter(v3.x,v3.y,v3.z,c="red",marker="o")
        v4 = Point(400,400,800)
        CoordinateSystem.mainplot.scatter(v4.x,v4.y,v4.z,c="red",marker="o")
        m = v1.midpt(v2)
        scale = mag(Point(800,800,800))
        m.color = ((m.x/scale),(m.y/scale),(m.z/scale))
        for x in range(0,n,1):
            CoordinateSystem.points.append(m)
            CoordinateSystem.mainplot.scatter(m.x,m.y,m.z,c=((m.x/scale),(m.y/scale),(m.z/scale)),marker="o")
            m = m.midpt(random.choice([v1,v2,v3,v4]))
            m.color = ((m.x/scale),(m.y/scale),(m.z/scale))
        plt.show()

WIDTH = 800
HEIGHT = 800
NUM_POINTS = 15000
x = CoordinateSystem()
x.plotPoints(NUM_POINTS)
