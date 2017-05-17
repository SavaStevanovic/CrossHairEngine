import com.google.gson.JsonObject;

public class Tile {
	private long type;
	private TileObject fObject;
	private Field field;

	public Tile(Field field, int type) {
		this.type = type;
		this.field = field;
	}

	public void setObject(TileObject fObject) {
		this.fObject = fObject;
	}

	public long getType() {
		return type;
	}

	public JsonObject getFieldObject() {
		if (fObject != null) {
			return fObject.getFieldObject();
		}
		return null;
	}
}
