import java.net.InetAddress;
import java.util.HashSet;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GameHandler {
	private Field field;
	private HashSet<MessageHandler> massageHandlers;
	private HashSet<BulletHandler> bulletHandlers;
	public static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

	public GameHandler() {
		super();
		this.field = new Field();
		massageHandlers = new HashSet<MessageHandler>();
		bulletHandlers = new HashSet<BulletHandler>();
	}

	public void jsonHandle(MessageHandler messageHandler, JsonObject json) {
		switch (json.get("action").getAsString()) {
		case "move":
			moveHandle(messageHandler, json);
			break;
		case "fire":
			fireHandle(messageHandler, json);
			break;
		}
		field.mapPlayers();
		for (MessageHandler messageSender : massageHandlers) {
			String message = PlayerInfo(messageSender).toString();
			System.out.println(message);
			messageSender.sendMessage(message);
		}
		field.relesePlayers();
	}

	private void fireHandle(MessageHandler messageHandler, JsonObject json) {
		InetAddress playerAdress = messageHandler.socket.getInetAddress();
		double bulletID=field.fire(playerAdress);
		BulletHandler bulletHandler=new BulletHandler(this, bulletID);
		bulletHandlers.add(bulletHandler);
		executor.schedule(bulletHandler, 1000, TimeUnit.MILLISECONDS);
	}

	private void moveHandle(MessageHandler messageHandler, JsonObject json) {
		InetAddress playerAdress = messageHandler.socket.getInetAddress();
		switch (json.get("direction").getAsString()) {
		case "down":
			field.movePlayer(playerAdress, Direction.DOWN);
			break;
		case "up":
			field.movePlayer(playerAdress, Direction.UP);
			break;
		case "left":
			field.movePlayer(playerAdress, Direction.LEFT);
			break;
		case "right":
			field.movePlayer(playerAdress, Direction.RIGHT);
			break;
		}
	}

	public JsonObject PlayerInfo(MessageHandler messageHandler) {
		Player player = field.getPlayer(messageHandler.socket.getInetAddress());
		int initX = player.getX() - Constants.FieldParams.fieldViewHeight / 2;
		int initY = player.getY() - Constants.FieldParams.fieldViewWidth / 2;
		StringBuilder retTiles = new StringBuilder();
		JsonArray retObjects = new JsonArray();
		for (int i = initX; i < initX + Constants.FieldParams.fieldViewHeight; i++)
			for (int j = initY; j < initY + Constants.FieldParams.fieldViewWidth; j++) {
				Tile tile = field.getTile(i, j);
				retTiles.append(Long.toString(tile.getType())).append(",");
				JsonObject jsonFieldObject = tile.getFieldObject();
				if (jsonFieldObject != null) {
					retObjects.add(jsonFieldObject);
				}
			}
		retTiles.deleteCharAt(retTiles.length() - 1);
		JsonObject playerJson = new JsonObject();
		playerJson.add("Player", player.getFieldObject());
		playerJson.addProperty("Name", "Player");
		playerJson.addProperty("Tiles", retTiles.toString());
		playerJson.add("AllPlayers", retObjects);
		return playerJson;
	}

	public void enterGame(MessageHandler messageHandler) {
		massageHandlers.add(messageHandler);
		field.getPlayer(messageHandler.socket.getInetAddress());

	}

	public void leaveGame(MessageHandler messageHandler) {
		massageHandlers.remove(messageHandler);
	}

	public void destroyBullet(BulletHandler bulletHandler) {
		bulletHandlers.remove(bulletHandler);
		field.removeBullet(bulletHandler.getBulletId());
	}
	
	public void bulletMove(double bulletID) {
		field.moveBullet(bulletID);
	}
}
