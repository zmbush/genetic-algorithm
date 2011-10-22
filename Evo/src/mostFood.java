import java.util.Random;

public class mostFood extends pathFinder{
   @Override
   public void doMove(){
      Random r = new Random();
      for(int i = 0; i < main.creatures.length; i++){
         creature c = main.creatures[i];
         place cell = main.map[c.x][c.y];
         if(cell.food > 0){
            c.eat();
         }else{
            if(c.sight > 0){
               int maxFood = -100;
               int maxDir = -1;
               if(c.x > 0 && main.map[c.x-1][c.y].food > maxFood){
                  maxFood = main.map[c.x-1][c.y].food;
                  maxDir = creature.left;
               }
               if(c.y > 0 && main.map[c.x][c.y-1].food > maxFood){
                  maxFood = main.map[c.x][c.y-1].food;
                  maxDir = creature.up;
               }
               if(c.y < main.map[0].length - 1 && 
                     main.map[c.x][c.y+1].food > maxFood){
                  maxFood = main.map[c.x][c.y+1].food;
                  maxDir = creature.down;
               }
               if(c.x < main.map.length - 1 && 
                     main.map[c.x + 1][c.y].food > maxFood){
                  maxFood = main.map[c.x+1][c.y].food;
                  maxDir = creature.right;
               }
               if(maxFood != 0){
                  System.err.println("Direction: " + maxDir);
                  c.move(maxDir);
               }else{
                  c.move(r.nextInt(4));
               }
            }else{
               c.move(r.nextInt(4));
            }
         }
      }
   }
}
