package cell;

import javafx.scene.paint.Color;

public class Cell {
    int wi,hi;
    Color color;
    String direction;

    public Cell(){
        direction = "";
        wi = -100;
        hi = -100;
    }

    public int getWi() {
        return wi;
    }

    public void setWi(int wi) {
        this.wi = wi;
    }

    public int getHi() {
        return hi;
    }

    public void setHi(int hi) {
        this.hi = hi;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

//    public boolean isL() {
//        return l;
//    }
//
//    public void setL(boolean l) {
//        this.l = l;
//    }
//
//    public boolean isR() {
//        return r;
//    }
//
//    public void setR(boolean r) {
//        this.r = r;
//    }
//
//    public boolean isU() {
//        return u;
//    }
//
//    public void setU(boolean u) {
//        this.u = u;
//    }
//
//    public boolean isD() {
//        return d;
//    }
//
//    public void setD(boolean d) {
//        this.d = d;
//    }
}
