import java.util.Random;

public class predatorStrat extends pathFinder{
	@Override
	public void doMove(){
		Random r = new Random();
		for(int i = 0; i < main.preds.length; i++){
			predator p = main.preds[i];
			if(main.map[p.x][p.y].creaturesHere()>0){
				p.eat();
			}else{
				p.move(r.nextInt(8));
			}
		}
	}
}
