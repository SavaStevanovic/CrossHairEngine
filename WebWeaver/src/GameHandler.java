import java.awt.List;
import java.io.DataOutputStream;
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

	public void jsonHandle(DataOutputStream playerAdress, JsonObject json) {
		switch (json.get("action").getAsString()) {
		case "move":
			moveHandle(playerAdress, json);
			break;
		}
	}

	private void moveHandle(DataOutputStream playerAdress, JsonObject json) {
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

	public JsonObject PlayerInfo(DataOutputStream writer) {
		Player player = field.getPlayer(writer);
		int initX = player.getX() - Constants.FieldParams.fieldViewHeight / 2;
		int initY = player.getY() - Constants.FieldParams.fieldViewWidth / 2;
		StringBuilder retTiles = new StringBuilder();
		JsonArray retObjects = new JsonArray();
		for (int i = initX; i < initX + Constants.FieldParams.fieldViewHeight; i++)
			for (int j = initY; j < initY + Constants.FieldParams.fieldViewWidth; j++) {
				Tile tile=field.getTile(i, j);
				retTiles.append(Long.toString(tile.getType())).append(",");
				JsonObject jsonFieldObject=tile.getFieldObject();
				if(jsonFieldObject!=null){
					retObjects.add(jsonFieldObject);
				}
			}
		for (Player otherPlayers : field.getGamePlayers()) {
			
		}
		retTiles.deleteCharAt(retTiles.length() - 1);
		JsonObject playerJson = new JsonObject();
		playerJson.add("Player", player.getFieldObject());
		playerJson.addProperty("Name", "Player");
		playerJson.addProperty("Tiles", retTiles.toString());
		playerJson.add("AllPlayers", retObjects);
		return playerJson;
	}
}
