import java.net.InetAddress;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Player implements TileObject {
	private int x, y;
	private Move move;
	private Field field;
	private MessageHandler messageHandler;

	public Player(MessageHandler messageHandeler, Field field, int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.field = field;
		this.messageHandler = messageHandeler;
		this.move = new Move(Direction.CENTER);
	}

	private void movePlayer() {
		field.moveFObject(this);
	}

	public void mObject() {
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

	public void sync() {
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

	public void jsonHandle(JsonObject json) {
		switch (json.get("action").getAsString()) {
		case "move":
			JsonProccessor.proccess(this, json);
			break;
		}
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
		messageHandler.sendMessage(playerJson.toString());
	}

	public void Move(Direction direction) {
		if (new Date().getTime() - this.move.getMoveStart() > Constants.FieldParams.baseTurnLength) {
			if (!this.move.isExecuted()) {
				movePlayer();
			}
			this.move = new Move(direction);
			PlayerInfo();
		}
	}

	public InetAddress getAddress() {
		return messageHandler.socket.getInetAddress();
	}

	@Override
	public Move getMove() {
		return this.move;
	}
}
