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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Field {
	private Tile[][] field;
	private Random rand = new Random();
	private HashMap<Double, Bullet> bullets;
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
		return player;
	}

	public void removePlayer(InetAddress playerAdress) {
		players.remove(playerAdress);
	}

	public void movePlayer(InetAddress playerAdress, Direction direction) {
		Player player = getPlayer(playerAdress);
		player.initiateMovePlayer(new Move(direction));
	}
	
	public void moveBullet(double bulletID) {
		bullets.get(bulletID).moveBullet();
	}

	public Tile getTile(int x, int y) {
		return field[x][y];
	}

	public void mapPlayers() {
		for (Player player : players.values()) {
			player.sync();
			field[player.getX()][player.getY()].setObject(player);
		}
	}

	public void relesePlayers() {
		for (Player player : players.values()) {
			field[player.getX()][player.getY()].setObject(null);
		}
	}

	public Vector<Player> getGamePlayers() {
		return new Vector<Player>(players.values());
	}

	public Set<InetAddress> getGamePlayersSockets() {
		return players.keySet();
	}

	public double fire(InetAddress playerAdress) {
		Player player = getPlayer(playerAdress);
		Bullet bullet = new Bullet(playerAdress, player.getX(), player.getY(), player.getMove(),
				Constants.FieldParams.baseBulletTravel);
		bullets.put(bullet.getBulletID(), bullet);
		return bullet.getBulletID();
	}
	
	public void removeBullet(double bulletID) {
		bullets.remove(bulletID);
	}
}
