import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

public class MinesweeperGame extends Game{
private final int DIMENSION = 12;
public CellProperties[][] cellProperties = new CellProperties[DIMENSION][DIMENSION];
    @Override
    public void initialize() {
        setScreenSize(DIMENSION, DIMENSION);
        createGame();
    }
    private void createGame(){
        for (int x = 0; x < DIMENSION; x++){
            for (int y = 0; y < DIMENSION; y++){
                boolean isMine = getRandomNumber(100)<10;
                cellProperties[x][y] = new CellProperties(x,y,isMine);
                setCellColor(x,y, Color.BLANCHEDALMOND);
            }
        }
    }






}
