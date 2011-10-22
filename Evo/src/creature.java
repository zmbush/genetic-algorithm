import java.util.Random;


public class creature {
	float sight = 4;
	float cooperation = 4;
	float food = 0;
	float fertility = 4;
	int x = 0;
	int y= 0;
	//path finding
	
	creature(int xi, int yi){
		x = xi;
		y = yi;
	}
	
	creature(){
		
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
