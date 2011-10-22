import java.util.LinkedList;


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
		return crea.size();
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
}
