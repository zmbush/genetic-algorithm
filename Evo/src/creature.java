import java.util.LinkedList;
import java.util.Random;


public class creature {
	float sight = 2; //0-5
	float cooperation = 4;  // max number that a creature will share with.
	float food = 0; 
	float fertility = 4; // 0-3
	float movementSpeed= 4; //inverse min 1
	float gatheringSpeed = 4; // 0-8
	float stealth = 2;
	int lengthOfStay = 0;
   boolean dead = false;
	int x = 0;
	int y= 0;
	LinkedList<creature> allies = new LinkedList<creature>();
	//path finding
	
	//DIRECTIONS
	public static final int up = 0;
	public static final int right = 1;
	public static final int down = 2;
	public static final int left = 3;
   public static final int ul = 4;
   public static final int ur = 5;
   public static final int dr = 6;
   public static final int dl = 7;
	
	creature(int xi, int yi){
		x = xi;
		y = yi;
	}
	
	creature(){		
	}
	
	public void initRandomness() {
		Random r = new Random();
		this.sight = r.nextInt(4);
		this.fertility = r.nextInt(6);
		this.gatheringSpeed = r.nextInt(6);
		this.movementSpeed = r.nextInt(2) + 1;
		this.stealth = r.nextInt(6);
	}
	
	public void declareAlly(creature friend){
		if(friend.cooperation > friend.allies.size()){
			this.allies.add(friend);
			friend.allies.add(this);
		}
	}

	public void move(int direction){
		main.map[this.x][this.y].removeCreature(this);
		if(direction == up && this.y>0){
			this.y--;
		}
		if(direction == down && this.y<main.size-1){
			this.y++;
		}
		if(direction == left && this.x>0){
			this.x--;
		}
		if(direction == right && this.x<main.size-1) {
			this.x++;
		}
      if(direction == ul){
         if(look(up) > look(left)){
            move(up);
         }else{
            move(left);
         }
         return;
      }
      if(direction == ur){
         if(look(up) > look(right)){
            move(up);
         }else{
            move(right);
         }
         return;
      }
      if(direction == dr){
         if(look(down) > look(right)){
            move(down);
         }else{
            move(right);
         }
         return;
      }
      if(direction == dl){
         if(look(down) > look(left)){
            move(down);
         }else{
            move(left);
         }
         return;
      }
		main.map[this.x][this.y].setCreature(this);
      lengthOfStay = 0;
	}

   public void eat(){
      place loc = main.map[x][y];
      if(loc.food >= gatheringSpeed/4){
         this.food += gatheringSpeed/4;
         loc.food -= gatheringSpeed/4;
      }else{
         this.food += loc.food;
         loc.food = 0;
      }
      
   }
   
   public int lookPredators(int direction){
	   int total = 0;
      float newSight = sight;
      float diagSight = newSight / 1.5f;
      switch(direction){
         case up:
            for(int i = 0; i < newSight && this.y - i >= 0; i++){
               total += main.map[x][y - i].predatorsHere();
            }
            break;
         case down:
            for(int i = 0; i < newSight && this.y + i < main.size - 1; i++){
               total += main.map[x][y + i].predatorsHere();
            }
            break;
         case left:
            for(int i = 0; i < newSight && this.x - i >= 0; i++){
               total += main.map[x - i][y].predatorsHere();
            }
            break;
         case right:
            for(int i = 0; i < newSight && this.x + i < main.size - 1; i++){
               total += main.map[x + i][y].predatorsHere();
            }
            break;
         case ul:
            for (int i = 0; i < diagSight && x - i >= 0 && y - i >= 0; i++){
               total += main.map[x - i][y - i].predatorsHere();
            }
            break;
         case ur:
            for (int i = 0; i < diagSight && x + i < main.size - 1 && y - i >= 0; i++){
               total += main.map[x + i][y - i].predatorsHere();
            }
            break;
         case dr:
            for (int i = 0; i < diagSight && x + i < main.size - 1 && y + i < main.size - 1; i++){
               total += main.map[x + i][y + i].predatorsHere();
            }
            break;
         case dl:
            for (int i = 0; i < diagSight && x - i >= 0 && y + i < main.size - 1; i++){
               total += main.map[x - i][y + i].predatorsHere();
            }
            break;
      }
	   return total;
   }
   public int look(int direction){
	   int total = 0;
      float newSight = sight;
      float diagSight = newSight / 1.5f;
      switch(direction){
         case up:
            for(int i = 0; i < newSight && this.y - i >= 0; i++){
               total += main.map[x][y - i].food;
            }
            break;
         case down:
            for(int i = 0; i < newSight && this.y + i < main.size - 1; i++){
               total += main.map[x][y + i].food;
            }
            break;
         case left:
            for(int i = 0; i < newSight && this.x - i >= 0; i++){
               total += main.map[x - i][y].food;
            }
            break;
         case right:
            for(int i = 0; i < newSight && this.x + i < main.size - 1; i++){
               total += main.map[x + i][y].food;
            }
            break;
         case ul:
            for (int i = 0; i < diagSight && x - i >= 0 && y - i >= 0; i++){
               total += main.map[x - i][y - i].food;
            }
            break;
         case ur:
            for (int i = 0; i < diagSight && x + i < main.size - 1 && y - i >= 0; i++){
               total += main.map[x + i][y - i].food;
            }
            break;
         case dr:
            for (int i = 0; i < diagSight && x + i < main.size - 1 && y + i < main.size - 1; i++){
               total += main.map[x + i][y + i].food;
            }
            break;
         case dl:
            for (int i = 0; i < diagSight && x - i >= 0 && y + i < main.size - 1; i++){
               total += main.map[x - i][y + i].food;
            }
            break;
      }
	   return total;
   }

   public int preferredDirection(){
      int maxFood = -100;
      int dir = -1;
      for(int i = 0; i < 8; i++){
         if(maxFood < look(i)){
            maxFood = look(i);
            dir = i;
         }
      }
      return dir;
   }

   public creature[] mateWith(creature mate){
	   int numOfChildren = (int) ((this.fertility+mate.fertility) / 2);
	   if (numOfChildren == 0){
		   return null;
	   }
	   creature[] children = new creature[numOfChildren];
	   for (int i=0; i<numOfChildren; i++){
		   children[i] = this.produce(mate);
	   }
	   
	   return children;
   }
	
	public creature produce(creature mate){
		Random c = new Random();
		creature baby = new creature(mate.x, mate.y);
		float nf = (this.fertility + mate.fertility)/2 + c.nextFloat()*norp();
		if (nf> 8){
			baby.fertility = 8;
		}else if( nf<0){
			baby.fertility= 0;
		}else{
			baby.fertility = nf;
		}
		
		float nst = (this.stealth + mate.stealth)/2 + c.nextFloat()*norp();
		if (nst> 6){
			baby.stealth = 6;
		}else if( nst<0){
			baby.stealth= 0;
		}else{
			baby.stealth = nst;
		}
		
		float ns = (this.sight + mate.sight)/2 + c.nextFloat()*norp();
		if (ns> 5){
			baby.sight = 5;
		}else if(ns<0){
			baby.sight = 0;
		}else
			baby.sight = ns;
		
		float nc = (this.cooperation + mate.cooperation)/2 + c.nextFloat()*norp();
		baby.cooperation = nc;
		
		float nms = (this.movementSpeed + mate.movementSpeed)/2 +c.nextFloat()*norp();
		if (nms <1){
			baby.movementSpeed = 1;
		}else{
			baby.movementSpeed = nms;
		}
		
		float ngs = (this.gatheringSpeed + mate.gatheringSpeed)/2 + c.nextFloat()*norp();
		if (ngs<0){
			baby.gatheringSpeed = 0;
		} else if(ngs>8){
			baby.gatheringSpeed = 8;
		} else{
			baby.gatheringSpeed = ngs;
		}
		
		baby.x = c.nextInt(main.size);
		baby.y = c.nextInt(main.size);
		
		return baby;
	}
	
	public int norp(){
		Random c = new Random();
		int n = 1;
		if (c.nextInt(2) == 1){
			n = -1;
		}
		return n;
	}
}
