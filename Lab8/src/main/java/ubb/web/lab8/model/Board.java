package ubb.web.lab8.model;

public class Board extends BaseEntity{
    private Integer[] tiles;
    private Long userId;
    private Integer numberOfMoves;
    private static final int BoardSize = 9;

    public Board() {}

    public Board(Long userId) {
        this.userId = userId;
        this.tiles = new Integer[BoardSize];
        this.numberOfMoves = 0;
    }

    public Board(Integer[] tiles, Long userId, Integer numberOfMoves) {
        this.tiles = tiles.clone();
        this.userId = userId;
        this.numberOfMoves = numberOfMoves;
    }

    public Integer getNumberOfMoves() {
        return numberOfMoves;
    }

    public void setNumberOfMoves(Integer numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public Integer[] getTiles() {
        return tiles;
    }

    public void setTiles(Integer[] tiles) {
        this.tiles = tiles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean compareUser(User other) {
        if (this.userId == other.getId()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String str = "Board{userId=" + userId +
                "numberOfMoves=" + numberOfMoves +
                "tiles=";
        for(int i = 0; i < BoardSize-1; i++) {
            str += i + "->" + tiles[i] + ", ";
        }
        str += (BoardSize-1) + "->" + tiles[BoardSize-1] + "}";
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Board))
            return false;
        Board other = (Board) obj;
        for(int i = 0; i < BoardSize-1; i++) {
            if (this.tiles[i] != other.tiles[i]) {
                return false;
            }
        }
        return true;
    }
}
