import com.codegym.engine.cell.Game;

public class CellProperties extends Game {
    public int x,y;
    public boolean isMine;
    CellProperties(int x, int y, boolean isMine){
        this.x = x;
        this.y = y;
        this.isMine = isMine;
    }
}
