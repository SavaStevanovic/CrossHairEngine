import com.google.gson.JsonObject;

public class Tile {
	private long type;
	private TileObject fObject;
	private String reserve;
	private byte restrictedDirections;
	private byte direction;

	public Tile(int type) {
		direction = 3;
		restrictedDirections = 0;
		this.type = type;
		reserve = "";
	}

	public Tile(int type, int restrictedDirections) {
		direction = 3;
		restrictedDirections = restrictedDirections;
		this.type = type;
		reserve = "";
	}

	public void rotateLeft() {
		direction >>>= 2;
		restrictedDirections >>>= 2;
	}

	public boolean canMoveFrom(Direction direction) {
		return restrictedDirections >> direction.getDirectionBit() == 0;
	}

	public boolean canMoveTo(Direction direction) {
		return restrictedDirections >> (direction.getDirectionBit() + 4) == 0;
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
