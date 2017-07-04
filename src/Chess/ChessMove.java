package Chess;
import GeneralGame.GameMove;
import GeneralGame.location;

public class ChessMove extends GameMove{
private location origin;
private location destination;
public ChessMove(int ox, int oy, int dx, int dy){
	origin = new location(ox,oy);
	destination = new location(dx,dy);
}
public ChessMove(location o,location d){
	origin = o;
	destination = d;
}
public location getOrigin(){
	return origin;
}
public location getDestination(){
	return destination;
}
}
