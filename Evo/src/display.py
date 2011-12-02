import pygame
import sys
import re
import random

def selectColor(c):
   if c == 0:
      return pygame.Color(139, 69, 19)
   elif c == 1:
      return pygame.Color(20, 204, 10, 0)
   elif c == 2:
      return pygame.Color(20, 153, 10, 0)
   elif c == 3:
      return pygame.Color(20, 102, 10, 0)
   elif c == 4:
      return pygame.Color(20, 51, 10, 0)

def main():
   dims = sys.stdin.readline().strip().split(" ")
   if(len(dims) < 2):
      sys.exit(1)
   xdim = int(dims[0])
   ydim = int(dims[1])
   
   size = [xdim*35, ydim*35]

   pygame.init

   screen = pygame.display.set_mode(size)

   pygame.display.set_caption("Charles Darwin")

   clock = pygame.time.Clock() 

   while True:
      for event in pygame.event.get():
         if event.type == pygame.QUIT:
            pygame.quit()
            return
      for x in range(xdim):
         for y in range(ydim):
            line = sys.stdin.readline().strip().split(" ")
            if line[0] != '':
               c = int(line[0])
               color = selectColor(c)
               pygame.draw.rect(screen, color, [x*35, y*35, 35, 35])
               xpos = 0
               ypos = 0
               for p in range(int(line[1])):
                  pygame.draw.rect(screen, pygame.Color("white"), 
                     [x*35 + 5 + xpos*10,
                      y*35 + 5 + ypos*10, 5, 5])
                  xpos += 1
                  if xpos >= 3:
                     xpos = 0
                     ypos += 1
                  if ypos >= 3:
                     break
               if ypos < 3:
                  for p in range(int(line[2])):
                     pygame.draw.rect(screen, pygame.Color("black"), 
                        [x*35 + 5 + xpos*10,
                         y*35 + 5 + ypos*10, 5, 5])
                     xpos += 1
                     if xpos >= 3:
                        xpos = 0
                        ypos += 1
                     if ypos >= 3:
                        break
            else:
               break
      pygame.display.flip()
      clock.tick(200)


   for line in sys.stdin:
      print line



if __name__ == "__main__":
   main()
