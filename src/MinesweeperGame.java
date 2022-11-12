import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game{
        private static final int SIDE = 9;
        private CellProperties[][] cellField = new CellProperties[SIDE][SIDE];
        private int countMinesOnField;
        private int countFlags;
        private static final String MINE = "\uD83D\uDCA3";
        private static final String FLAG = "\uD83D\uDEA9";
        private boolean isGameStopped;
        private int countClosedTiles = SIDE*SIDE;
        private int score;

        @Override
        public void initialize() {
            setScreenSize(SIDE, SIDE);
            createGame();
        }

        @Override
        public void onMouseLeftClick(int x, int y) {
            if (isGameStopped){
                restart();
                return;
            }
            openTile(x, y);
        }
        @Override
        public void onMouseRightClick(int x, int y){
            markTile(x,y);
        }

        private void createGame() {
            for (int y = 0; y < SIDE; y++) {
                for (int x = 0; x < SIDE; x++) {
                    setCellValue(x,y,"");
                }
            }
            for (int y = 0; y < SIDE; y++) {
                for (int x = 0; x < SIDE; x++) {
                    boolean isMine = getRandomNumber(10) < 1;
                    if (isMine) {
                        countMinesOnField++;
                    }
                    cellField[y][x] = new CellProperties(x, y, isMine);
                    setCellColor(x, y, Color.ORANGE);
                }
            }
            countMineNeighbours();
            countFlags = countMinesOnField;
        }
        private void gameOver(){
            isGameStopped = true;
            showMessageDialog(Color.AZURE, "You lost!!!!!!",Color.BLACK, 30);
        }
        private void win(){
            isGameStopped = true;
            showMessageDialog(Color.AZURE,"You won!!",Color.BLACK,30);
        }
        private void restart(){
            setScore(score=0);
            countClosedTiles = SIDE*SIDE;
            isGameStopped = false;
            countFlags = 0;
            countMinesOnField = 0;
            createGame();
        }

        private List<CellProperties> getNeighbors(CellProperties cellProperties) {
            List<CellProperties> result = new ArrayList<>();
            for (int y = cellProperties.y - 1; y <= cellProperties.y + 1; y++) {
                for (int x = cellProperties.x - 1; x <= cellProperties.x + 1; x++) {
                    if (y < 0 || y >= SIDE) {
                        continue;
                    }
                    if (x < 0 || x >= SIDE) {
                        continue;
                    }
                    if (cellField[y][x] == cellProperties) {
                        continue;
                    }
                    result.add(cellField[y][x]);
                }
            }

            return result;
        }

        private void countMineNeighbours() {
            for (int x = 0; x < SIDE; x++) {
                for (int y = 0; y < SIDE; y++) {
                    CellProperties cellProperties = cellField[x][y];
                    //getNeighbors(cellProperties);
                    if (!cellProperties.isMine) {
                        cellProperties.countMineNeighbours = Math.toIntExact(getNeighbors(cellProperties).stream().filter(neighbor -> neighbor.isMine).count());
                    }
                }
            }
        }
        private void openTile(int x, int y) {
            CellProperties cellProperties = cellField[y][x];
            if (!isGameStopped && !cellProperties.isOpen && !cellProperties.isFlag) {
                cellProperties.isOpen = true;
                countClosedTiles--;

                setCellColor(x, y, Color.GREEN);
                if (cellProperties.isMine) {
                    setCellValueEx(cellProperties.x, cellProperties.y, Color.RED, MINE);
                    gameOver();
                } else if (cellProperties.countMineNeighbours == 0) {
                    score = score + 5;
                    setScore(score);
                    setCellValue(cellProperties.x, cellProperties.y, "");
                    List<CellProperties> neighbors = getNeighbors(cellProperties);
                    for (CellProperties neighbor : neighbors) {
                        if (!neighbor.isOpen) {
                            openTile(neighbor.x, neighbor.y);
                        }
                    }
                } else {
                    score = score + 5;
                    setScore(score);
                    setCellNumber(x, y, cellProperties.countMineNeighbours);
                }
            } if (countClosedTiles == countMinesOnField && !cellProperties.isMine){
                win();
            }

        }
        private void markTile(int x, int y){
        CellProperties cellProperties = cellField[y][x];
        if (!cellProperties.isOpen && !isGameStopped){
            if (!cellProperties.isFlag && countFlags != 0){
                cellProperties.isFlag = true;
                setCellColor(x,y,Color.YELLOW);
                setCellValue(x,y,FLAG);
                countFlags--;

            }else if (cellProperties.isFlag) {
                cellProperties.isFlag = false;
                setCellColor(x,y,Color.ORANGE);
                setCellValue(x,y,"");
                countFlags++;
            }
        }
    }
    }