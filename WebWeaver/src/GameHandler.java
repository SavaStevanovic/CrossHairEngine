import java.awt.List;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GameHandler {
	private Field field;
	private HashSet<MessageHandler> massageHandlers;
	private Random randomGenerator;

	public GameHandler() {
		super();
		this.field = new Field();
		massageHandlers = new HashSet<MessageHandler>();
		randomGenerator = new Random();
	}

	public Player getPlayer(MessageHandler messageHandler) {
		InetAddress playerAddress = messageHandler.socket.getInetAddress();
		if (!field.containsPlayer(playerAddress)) {
			int pHeight = randomGenerator
					.nextInt(Constants.FieldParams.fieldHeight - Constants.FieldParams.fieldViewHeight)
					+ Constants.FieldParams.fieldViewHeight / 2;
			int pWidth = randomGenerator
					.nextInt(Constants.FieldParams.fieldWidth - Constants.FieldParams.fieldViewWidth)
					+ Constants.FieldParams.fieldViewWidth / 2;
			field.addFObject(new Player(messageHandler, field, pHeight, pWidth));
		}
		Player player = (Player) field.getTileObject(playerAddress.toString());
		player.connect(messageHandler);
		return player;
	}
}
