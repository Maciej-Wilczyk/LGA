package funcitons;

import cell.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Function1 {
    int wi = 600, hi = 600;
    List<Cell> list;

    public Function1() {
    }

    public void prepare(Image image) {
        prepareFirstList(image);
    }

    public Image setRandomGasCell(Image image) { //ustawiamy na obrazku poczatkowe komorki
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();
        PixelReader pixelReader = image.getPixelReader();
        for (int hi = 0; hi < image.getHeight(); hi++) {
            for (int wi = 0; wi < image.getWidth(); wi++) {
                if (pixelReader.getColor(wi, hi).equals(Color.BLACK))
                    pixelWriter.setColor(wi, hi, Color.BLACK);
                else
                    pixelWriter.setColor(wi, hi, Color.WHITE);
            }
        }
        int randomNum;
        for (int hi = 5; hi < this.wi-5; hi++) {
            for (int wi = 5; wi < 195; wi++) {
                randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
                if (randomNum == 0) {
                    pixelWriter.setColor(wi, hi, Color.RED);
                }
            }
        }
        return wImage;
    }

    public void prepareFirstList(Image image) {//tworzymy pierwsza liste
        List<Cell> list = new ArrayList<>();
        int randomNum; Cell cell; PixelReader pixelReader = image.getPixelReader();
        for (int hi = 1; hi < image.getHeight() - 1; hi++) {
            for (int wi = 1; wi < image.getWidth() - 1; wi++) {
                Color color = pixelReader.getColor(wi, hi);
                if (color.equals(Color.RED)) {
                    cell = new Cell();
                    cell.setWi(wi);
                    cell.setHi(hi);
                    cell.setColor(color);
                    randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);
                    switch (randomNum) {
                        case 0:
                            cell.setDirection("left");
                            break;
                        case 1:
                            cell.setDirection("right");
                            break;
                        case 2:
                            cell.setDirection("up");
                            break;
                        case 3:
                            cell.setDirection("down");
                            break;
                    }
                    list.add(cell);
                }
            }
        }
        this.list = list;
    }


    public void move(Image image) {
        PixelReader pixelReader = image.getPixelReader();
        for (Cell i : this.list) {
            if (i.getDirection().equals("left")) {
                i.setWi(i.getWi() - 1);

            } else if (i.getDirection().equals("right")) {
                i.setWi(i.getWi() + 1);
            } else if (i.getDirection().equals("up")) {
                i.setHi(i.getHi() - 1);

            } else if (i.getDirection().equals("down")) {
                i.setHi(i.getHi() + 1);

            }
            if (pixelReader.getColor(i.getWi(), i.getHi()).equals(Color.BLACK)) {
                if (i.getDirection().equals("left")) {
                    i.setDirection("right");
                    i.setWi(i.getWi() + 1);
                } else if (i.getDirection().equals("right")) {
                    i.setDirection("left");
                    i.setWi(i.getWi() - 1);
                } else if (i.getDirection().equals("up")) {
                    i.setDirection("down");
                    i.setHi(i.getHi() + 1);
                } else if (i.getDirection().equals("down")) {
                    i.setDirection("up");
                    i.setHi(i.getHi() - 1);
                }
            }
        }
    }

    public void makeMatrix() {
        String[][] matrix = new String[this.wi][this.hi];
        for (int hi1 = 0; hi1 < this.hi; hi1++) {
            for (int wi1 = 0; wi1 < this.wi; wi1++) {
                matrix[wi1][hi1] = "";
            }
        }
        for (Cell i : list) {
            matrix[i.getWi()][i.getHi()] += i.getDirection();
        }
        for (Cell i : list) {
            int x = i.getWi();
            int y = i.getHi();
            String dirOps;
            int xOps, yOps;
            if (i.getDirection().equals("up")) {
                dirOps = "down";
                xOps = x;
                yOps = y - 1;
            } else if (i.getDirection().equals("right")) {
                dirOps = "left";
                xOps = x + 1;
                yOps = y;
            } else if (i.getDirection().equals("down")) {
                dirOps = "up";
                xOps = x;
                yOps = y + 1;
            } else {

                dirOps = "right";
                xOps = x - 1;
                yOps = y;
            }
            if (matrix[x][y].contains(dirOps) || (xOps >= 0 && xOps <= 599 && yOps >= 0 && yOps <= 599 && matrix[xOps][yOps].contains(dirOps))) {
                //  i.dir += 90;
                if (i.getDirection().equals("up")) {
                    i.setDirection("right");
                }
                else if (i.getDirection().equals("right")) {
                    i.setDirection("down");
                }
                else if (i.getDirection().equals("down")) {
                    i.setDirection("left");
                }
                else if (i.getDirection().equals("left")) {
                    i.setDirection("up");
                }
            }
        }
    }

    public Image makeAll(Image image) {
        move(image);
        makeMatrix();
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        PixelReader pixelReader = image.getPixelReader();
        for (int hi = 0; hi < image.getHeight(); hi++) {
            for (int wi = 0; wi < image.getWidth(); wi++) {
                if (pixelReader.getColor(wi, hi).equals(Color.BLACK))
                    pixelWriter.setColor(wi, hi, Color.BLACK);
                else
                    pixelWriter.setColor(wi, hi, Color.WHITE);
            }
        }
        for (Cell i : list) {
            pixelWriter.setColor(i.getWi(), i.getHi(), i.getColor());
        }
        return wImage;
    }
}
