import com.google.gson.JsonObject;

public interface TileObject {
	public JsonObject getFieldObject();
	
	public int getX();
	
	public int getY();
	
	public Move getMove();
	
	public void mObject();

	public void sync();
	
	public void destroy();
}
