import java.awt.List;
import java.io.DataOutputStream;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

public class Field {
	private Tile[][] field;
	private Random rand = new Random();
	private HashMap<InetAddress, Player> players;

	public Field() {
		super();
		this.field = new Tile[Constants.FieldParams.fieldHeight][Constants.FieldParams.fieldWidth];
		this.players = new HashMap<InetAddress, Player>();
		InitField();
	}

	private void InitField() {
		for (int i = 0; i < Constants.FieldParams.fieldHeight; i++)
			for (int j = 0; j < Constants.FieldParams.fieldWidth; j++) {
				field[i][j] = new Tile(Constants.TileType.grass + rand.nextInt(Constants.FieldParams.fieldTypes));
			}
	}

	public boolean containsPlayer(InetAddress inetAddress) {
		return players.containsKey(inetAddress);
	}

	public Player getPlayer(InetAddress playerAdress) {
		return players.get(playerAdress);
	}

	public void removePlayer(InetAddress playerAdress) {
		players.remove(playerAdress);
	}

	public Tile getTile(int x, int y) {
		return field[x][y];
	}

	public Vector<Player> getGamePlayers() {
		return new Vector<Player>(players.values());
	}

	public Set<InetAddress> getGamePlayersSockets() {
		return players.keySet();
	}

	public void addPlayer(Player player) {
		players.put(player.getAddress(), player);
		field[player.getX()][player.getY()].setFObject(player);
	}

	public void moveFObject(TileObject fObject) {
		field[fObject.getX()][fObject.getY()].free();
		fObject.mObject();
		field[fObject.getX()][fObject.getY()].setFObject(fObject);
	}
}
