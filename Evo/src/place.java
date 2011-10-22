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
      int retval = 0;
      Random r = new Random();
      for(int i = 0; i < crea.size(); i++){
         if(!crea.get(i).dead && crea.get(i).stealth < r.nextInt(10)){
            retval++;
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
