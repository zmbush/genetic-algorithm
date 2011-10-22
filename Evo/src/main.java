import java.util.Iterator;
import java.util.Random;
import java.util.LinkedList;


public class main {
	static int size= 20;
	static int threshold = 20;
	static int predatorThreshold = 3;
	static place[][] map = new place[size][size];
	static creature[] creatures;
	static predator[] preds;
	static pathFinder pf;
	static pathFinder ps;
	static int generations = 10000;
	static int gennum = 0;
   static boolean predators = false;
   static boolean predWin = false;
   static Random rand = new Random();

	public static void main(String[] args){
      for(int i = 0; i < args.length; i++){
         System.err.println(args[i]);
      }
		initMap(size);
      if(args.length > 0){
         predators = true;
         if(args[0].equals("no-pred")){
            predators = false;
         }else if(args[0].equals("pred-win")){
            predWin = true;
            initCreatures(20);
            initPredators(4);
         }else if(args[0].equals("pred-lose")){
            predWin = false;
            initCreatures(40);
            initPredators(2);
         }
      }
		pf = new preferredDirectionSharing();
		ps = new predatorStrat();
		System.out.println(size + " " + size);
		//		System.out.println("Map initialized.");
		//		System.out.println("Creatures initialized.");
		initPredators(2);
		displayMap();
      //System.err.println("Population,Sight,Cooperation,Food,Fertility,Movement Speed,Gathering Speed");
		while (gennum < generations){
			//System.err.println("Agents: " + creatures.length);
			runGeneration();
			//          System.err.println("Before sharing: ");
			//          creature[] before = sortCreaturesByFood();
			//          for(int i = 0; i < before.length; i++){
			//             System.err.println(before[i].food);
			//          }
         share();
			//          System.err.println("After sharing: ");
			//          creature[] after = sortCreaturesByFood();
			//          for(int i = 0; i < after.length; i++){
			//             System.err.println(after[i].food);
			//          }
			//System.err.println();
			printInfo();
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
		for(int i = 0; i < num; i++){
			creature c = new creature(r.nextInt(size), r.nextInt(size));
			c.initRandomness();
			creatures[i] = c;
			map[c.x][c.y].setCreature(c);
		}
	}

	public static void initPredators(int num){
		preds = new predator[num];
		Random r = new Random();
		for(int i = 0; i < num; i++){
			predator c = new predator(r.nextInt(size), r.nextInt(size));
			c.initRandomness();
			preds[i] = c;
			map[c.x][c.y].setPredator(c);
		}
	}


	public static void displayMap(){
		for(int x = 0; x < map.length; x++){
			for(int y = 0; y < map[x].length; y++){
				System.out.println(map[x][y].food + " " + map[x][y].creaturesHere() 
               + " " + ((predators) ? map[x][y].predatorsHere() : 0));
			}
		}
	}

	public static void runGeneration(){
		for(int i = 0; i < 100 && totalFood() > 0; i++){
			//while(totalFood() > 0){

         if(predators){
            ps.doMove();
         }
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
		for(int i = 0; i < sorted.length && sorted[i].food >= threshold; i++){
         if(sorted[i].dead) continue;
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

      predator[] psort = sortPredatorsByFood();

      LinkedList<predator> survivor = new LinkedList<predator>();

      for(int i = 0 ; i < psort.length && psort[i].food >= predatorThreshold; i++){
         survivor.add(psort[i]);
      }

      LinkedList<predator> predNextGen = new LinkedList<predator>();
      for(int i = 0; i < survivor.size(); i++){
         int one = i + r.nextInt(survivor.size());
         if(one >= survivor.size()) one -= survivor.size();
         creature[] children = survivor.get(i).mateWith((creature)survivor.get(one));
         if(children != null){
            for(int j = 0; j < children.length; j++){
               predNextGen.add(new predator(children[j]));
            }
         }
      }

      preds = predNextGen.toArray(new predator[0]);
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

	public static predator[] sortPredatorsByFood(){
		predator[] retval = preds;
		for(int i = 0; i < retval.length; i++){
			for(int j = 0; j < retval.length - i - 1; j++){
				if(retval[j].food < retval[j+1].food){
					predator temp = retval[j];
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

	public static void share() {
		for (int i = 0; i<creatures.length; i++){
			creature current = creatures[i];
			Iterator<creature> iter = current.allies.listIterator();
			creature taker;
			while(iter.hasNext()){
				taker = iter.next();
				while (current.food > threshold + 1){
					if (taker.food < threshold + 1){
						current.food--;
						taker.food++;
					}else{
						break;
					}
				}
			}
		}
		// Suicide...
      for(int x = 0; x < 10; x++){
         for(int i = 0; i < creatures.length; i++){
            creature c = creatures[i];
            Iterator<creature> iter = c.allies.listIterator();
            float maxFood = -1;
            creature t, r = null;
            while(iter.hasNext()){
               t = iter.next();
               if(c.food > maxFood){
                  maxFood = t.food;
                  r = t;
               }
            }
            if(r != null){
               if(maxFood < threshold + 1 && maxFood < c.food){
                  float need = threshold - r.food;
                  if(c.food >= need){
                     c.food -= need;
                     r.food += need;
                  }else{
                     r.food += c.food;
                     c.food = 0;
                  }
               }
            }
         }
      }
	}

	public static void printInfo(){
		float avgSight = 0;
		float avgCooperation = 0;
		float avgFood = 0;
		float avgFertility = 0;
		float avgMovementSpeed = 0;
		float avgGatheringSpeed = 0;
      float avgStealth = 0;
		for (int i = 0; i<creatures.length; i++) {
			avgSight += creatures[i].sight;
			avgCooperation += creatures[i].cooperation;
			avgFood += creatures[i].food;
			avgFertility += creatures[i].fertility;
			avgMovementSpeed += creatures[i].movementSpeed;
			avgGatheringSpeed += creatures[i].gatheringSpeed;
         avgStealth += creatures[i].stealth;
		}
		avgSight /= creatures.length;
		avgCooperation /= creatures.length;
		avgFood /= creatures.length;
		avgFertility /= creatures.length;
		avgMovementSpeed /= creatures.length;
		avgGatheringSpeed /= creatures.length;
      avgStealth /= creatures.length;

      System.err.println("Creatures: " + creatures.length);
      System.err.println("Predators: " + preds.length);
      System.err.println();
		System.err.println("Average Scores:");
		System.err.println("Sight: " + avgSight);
		System.err.println("Cooperation: " + avgCooperation);
		System.err.println("Food: " + avgFood);
		System.err.println("Fertility: " + avgFertility);
		System.err.println("Movement Speed: " + avgMovementSpeed);
		System.err.println("Gathering Speed: " + avgGatheringSpeed);
      System.err.println("Stealth: " + avgStealth);
		System.err.println();
//       System.err.println(creatures.length + "," + avgSight + "," + avgCooperation + "," + avgFood + "," + avgFertility + "," + avgMovementSpeed + "," + avgGatheringSpeed);
	}
}
