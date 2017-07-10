package GeneralGame;

/**
 * @author Daniel
 * This is veriable that represents a specific board location
 */
public class location {
/**
 * x and y corodanats of a 2 dementional location. x is associated with rows and y with columns
 */
public int x,y;
public location(int x,int y){
	this.x = x;
	this.y = y;
}
public boolean equals(location l){
	if (x==l.x && y == l.y)return true;
	return false;
}
}
