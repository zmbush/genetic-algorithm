import java.util.Random;

public class pathFinder {
   public void doMove(){
      Random r = new Random();
      for(int i = 0; i < main.creatures.length; i++){
         main.creatures[i].move(r.nextInt(4));
      }
   }
}
