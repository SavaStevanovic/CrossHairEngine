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
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Field {
	private Tile[][] field;
	private Random rand = new Random();
	private HashMap<String, TileObject> tileObjects;

	public Field() {
		super();
		this.field = new Tile[Constants.FieldParams.fieldHeight][Constants.FieldParams.fieldWidth];
		this.tileObjects = new HashMap<String, TileObject>();
		InitField();
	}

	private void InitField() {
		for (int i = 0; i < Constants.FieldParams.fieldHeight; i++)
			for (int j = 0; j < Constants.FieldParams.fieldWidth; j++) {
				field[i][j] = new Tile(Constants.TileType.grass + rand.nextInt(Constants.FieldParams.fieldTypes));
			}
	}

	public boolean containsPlayer(InetAddress inetAddress) {
		return tileObjects.containsKey(inetAddress);
	}

	public void removeTileObject(int x, int y) {
		field[x][y].setFObject(null);
	}

	public TileObject getTileObject(String tileObjectId) {
		return tileObjects.get(tileObjectId);
	}

	public void removePlayer(String string) {
		tileObjects.remove(string);
	}

	public Tile getTile(int x, int y) {
		return field[x][y];
	}

	public Set<String> getTileObjectsIds() {
		return tileObjects.keySet();
	}

	public void addFObject(TileObject tileObject) {
		tileObjects.put(tileObject.getID(), tileObject);
		field[tileObject.getX()][tileObject.getY()].setFObject(tileObject);
	}

	public void moveFObject(TileObject fObject) {
		field[fObject.getX()][fObject.getY()].free();
		fObject.mObject();
		TileObject obsticle = field[fObject.getX()][fObject.getY()].getFObject();
		if (obsticle != null) {
			fObject.destroy();
			obsticle.destroy();
			return;
		}
		field[fObject.getX()][fObject.getY()].setFObject(fObject);
	}

	public boolean freeToMove(Player player, Direction direction) {
		Tile tile = field[player.getX() + direction.getX()][player.getY() + direction.getY()];
		return (tile.isReserved().equals(player.getID())) || (tile.getFObject() == null && tile.isReserved().isEmpty());
	}

	public void reserveTile(TileObject tileObject, Direction direction) {
		Tile tile = field[tileObject.getX() + direction.getX()][tileObject.getY() + direction.getY()];
		tile.reserve(tileObject);
	}
}
