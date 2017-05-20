import java.awt.List;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GameHandler {
	private Field field;
	private HashSet<MessageHandler> massageHandlers;

	public GameHandler() {
		super();
		this.field = new Field();
		massageHandlers = new HashSet<MessageHandler>();
	}

	public void jsonHandle(MessageHandler messageHandler, JsonObject json) {
		switch (json.get("action").getAsString()) {
		case "move":
			moveHandle(messageHandler, json);
			break;
		}
		field.mapPlayers();
		for (MessageHandler messageSender : massageHandlers) {
			String message = PlayerInfo(messageSender).toString();
			System.out.println(message);
			messageSender.sendMessage(message);
		}
		//field.relesePlayers();
	}

	private void moveHandle(MessageHandler messageHandler, JsonObject json) {
		InetAddress playerAdress = messageHandler.socket.getInetAddress();
		switch (json.get("direction").getAsString()) {
		case "down":
			field.Move(playerAdress, Direction.DOWN);
			break;
		case "up":
			field.Move(playerAdress, Direction.UP);
			break;
		case "left":
			field.Move(playerAdress, Direction.LEFT);
			break;
		case "right":
			field.Move(playerAdress, Direction.RIGHT);
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
}
