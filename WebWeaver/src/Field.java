import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Vector;



public class Field {
	private int[][] field;
	private Random rand = new Random();
	private HashMap<InetAddress,Player> players;

	public Field() {
		super();
		this.field = new int[Constants.FieldParams.fieldHeight][Constants.FieldParams.fieldWidth];
		this.players = new HashMap<InetAddress,Player>();
		
		for (int i = 0; i < Constants.FieldParams.fieldHeight; i++) 
			for (int j = 0; j < Constants.FieldParams.fieldWidth; j++) {
				field[i][j]=Constants.TileType.grass+rand.nextInt(Constants.FieldParams.fieldTypes);
			}
		} 
		
	public Player getPlayer(InetAddress address) {
		if(players.containsKey(address)){
			return players.get(address);
		}
		Player player=new Player(rand.nextInt(Constants.FieldParams.fieldHeight), rand.nextInt(Constants.FieldParams.fieldWidth));
		players.put(address, player);
		return player;
	}

	public void Move(InetAddress playerAdress, int i, int j) {
		Player player= getPlayer(playerAdress);
		player.move(i, j);
	}
	
	public long getTile(int x,int y){
		return field[x][y];
	}
}
