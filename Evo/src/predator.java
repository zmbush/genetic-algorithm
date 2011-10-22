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
		place loc = main.map[x][y];
		if(loc.creaturesHere() >= (int) gatheringSpeed/4){
			this.food += (int) gatheringSpeed/4;
			for (int i = ((int) gatheringSpeed/4); i>0; i--){
				loc.crea.remove(0);
			}
		}else{
			this.food += loc.creaturesHere();
			for(int i = 0; i<loc.creaturesHere(); i++){
				loc.crea.remove(0);
			}
			
		}
	}
	@Override
	public void initRandomness(){
		Random r = new Random();
		this.sight = r.nextInt(3)+1;
		this.fertility = r.nextInt(4);
		this.gatheringSpeed = r.nextInt(4)+1;
		this.movementSpeed = r.nextInt(2) + 1;
	}

}
