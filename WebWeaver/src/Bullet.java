import java.util.Date;
import java.util.UUID;

import com.google.gson.JsonObject;

public class Bullet implements TileObject {
	private String bulletID;
	private String playerAdress;
	private int x, y;
	private Field field;
	private int travelDistance;
	private Move move;
	
	public Bullet(String playerAdress,Field field, int x, int y, Move move, int travelDistance) {
		super();
		this.field=field;
		this.bulletID=UUID.randomUUID().toString();
		this.playerAdress=playerAdress;
		this.x = x;
		this.y = y;
		this.travelDistance=travelDistance;
		if(move.getDirection()==Direction.CENTER){
			move=new Move(Direction.RIGHT);
		}
		this.move = move;
	} 
	
	@Override
	public JsonObject getFieldObject() {
		long time = new Date().getTime() - move.getMoveStart();
		JsonObject json = new JsonObject();
		json.addProperty("direction", move.getDirection().toString());
		json.addProperty("startTime", time);
		json.addProperty("executed", move.isExecuted());
		json.addProperty("moveTime", Constants.FieldParams.baseTurnLength);
		json.addProperty("type", "player");
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

}
