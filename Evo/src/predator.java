
public class predator extends creature{

	@Override
	public void eat() {
		place loc = main.map[x][y];
		if(loc.creaturesHere() >= (int) gatheringSpeed/4){
			this.food += (int) gatheringSpeed/4;
			for (int i = ((int) gatheringSpeed/4); i>0; i--){
				
			}
		}else{
			this.food += loc.food;
			loc.food = 0;
		}
	}
}
