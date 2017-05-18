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
				field[i][j] = new Tile(this, Constants.TileType.grass + rand.nextInt(Constants.FieldParams.fieldTypes));
			}
	}

	public Player getPlayer(InetAddress playerAdress) {
		if (players.containsKey(playerAdress)) {
			return players.get(playerAdress);
		}
		Player player = new Player(rand.nextInt(Constants.FieldParams.fieldHeight),
				rand.nextInt(Constants.FieldParams.fieldWidth));
		players.put(playerAdress, player);
		field[player.getX()][player.getY()].setObject(player);
		return player;
	}

	public void removePlayer(InetAddress playerAdress){
		players.remove(playerAdress);
	}
	
	public void Move(InetAddress playerAdress, int i, int j) {
		Player player = getPlayer(playerAdress);
		field[player.getX()][player.getY()].setObject(null);
		player.move(i, j);
		field[player.getX()][player.getY()].setObject(player);
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
}
