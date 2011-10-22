import java.util.LinkedList;


public class place {
	
	LinkedList<creature> crea = new LinkedList<creature>();
	place(int foo){
		food = foo;
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
}
