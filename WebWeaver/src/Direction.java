
public enum Direction {
	UP("up", -1, 0), DOWN("down", 1, 0), LEFT("left", 0, -1), RIGHT("right", 0, 1), CENTER("center", 0, 0);

	private final String name;
	private final int xMove;
	private final int yMove;

	private Direction(String name, int x, int y) {
		this.name = name;
		this.xMove = x;
		this.yMove = y;
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
}