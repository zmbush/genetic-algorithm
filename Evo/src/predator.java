import java.util.Random;


public class predator extends creature{

	public predator(int xi, int yi) {
		this.x = xi;
		this.y = yi;
	}

   public predator(creature c){
      this.sight = c.sight;
      this.cooperation = c.cooperation;
      this.food = c.food;
      this.fertility = c.fertility;
      this.movementSpeed = c.movementSpeed;
      this.gatheringSpeed = c.gatheringSpeed;
      this.lengthOfStay = c.lengthOfStay;
      this.x = c.x;
      this.y = c.y;
      this.allies = c.allies;
   }
	
	@Override
	public void eat() {
      if(this.food >= 4 && !main.predWin) return;
		place loc = main.map[x][y];
      int gath = (int)Math.ceil(gatheringSpeed / 4.0f);
		if(loc.creaturesHere() >= gath){
			this.food += gath;
			for (int i = gath; i>0; i--){
            loc.crea.get(0).dead = true;
				loc.crea.remove(0);
			}
		}else{
			this.food += loc.creaturesHere();
			for(int i = 0; i<loc.creaturesHere(); i++){
            loc.crea.get(0).dead = true;
				loc.crea.remove(0);
			}
			
		}
	}

   @Override
	public void move(int direction){
		main.map[this.x][this.y].removePredator(this);
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
		main.map[this.x][this.y].setPredator(this);
      lengthOfStay = 0;
	}
	@Override
	public void initRandomness(){
		this.sight = main.rand.nextInt(3)+1;
		this.fertility = main.rand.nextInt(4);
		this.gatheringSpeed = main.rand.nextInt(4)+1;
		this.movementSpeed = main.rand.nextInt(2) + 1;
	}

   @Override
   public int look(int direction){
	   int total = 0;
      float newSight = sight;
      float diagSight = newSight / 1.5f;
      switch(direction){
         case up:
            for(int i = 0; i < newSight && this.y - i >= 0; i++){
               total += main.map[x][y - i].creaturesHere();
            }
            break;
         case down:
            for(int i = 0; i < newSight && this.y + i < main.size - 1; i++){
               total += main.map[x][y + i].creaturesHere();
            }
            break;
         case left:
            for(int i = 0; i < newSight && this.x - i >= 0; i++){
               total += main.map[x - i][y].creaturesHere();
            }
            break;
         case right:
            for(int i = 0; i < newSight && this.x + i < main.size - 1; i++){
               total += main.map[x + i][y].creaturesHere();
            }
            break;
         case ul:
            for (int i = 0; i < diagSight && x - i >= 0 && y - i >= 0; i++){
               total += main.map[x - i][y - i].creaturesHere();
            }
            break;
         case ur:
            for (int i = 0; i < diagSight && x + i < main.size - 1 && y - i >= 0; i++){
               total += main.map[x + i][y - i].creaturesHere();
            }
            break;
         case dr:
            for (int i = 0; i < diagSight && x + i < main.size - 1 && y + i < main.size - 1; i++){
               total += main.map[x + i][y + i].creaturesHere();
            }
            break;
         case dl:
            for (int i = 0; i < diagSight && x - i >= 0 && y + i < main.size - 1; i++){
               total += main.map[x - i][y + i].creaturesHere();
            }
            break;
      }
	   return total;
   }
}
