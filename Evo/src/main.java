import java.util.Random;


public class main {
	static int size= 20;
	static place[][] map = new place[size][size];
	static creature[] creatures;
	static pathFinder pf;
	static int rounds = 1000;
	static int roundnum = 0;

	public static void main(String[] args){
		pf = new mostFood();
		System.out.println(size + " " + size);
		initMap(size);
		//		System.out.println("Map initialized.");
		initCreatures(10);
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
//             map[x][y] = new place(4);
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
		pf.doMove();
		//	System.out.println("Running round" + roundnum);
		//
	}

	public static void printInfo(){
		int avgSight = 0;
		int avgCooperation = 0;
		int avgFood = 0;
		int avgFertility = 0;
		int avgMovementSpeed = 0;
		int avgGatheringSpeed = 0;
		for (int i = 0; i<creatures.length; i++) {
			avgSight += creatures[i].sight;
			avgCooperation += creatures[i].cooperation;
			avgFood += creatures[i].food;
			avgFertility += creatures[i].fertility;
			avgMovementSpeed += creatures[i].movementSpeed;
			avgGatheringSpeed += creatures[i].gatheringSpeed;
		}
		avgSight /= creatures.length;
		avgCooperation /= creatures.length;
		avgFood /= creatures.length;
		avgFertility /= creatures.length;
		avgMovementSpeed /= creatures.length;
		avgGatheringSpeed /= creatures.length;

		System.err.println("Average Scores:");
		System.err.println("Sight: " + avgSight);
		System.err.println("Cooperation: " + avgCooperation);
		System.err.println("Food: " + avgFood);
		System.err.println("Fertility: " + avgFertility);
		System.err.println("Movement Speed: " + avgMovementSpeed);
		System.err.println("Gethering Speed: " + avgGatheringSpeed);
	}
}
