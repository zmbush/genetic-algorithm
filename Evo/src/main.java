import java.util.Random;


public class main {
	static int size= 20;
	static place[][] map = new place[size][size];
	static creature[] creatures;
	static int rounds = 10;
	static int roundnum = 0;

	public static void main(String[] args){
		System.out.println(size + " " + size);
		initMap(size);
		//		System.out.println("Map initialized.");
		initCreatures(500);
		//		System.out.println("Creatures initialized.");
		displayMap();
		while (roundnum<rounds){
			runRound();
			displayMap();
			roundnum++;
		}
	}

	public static void initMap(int mapSize){
		Random c = new Random();
		for(int x=0; x<mapSize; x++ ){
			for (int y=0; y<mapSize; y++){
				map[x][y]= new place(c.nextInt(5));
			}
		}
	}

	public static void initCreatures(int num){
		creatures = new creature[num];
		Random r = new Random();
		int counter = 0;
		for(int i = 0; i < num; i++){
			creature c = new creature(r.nextInt(size), r.nextInt(size));
			creatures[i] = c;
			map[c.x][c.y].setCreature(c);
		}
	}

	public static void displayMap(){
		for(int x = 0; x < map.length; x++){
			for(int y = 0; y < map[x].length; y++){
				System.out.println(map[x][y].food + " " + map[x][y].creaturesHere());
			}
		}
	}

	public static void runRound(){
		//	System.out.println("Running round" + roundnum);
	}
}
