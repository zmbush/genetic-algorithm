import java.util.LinkedList;
import java.util.Random;

public class place {
	
	LinkedList<creature> crea = new LinkedList<creature>();
	LinkedList<predator> pred = new LinkedList<predator>();
	place(int foo){
		food = foo;
	}
	
	public int predatorsHere(){
		return pred.size();
	}
	
   public int creaturesHere(){
      return creaturesHere(false);
   }
	public int creaturesHere(boolean noHide){
      int retval = 0;
      for(int i = 0; i < crea.size(); i++){
         if(!crea.get(i).dead){
            if((crea.get(i).stealth + (10/main.creatures.length)) < main.rand.nextInt(10) || noHide){
               retval++;
            }
         }
      }
		return retval;
	}
	int food = 0;
	int x = 0;
	int y = 0;
	public void setCreature(creature c) {
		crea.add(c);
	}
	
	public void setPredator(predator p){
		pred.add(p);
	}
	
	public void removeCreature(creature c){
		crea.remove(c);
	}

   public void removePredator(predator p){
      pred.remove(p);
   }
}
