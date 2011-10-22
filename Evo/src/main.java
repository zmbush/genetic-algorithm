import java.util.Random;


public class main {
	static int size= 20;
	static place[][] map = new place[size][size];
	static creature[] creatures = new creature[size];

	public static void main(String[] args){
		initMap(size);
		initCreatures(40);
	}
	
	public static void initMap(int mapSize){
		Random c = new Random();
		for(int x=0; x<mapSize; x++ ){
			for (int y=0; y<mapSize; y++){
				map[x][y]= new place(c.nextInt(3));
			}
		}
	}
	
	public static void initCreatures(int num){
		Random c = new Random();
		int counter = 0;
		for (int x=0; x<num; x++){
			creatures[counter] = new creature(c.nextInt(size-1), c.nextInt(size-1));
			counter++;
		}
	}
}
