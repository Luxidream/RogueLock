public class Location{
	private int item;
	private int x;
	private int y;
	private int heuristic;
	private Location parent;
	
	public Location(int x, int y, int item){
		this.x = x;
		this.y = y;
		this.item = item;
	}
	
	public void setParent(Location parent) {
		this.parent = parent;
	}
	public Location getParent() {
		return parent;
	}
	
	public int getHeuristic() {
		return this.heuristic;
	}
	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getHeuristic(Location a, Location b) {
		int ax = a.getX();
		int ay = a.getY();
		int bx = b.getX();
		int by = b.getY();
		
		int x = Math.abs(ax-bx);
		int y = Math.abs(ay-by);
		
		if (x > y) {
			return x;
		}
		else {
			return y;
		}
	}

	
}