#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Sep  9 11:24:06 2022

@author: joan
"""
import numpy as np
import matplotlib.pyplot as plt
import os

path = '/home/joan/Documents/disseny de software/2022-23/materials/nbody3/'
fname = 'choreography.txt'

with open(os.path.join(path, fname),'r') as f:
    data = f.readlines()
    

num_bodies = int(data[0])
x = []
y = []
for d in data[1:]:
    p, q = [float(c) for c in d.replace('\n','').split(' ')]
    x.append(p)
    y.append(q)

x = np.array(x)
y = np.array(y)

x = np.reshape(x, (len(x) // num_bodies,num_bodies))
y = np.reshape(y, (len(y) // num_bodies,num_bodies))


plt.close('all')
plt.figure()
for nb in range(num_bodies):
    plt.plot(x[:,nb], y[:,nb],'-')
    plt.plot(x[-1,nb], y[-1,nb], 'k.') # end point
    
plt.axis('equal')
plt.title(fname)
plt.show(block=False)
