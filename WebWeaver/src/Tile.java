import com.google.gson.JsonObject;

public class Tile {
	private long type;
	private TileObject fObject;
	private String reserve;

	public Tile(int type) {
		this.type = type;
		reserve = "";
	}

	public void reserve(TileObject tileObject) {
		reserve = tileObject.getID();
	}

	public void free() {
		fObject = null;
		reserve = "";
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

	public String isReserved() {
		return reserve;
	}
}
