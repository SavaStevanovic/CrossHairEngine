import com.google.gson.JsonObject;

public class Tile {
	private long type;
	private TileObject fObject;
	private boolean reserve;

	public Tile(int type) {
		this.type = type;
		reserve = false;
	}

	public void reserve() {
		reserve = true;
	}

	public void free() {
		fObject = null;
		reserve = false;
	}

	public void setFObject(TileObject fObject) {
		this.fObject = fObject;
	}

	public long getType() {
		return type;
	}

	public TileObject getFObject() {
		return fObject;
	}

	public boolean isReserved() {
		return reserve;
	}
}
