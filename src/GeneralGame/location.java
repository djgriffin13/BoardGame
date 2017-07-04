package GeneralGame;

public class location {
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
