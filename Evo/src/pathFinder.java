import java.util.Random;

public class pathFinder {
   public void doMove(){
      Random r = new Random();
      for(int i = 0; i < main.creatures.length; i++){
         if(main.creatures[i].lengthOfStay < main.creatures[i].movementSpeed){
            main.creatures[i].lengthOfStay++;
         }else{
            main.creatures[i].move(r.nextInt(4));
         }
      }
   }
}
