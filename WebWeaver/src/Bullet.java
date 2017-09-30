import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Bullet implements TileObject, Runnable {
	private String bulletID;
	private String playerAdress;
	private Field field;
	private int x, y;

	private int travelDistance;
	private Move move;
	private boolean active;

	public Bullet(String playerAdress, Field field, int x, int y, Move move, int travelDistance) {
		super();
		active = true;
		this.field = field;
		this.bulletID = UUID.randomUUID().toString();
		this.playerAdress = playerAdress;
		this.x = x + move.getDirection().getX();
		this.y = y + move.getDirection().getY();
		this.travelDistance = travelDistance;
		if (move.getDirection() == Direction.CENTER) {
			move = new Move(Direction.RIGHT);
		}
		this.move = move;
		field.addFObject(this);
		sync();
	}

	@Override
	public JsonObject getFieldObject() {
		long time = new Date().getTime() - move.getMoveStart();
		JsonObject json = new JsonObject();
		json.addProperty("direction", move.getDirection().toString());
		json.addProperty("startTime", time);
		json.addProperty("active", active);
		json.addProperty("executed", move.isExecuted());
		json.addProperty("moveTime", Constants.FieldParams.baseTurnLength);
		json.addProperty("type", "bullet");
		json.addProperty("x", x);
		json.addProperty("y", y);
		return json;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public Move getMove() {
		return move;
	}

	@Override
	public void mObject() {
		move.execute();
		Direction direction = move.getDirection();
		this.x += direction.getX();
		this.y += direction.getY();
	}

	@Override
	public void run() {
		travelDistance--;
		if (travelDistance <= 0) {
			field.removeTileObject(this.getX(), this.getY());
			destroy();
		}
		if (!active) {
			return;
		}
		ExecutorManager.getInstance().schedule(this, Constants.FieldParams.baseTurnLength, TimeUnit.MILLISECONDS);
		field.moveFObject(this);
		sync();

	}

	@Override
	public void destroy() {
		active = false;
		sync();
	}

	@Override
	public String getID() {
		return bulletID;
	}

	@Override
	public Field getField() {
		return field;
	}

}
