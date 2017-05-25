import java.net.InetAddress;

import com.google.gson.JsonObject;

public class JsonProccessor {

	public static void proccess(Player player, JsonObject jsonObject){
		switch (jsonObject.get("action").getAsString()) {
		case "move":
			moveHandle(player, jsonObject);
			break;
		}
	}

	private static void moveHandle(Player player, JsonObject jsonObject) {
		switch (jsonObject.get("direction").getAsString()) {
		case "down":
			player.Move( Direction.DOWN);
			break;
		case "up":
			player.Move( Direction.UP);
			break;
		case "left":
			player.Move( Direction.LEFT);
			break;
		case "right":
			player.Move( Direction.RIGHT);
			break;
		}
	}
	
}
