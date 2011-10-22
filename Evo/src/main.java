import java.util.Random;
import java.util.LinkedList;


public class main {
	static int size= 20;
	static place[][] map = new place[size][size];
	static creature[] creatures;
	static pathFinder pf;
	static int generations = 100;
	static int gennum = 0;

	public static void main(String[] args){
		pf = new mostFoodInSight();
		System.out.println(size + " " + size);
      initMap(size);
		//		System.out.println("Map initialized.");
		initCreatures(10);
		//		System.out.println("Creatures initialized.");
		displayMap();
		while (gennum < generations){
         System.err.println("Agents: " + creatures.length);
			runGeneration();
         //printInfo();
         createNextGeneration();
         initMap(size);
			gennum++;
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
         c.initRandomness();
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

	public static void runGeneration(){
      //for(int i = 0; i < 300 && totalFood() > 0; i++){
      while(totalFood() > 0){
         pf.doMove();
         displayMap();
      }
	}

   public static void createNextGeneration(){
      creature[] sorted = sortCreaturesByFood();
//       System.err.println("Food Distribution: ");
//       for(int i = 0; i < sorted.length; i++){
//          System.err.println(sorted[i].food);
//       }

      LinkedList<creature> fit = new LinkedList<creature>();
      for(int i = 0; i < sorted.length && sorted[i].food >= 20; i++){
//          System.err.println("Adding fit creature: " + sorted[i].food);
         fit.add(sorted[i]);
      }

      Random r = new Random();
      LinkedList<creature> nextGen = new LinkedList<creature>();
      for(int i = 0; i < fit.size(); i++){
         int one = i + r.nextInt(fit.size());
         if (one >= fit.size()) one -= fit.size();
         creature[] children = fit.get(i).mateWith(fit.get(one));
         if(children != null){
            for(int j = 0; j < children.length; j++){
//                System.err.println("Adding new child");
               nextGen.add(children[j]);
            }
         }
      }

      creatures = nextGen.toArray(new creature[0]);
   }

   public static creature[] sortCreaturesByFood(){
      creature[] retval = creatures;
      for(int i = 0; i < retval.length; i++){
         for(int j = 0; j < retval.length - i - 1; j++){
            if(retval[j].food < retval[j+1].food){
               creature temp = retval[j];
               retval[j] = retval[j+1];
               retval[j+1] = temp;
            }
         }
      }
      return retval;
   }

   public static int totalFood(){
      int retval = 0;
      for(int x = 0; x < map.length; x++){
         for(int y = 0; y < map[x].length; y++){
            retval += map[x][y].food;
         }
      }
      return retval;
   }
	
	public static void printInfo(){
		float avgSight = 0;
		float avgCooperation = 0;
		float avgFood = 0;
		float avgFertility = 0;
		float avgMovementSpeed = 0;
		float avgGatheringSpeed = 0;
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
		System.err.println("Gathering Speed: " + avgGatheringSpeed);
      System.err.println();
	}
}
