import java.util.Random;


public class main {
	static int size= 20;
	static place[][] map = new place[size][size];
	static creature[][] cmap = new creature[size][size];
   static creature[] creatures;

	public static void main(String[] args){
		initMap(size);
		initCreatures(size);
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
      creatures = new creature[num];
		Random c = new Random();
		int counter = 0;
      for(int i = 0; i < num; i++){
         creature c = new creature(c.nextInt(size-1), c.nextInt(size-1));
         creatures[i] = c;
         map[c.x][c.y].setCreature(c);
      }
	}

   public static void displayMap(){
      for(int x = 0; x < map.length; x++){
         for(int y = 0; y < map[x].length; y++){
            System.out.println(map.food + " " + map.creaturesHere());
         }
      }
   }
}
