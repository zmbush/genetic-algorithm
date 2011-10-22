import java.util.Random;


public class creature {
	float sight = 2;
	float cooperation = 4;
	float food = 0;
	float fertility = 4;
	float movementSpeed= 4;
	float gatheringSpeed = 4;
	int x = 0;
	int y= 0;
	//path finding
	
	//DIRECTIONS
	public static final int up = 0;
	public static final int right = 1;
	public static final int down = 2;
	public static final int left = 3;
	
	creature(int xi, int yi){
		x = xi;
		y = yi;
	}
	
	creature(){		
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
		main.map[this.x][this.y].setCreature(this);
	}

   public void eat(){
      place loc = main.map[x][y];

      float foodRemain = loc.food;
      if(loc.food >= gatheringSpeed/4){
         this.food += gatheringSpeed/4;
         loc.food -= gatheringSpeed/4;
      }else{
         this.food = loc.food;
         loc.food = 0;
      }
      
   }
	
	public creature mateWith(creature mate){
		Random c = new Random();
		creature baby = new creature(mate.x, mate.y);
		baby.fertility = (this.fertility + mate.fertility)/2 + c.nextFloat()*norp();
		baby.sight = (this.sight + mate.sight)/2 + c.nextFloat()*norp();
		baby.cooperation = (this.cooperation + mate.cooperation)/2 + c.nextFloat()*norp();
		return baby;
	}
	
	public int norp(){
		Random c = new Random();
		int n = 1;
		if (c.nextInt(1) == 1){
			n = -1;
		}
		return n;
	}
}
