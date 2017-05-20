import java.util.Date;

public class Move {
	private Direction direction;
	private long moveStart;
	private boolean executed;
	
	public Move(Direction direction) {
		this.direction=direction;
		this.moveStart = new Date().getTime();
		this.executed=false;
	}

	public void execute(){
		this.executed=true;
	}
	
	public boolean isExecuted() {
		return executed;
	}

	public long getMoveStart() {
		return moveStart;
	}

	public Direction getDirection() {
		return direction;
	}
}
