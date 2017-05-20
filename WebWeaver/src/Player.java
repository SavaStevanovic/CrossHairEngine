import java.net.InetAddress;
import java.util.Date;

import com.google.gson.JsonObject;

public class Player implements TileObject {
	private int x, y;
	private Move move;

	public Player(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.move = new Move(Direction.CENTER);
	}

	public void initiateMovePlayer(Move move) {
		if (new Date().getTime() - this.move.getMoveStart() > Constants.FieldParams.baseTurnLength) {
			if (!move.isExecuted()) {
				movePlayer();
			}
			this.move = move;
		}
	}

	private void movePlayer() {
		move.execute();
		Direction direction = move.getDirection();
		this.x += direction.getX();
		this.y += direction.getY();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void sync(){
		long time = new Date().getTime() - move.getMoveStart();
		if (!move.isExecuted() && time > Constants.FieldParams.baseTurnLength / 2) {
			movePlayer();
		}
	}
	
	@Override
	public JsonObject getFieldObject() {
		long time = new Date().getTime() - move.getMoveStart();
		JsonObject json = new JsonObject();
		json.addProperty("direction", move.getDirection().toString());
		json.addProperty("startTime", time);
		json.addProperty("executed", move.isExecuted());
		json.addProperty("type", "player");
		json.addProperty("x", x);
		json.addProperty("y", y);
		return json;
	}
}
