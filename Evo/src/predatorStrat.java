import java.util.Random;

public class predatorStrat extends pathFinder{
	@Override
	public void doMove(){
		for(int i = 0; i < main.preds.length; i++){
			predator p = main.preds[i];
			if(main.map[p.x][p.y].creaturesHere()>0){
				p.eat();
			}else{
            int maxFood = -1;
            int maxDir = -1;
            for(int j = 0; j < 8; j++){
               if(p.look(j) > maxFood){
                  maxFood = p.look(j);
                  maxDir = j;
               }
            }
            if(maxFood > 0){
				   p.move(maxDir);
            }else{
               p.move(main.rand.nextInt(4));
            }
			}
		}
	}
}
