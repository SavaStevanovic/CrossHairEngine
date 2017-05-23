import java.net.InetAddress;
import java.util.Date;
import java.util.Random;

import com.google.gson.JsonObject;

public class Bullet implements TileObject{
	private double bulletID;
	private InetAddress playerAdress;
	private int x, y;
	private int travelDistance;
	private Move move;

	public Bullet(InetAddress playerAdress, int x, int y, Move move, int travelDistance) {
		super();
		this.bulletID=Math.random();
		this.playerAdress=playerAdress;
		this.x = x;
		this.y = y;
		this.travelDistance=travelDistance;
		if(move.getDirection()==Direction.CENTER){
			move=new Move(Direction.RIGHT);
		}
		this.move = move;
	}

	public void moveBullet() {
		move.execute();
		Direction direction = move.getDirection();
		this.x += direction.getX();
		this.y += direction.getY();
		travelDistance-=1;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getTravelDistance() {
		return travelDistance;
	}

	@Override
	public JsonObject getFieldObject() {
		long time = new Date().getTime() - move.getMoveStart();
		JsonObject json = new JsonObject();
		json.addProperty("direction", move.getDirection().toString());
		json.addProperty("startTime", time);
		json.addProperty("moveTime", Constants.FieldParams.baseTurnLength);
		json.addProperty("executed", move.isExecuted());
		json.addProperty("type", "player");
		json.addProperty("x", x);
		json.addProperty("y", y);
		return json;
	}

	public double getBulletID() {
		return bulletID;
	}
}
