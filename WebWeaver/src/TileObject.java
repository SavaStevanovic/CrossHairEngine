import com.google.gson.JsonObject;

public interface TileObject {
	public JsonObject getFieldObject();

	public String getID();

	public int getX();

	public int getY();

	public Field getField();

	public Move getMove();

	public void mObject();

	public default void sync() {
		int xS = getX();
		int yS = getY();
		Move move = getMove();
		Direction direction = move.getDirection();
		Field field = getField();
		int heighOffset = direction.getX();
		int widthOffset = direction.getY();
		int height = Constants.FieldParams.fieldViewHeight;
		int width = Constants.FieldParams.fieldViewWidth;
		int initX = xS - height / 2 + (heighOffset == -1 ? -1 : 0);
		int initY = yS - width / 2 + (widthOffset == -1 ? -1 : 0);
		for (int i = initX; i < initX + height + (Math.abs(heighOffset) == 1 ? 1 : 0); i++)
			for (int j = initY; j < initY + width + (Math.abs(widthOffset) == 1 ? 1 : 0); j++) {
				Tile tile = field.getTile(i, j);
				TileObject fObject = tile.getFObject();
				if (fObject != null) {
					fObject.Info();
				}
			}
	}

	public default void Info() {
	};

	public void destroy();
}
