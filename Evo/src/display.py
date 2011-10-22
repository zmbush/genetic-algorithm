import pygame
import sys
import re

def main():
   dims = sys.stdin.readline().strip().split(" ")
   if(len(dims) < 2):
      sys.exit(1)
   xdim = int(dims[0])
   ydim = int(dims[1])
   
   print str(xdim) + ", " + str(ydim)



if __name__ == "__main__":
   main()
