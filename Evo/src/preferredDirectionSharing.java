import java.util.Random;

public class preferredDirectionSharing extends pathFinder{
   @Override
   public void doMove(){
      Random r = new Random();
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
            if(cell.food > 0){
               c.eat();
            }else if(maxFood > 0){
               c.move(maxDir);
            }else{
               c.move(r.nextInt(4));
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
