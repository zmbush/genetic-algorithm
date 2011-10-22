import pygame
import sys
import re
import random

def selectColor(c):
   if c == 0:
      return pygame.Color("black")
   elif c == 1:
      return pygame.Color("red")
   elif c == 2:
      return pygame.Color("orange")
   elif c == 3:
      return pygame.Color("yellow")
   elif c == 4:
      return pygame.Color("green")
   else:
      return pygame.Color("red")

def main():
   dims = sys.stdin.readline().strip().split(" ")
   if(len(dims) < 2):
      sys.exit(1)
   xdim = int(dims[0])
   ydim = int(dims[1])
   
   size = [xdim*3, ydim*3]

   pygame.init

   screen = pygame.display.set_mode(size)

   pygame.display.set_caption("Charles Darwin")

   clock = pygame.time.Clock() 

   while True:
      for x in range(xdim):
         for y in range(ydim):
            line = sys.stdin.readline()
            c = random.randint(0, 5)
            color = selectColor(c)
            pygame.draw.rect(screen, color, [x*3, y*3, 3, 3])
      pygame.display.flip()
      clock.tick(15)


   for line in sys.stdin:
      print line



if __name__ == "__main__":
   main()
