import java.util.HashMap;

public final class Tile_factory {
	private static HashMap<Integer, Tile> tiles;

	public Tile_factory() {
		tiles = new HashMap<Integer, Tile>();
		tiles.put(Constants.TileType.grass, new Tile(Constants.TileType.grass));
		tiles.put(Constants.TileType.stone, new Tile(Constants.TileType.stone));
		tiles.put(Constants.TileType.corner, new Tile(Constants.TileType.corner, 3 + 96));
	}

	public Tile getTile(int type, Direction direction) {
		Tile tile = tiles.get(type);
		for (int i = 0; i < direction.getDirectionBit() / 2; i++) {
			tile.rotateLeft();
		}
		return tile;
	}

	public Tile getTile(int type) {
		return tiles.get(type);
	}

}
