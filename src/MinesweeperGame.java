import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

public class MinesweeperGame extends Game{
private final int DIMENSION = 12;
public CellProperties[][] cellObject = new CellProperties[DIMENSION][DIMENSION];
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";

    @Override
    public void initialize() {
        setScreenSize(DIMENSION, DIMENSION);
        createGame();
    }
    private void createGame(){
        for (int x = 0; x < DIMENSION; x++){
            for (int y = 0; y < DIMENSION; y++){
                boolean isMine = getRandomNumber(100)<10;
                cellObject[x][y] = new CellProperties(x,y,isMine);
                setCellColor(x,y, Color.BLANCHEDALMOND);
            }
        }
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        openCell(x,y);
    }
    @Override
    public void onMouseRightClick(int x, int y) {
        //
    }
    private void openCell(int x, int y){
        CellProperties cellProperties = cellObject[x][y];
        if (cellProperties.isMine){
            setCellValue(cellProperties.x,cellProperties.y,MINE);
        } else {
            setCellColor(cellProperties.x,cellProperties.y,Color.CYAN);
        }
    }
}
