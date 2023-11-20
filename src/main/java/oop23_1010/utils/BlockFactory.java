package oop23_1010.utils;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

public class BlockFactory {

    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    private ArrayList<Rectangle> lista = new ArrayList<Rectangle>();

    private int gridSize;

    private int xBase;
    private int yBase;

    private Rectangle a;

    private Path square;

    public BlockFactory(int cellSize, int gridSize) {
        this.setXBaseYBase(cellSize, gridSize);
        this.gridSize = cellSize;
        // Rectangle square = new Rectangle();
        // square.setWidth(gridSize);
        // square.setHeight(gridSize);
        // square.setX(50);
        // square.setY(50);
        // square.setArcHeight(10);
        // square.setArcWidth(10);
        // square.setFill(Color.ORANGE);

        Path path = new Path();
        path.getElements().addAll(new MoveTo(10, 10),
                new LineTo(10, 30),
                new LineTo(30, 30),
                new LineTo(30, 10),
                new LineTo(10, 10),
                new MoveTo(40, 40),
                new LineTo(40, 60),
                new LineTo(60, 60),
                new LineTo(60, 40),
                new LineTo(40, 40),
                new ClosePath());

        // path.setFill(Color.AQUA);
        path.setStyle("-fx-fill: aqua; -fx-stroke-width: 3");
        // path.toFront();

        this.square = path;

        // .a = square;
        // lista.add(square);
    }

    public Path getPath() {
        return this.square;
    }

    public void setXBaseYBase(int cellSize, int gridSize) {
        int centerXOfPane = (WIDTH - gridSize) / 2;
        int centerYOfPane = (HEIGHT - gridSize) / 2;

        // System.out.println(centerXOfPane);
        // System.out.println(centerYOfPane);
    }
}
