
public enum Direction {
	UP("up", -1, 0, 1), DOWN("down", 1, 0, 5), LEFT("left", 0, -1, 7), RIGHT("right", 0, 1, 3), CENTER("center", 0, 0, 0);

	private final String name;
	private final int xMove;
	private final int yMove;
	private final int directionBit;

	private Direction(String name, int x, int y, int directionBit) {
		this.name = name;
		this.xMove = x;
		this.yMove = y;
		this.directionBit=directionBit;
	}

	public int getX() {
		return xMove;
	}

	public int getY() {
		return yMove;
	}

	public String toString() {
		return this.name;
	}
	
	public int getDirectionBit() {
		return this.directionBit;
	}
}