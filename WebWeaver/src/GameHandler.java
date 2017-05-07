import java.awt.List;
import java.lang.reflect.Array;
import java.net.InetAddress;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GameHandler {
	Field field;

	public GameHandler() {
		super();
		this.field = new Field();
	}

	public void jsonHandle(InetAddress playerAdress, JsonObject json) {
		switch (json.get("action").getAsString()) {
		case "move":
			moveHandle(playerAdress, json);
			break;
		}
	}

	private void moveHandle(InetAddress playerAdress, JsonObject json) {
		switch (json.get("direction").getAsString()) {
		case "down":
			field.Move(playerAdress, 1, 0);
			break;
		case "up":
			field.Move(playerAdress, -1, 0);
			break;
		case "left":
			field.Move(playerAdress, 0, -1);
			break;
		case "right":
			field.Move(playerAdress, 0, 1);
			break;
		}
	}

	public JsonObject PlayerInfo(InetAddress playerAdress) {
		Player player = field.getPlayer(playerAdress);
		int initX = player.getX() - Constants.FieldParams.fieldViewHeight / 2;
		int initY = player.getY() - Constants.FieldParams.fieldViewWidth / 2;
		StringBuilder retTiles = new StringBuilder();
		for (int i = initX; i < initX + Constants.FieldParams.fieldViewHeight; i++)
			for (int j = initY; j < initY + Constants.FieldParams.fieldViewWidth; j++) {
				retTiles.append(Long.toString(field.getTile(i, j))).append(",");
			}
		retTiles.deleteCharAt(retTiles.length() - 1);
		JsonObject playerJson = new JsonObject();
		playerJson.addProperty("Name", "Player");
		playerJson.addProperty("Tiles", retTiles.toString());
		return playerJson;
	}
}
