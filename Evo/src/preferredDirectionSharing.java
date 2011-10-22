import java.util.Random;

public class preferredDirectionSharing extends pathFinder{
   @Override
   public void doMove(){
      for(int i = 0; i < main.creatures.length; i++){
         creature c = main.creatures[i];
         if(c.dead) continue;
         if(c.lengthOfStay < c.movementSpeed){
            c.lengthOfStay++;
         }else{
            place cell = main.map[c.x][c.y];
            int maxFood = -100;
            int maxDir = -1;
            if(c.sight > 0){
               maxDir = c.preferredDirection();
               maxFood = c.look(maxDir);
            }else{
               maxFood = 0;
            }
            if(cell.predatorsHere() > 0){
               int minPred = 10000;
               int minDir = -1;
               for(int j = 0; j < 8; j++){
                  if(c.lookPredators(i) < minPred){
                     minPred = c.lookPredators(i);
                     minDir = i;
                  }
               }
               c.move(minDir);
            }else{
               if(cell.food > 0){
                  c.eat();
               }else if(maxFood > 0){
                  c.move(maxDir);
               }else{
                  c.move(main.rand.nextInt(4));
               }
            }
         }
         for(int j = 0; j < main.creatures.length; j++){
            if(i != j){
               creature o = main.creatures[j];
               if(o.x == c.x && o.y == c.y){
                  if(o.allies.size() < o.cooperation){
                     c.declareAlly(o);
                  }
               }
            }
         }
      }
   }
}
