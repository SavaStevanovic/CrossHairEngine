import java.net.InetAddress;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Player implements TileObject {
	private int x, y;
	private Move move;
	private Field field;
	private MessageHandler messageHandler;

	public Player(MessageHandler messageHandler, Field field, int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.field = field;
		this.move = new Move(Direction.CENTER);
		this.messageHandler = messageHandler;
		field.addPlayer(this);
	}

	public void connect(MessageHandler messageHandeler) {
		this.messageHandler = messageHandeler;
		this.PlayerInfo();
	}

	public void mObject() {
		move.execute();
		this.x += move.getDirection().getX();
		this.y += move.getDirection().getY();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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

	public void jsonHandle(JsonObject json) {
		JsonProccessor.proccess(this, json);
	}

	public void PlayerInfo() {
		int initX = getX() - Constants.FieldParams.fieldViewHeight / 2;
		int initY = getY() - Constants.FieldParams.fieldViewWidth / 2;
		StringBuilder retTiles = new StringBuilder();
		JsonArray retObjects = new JsonArray();
		for (int i = initX; i < initX + Constants.FieldParams.fieldViewHeight; i++)
			for (int j = initY; j < initY + Constants.FieldParams.fieldViewWidth; j++) {
				Tile tile = field.getTile(i, j);
				retTiles.append(Long.toString(tile.getType())).append(",");
				TileObject fObject = tile.getFObject();
				if (fObject != null) {
					retObjects.add(fObject.getFieldObject());
				}
			}
		retTiles.deleteCharAt(retTiles.length() - 1);
		JsonObject playerJson = new JsonObject();
		playerJson.add("Player", getFieldObject());
		playerJson.addProperty("Name", "Player");
		playerJson.addProperty("Tiles", retTiles.toString());
		playerJson.add("AllPlayers", retObjects);
		if (messageHandler != null) {
			messageHandler.sendMessage(playerJson.toString());
		}
	}

	public void sync() {
		if (new Date().getTime() - this.move.getMoveStart() > Constants.FieldParams.baseTurnLength) {
			if (!this.move.isExecuted()) {
				field.moveFObject(this);
			}
		}
		PlayerInfo();
	}

	public void Move(Direction direction) {
		if (field.freeToMove(this, direction)
				&& new Date().getTime() - this.move.getMoveStart() > Constants.FieldParams.baseTurnLength) {
			if (!this.move.isExecuted()) {
				field.moveFObject(this);
			}
			field.reserveTile(this, direction);
			this.move = new Move(direction);
		}
		PlayerInfo();
	}

	public String getAddress() {
		if (messageHandler == null) {
			return null;
		}
		return messageHandler.socket.getInetAddress().toString();
	}

	@Override
	public Move getMove() {
		return this.move;
	}

	public void disconect() {
		this.messageHandler = null;

	}

	public void fire() {
		Direction bulletDirection = this.getMove().getDirection();
		ExecutorManager.getInstance().schedule(
				new Bullet(this.getAddress(), field, x + bulletDirection.getX(), y + bulletDirection.getY(), move, 5), 0, TimeUnit.MILLISECONDS);
	}

	@Override
	public void destroy() {
		field.removePlayer(this.getAddress());
	}

	@Override
	public String getID() {
		return messageHandler.socket.getInetAddress().toString();
	}
}
