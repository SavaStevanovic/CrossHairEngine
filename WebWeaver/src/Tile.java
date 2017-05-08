import com.google.gson.JsonObject;

public class Tile {
	private long type;
	private FieldObject fObject;

	public Tile(int type) {
		this.type = type;
	}

	public void setObject(FieldObject fObject) {
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
