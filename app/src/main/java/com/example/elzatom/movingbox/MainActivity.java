package com.example.elzatom.movingbox;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View box;
    boolean isInMotion;
    int screenWidth;
    int screenHeight;
    int deltaX = 10;
    int deltaY = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        box = this.findViewById(R.id.box);
        Display display = getWindowManager().getDefaultDisplay();
        Point screenSize = new Point();
        display.getSize(screenSize);
        screenHeight = screenSize.y;
        screenWidth = screenSize.x;
    }

    public void start(View button){
        isInMotion = true;
        move();
    }
    public void stop(View button){
        isInMotion = false;
    }

    private void changeOnCollison() {
        if (xIsOutOfBounds(box)){
            deltaX = deltaX * -1;
        }
        if (yIsOutOfBounds(box)) {
            deltaY = deltaY * -1;
        }
    }
    private boolean xIsOutOfBounds(View widget) {
        float x = widget.getX();
        if (x <0) {
            return true;
        }
        if (x + widget.getWidth() > screenWidth) {
            return true;
        }
        return false;
    }
    private boolean yIsOutOfBounds(View widget) {
        float y = widget.getY();
        if (y <0) {
            return true;
        }
        if (y + widget.getHeight() + 150> screenHeight) {
            return true;
        }
        return false;
    }

    private void move() {
        box.setX(box.getX() + deltaX);
        box.setY(box.getY() + deltaY);
        changeOnCollison();
        if (isInMotion) {
            box.postDelayed(new Mover(), 50);
        }
    }
    class Mover implements Runnable {
        @Override public void run() {
            move();
        }
    }
}
