import java.util.Random;

public class predatorStrat extends pathFinder{
   @Override
   public void doMove(){
      Random r = new Random();
      for(int i = 0; i < main.preds.length; i++){
         predator p = main.preds[i];
         if(p.lengthOfStay < p.movementSpeed){
            p.lengthOfStay++;
         }else{
            place cell = main.map[p.x][p.y];
            int maxFood = -100;
            int maxDir = -1;
            if(p.sight > 0){
               if(p.look(predator.up) > maxFood){
                  maxFood = p.look(predator.up);
                  maxDir = predator.up;
               }
               if(p.look(predator.right) > maxFood){
                  maxFood = p.look(predator.right);
                  maxDir = predator.right;
               }
               if(p.look(predator.down) > maxFood){
                  maxFood = p.look(predator.down);
                  maxDir = predator.down;
               }
               if(p.look(predator.left) > maxFood){
                  maxFood = p.look(predator.left);
                  maxDir = predator.left;
               }
            }else{
               maxFood = 0;
            }
            if(cell.food > 0){
               p.eat();
            }else if(maxFood > 0){
               p.move(maxDir);
            }else{
               p.move(r.nextInt(4));
            }
         }
      }
   }
}
