import java.util.Random;


public class cooperate extends pathFinder{
	@Override
	public void doMove(){
		Random r = new Random();
		for(int i = 0; i < main.creatures.length; i++){
			creature c = main.creatures[i];
			if(c.lengthOfStay < c.movementSpeed){
				c.lengthOfStay++;
			}else{
				place cell = main.map[c.x][c.y];
				int maxFood = -100;
				int maxDir = -1;
				if(c.sight > 0){
					if(c.look(creature.up) > maxFood){
						maxFood = c.look(creature.up);
						maxDir = creature.up;
					}
					if(c.look(creature.right) > maxFood){
						maxFood = c.look(creature.right);
						maxDir = creature.right;
					}
					if(c.look(creature.down) > maxFood){
						maxFood = c.look(creature.down);
						maxDir = creature.down;
					}
					if(c.look(creature.left) > maxFood){
						maxFood = c.look(creature.left);
						maxDir = creature.left;
					}
				}else{
					maxFood = 0;
				}
				if(cell.food > 0){
					c.eat();
				}else if(maxFood > 0){
					c.move(maxDir);
				}else{
					c.move(r.nextInt(4));
				}
			}
		}
	}
}
