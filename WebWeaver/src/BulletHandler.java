
public class BulletHandler implements Runnable {
	private GameHandler gameHandler;
	private double bulletID;
	
	public BulletHandler(GameHandler gameHandler, double bulletId) {
		super();
		this.gameHandler = gameHandler;
		this.bulletID = bulletId;
	}

	public GameHandler getGameHandler() {
		return gameHandler;
	}

	public double getBulletId() {
		return bulletID;
	}
	
	public void remove(){
		gameHandler.destroyBullet(this);
		gameHandler=null;
	}

	@Override
	public void run() {
		gameHandler.bulletMove(bulletID);
	}
}
