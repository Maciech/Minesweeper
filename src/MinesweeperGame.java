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
    public void createGame(){
        for (int i = 0; i < DIMENSION; i++){
            for (int j = 0; j < DIMENSION; j++){
                setCellColor(i,j, Color.AQUA);
            }
        }

    }






}
