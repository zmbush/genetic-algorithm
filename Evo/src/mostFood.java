import java.util.Random;
 
public class mostFood extends pathFinder{
   @Override
   public void doMove(){
      Random r = new Random();
      for(int i = 0; i < main.creatures.length; i++){
         creature c = main.creatures[i];
         if(c.lengthOfStay < c.movementSpeed){
            c.lengthOfStay++;
         }else{
            place cell = main.map[c.x][c.y];
            int maxFood = -100;
            int maxDir = -1;
            if(c.sight > 0){
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
            }else{
               maxFood = 0;
            }
            if(cell.food > 0){
               c.eat();
            }else if(maxFood > 0){
               c.move(maxDir);
            }else{
               c.move(r.nextInt(4));
            }
         }
      }
   }
}
