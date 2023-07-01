package ru.ocupuc.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private int sizeX;
    private int sizeY;
    private List<Coordinate> walls;

    public Level(String filename) {
        walls = new ArrayList<>();
        loadLevelFromFile(filename);
    }

    private void loadLevelFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean readingWalls = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("// Size")) {
                    String[] size = line.split(";");
                    sizeX = Integer.parseInt(size[0].substring(1));
                    sizeY = Integer.parseInt(size[1]);
                } else if (line.startsWith("// Walls")) {
                    readingWalls = true;
                } else if (readingWalls && !line.isEmpty()) {
                    String[] coordinates = line.split(";");
                    int x = Integer.parseInt(coordinates[0]);
                    int y = Integer.parseInt(coordinates[1]);
                    walls.add(new Coordinate(x, y));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public List<Coordinate> getWalls() {
        return walls;
    }

    public boolean isWall(int x, int y) {
        return walls.contains(new Coordinate(x, y));
    }
}
