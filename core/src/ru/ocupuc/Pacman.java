package ru.ocupuc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Pacman {
    private int x;
    private int y;
    private int speed = 1;
    private Socket serverSocket;
    private PrintWriter out;

    public Pacman() throws IOException {
        this.serverSocket = new Socket("localhost", 8000);
        this.out = new PrintWriter(serverSocket.getOutputStream(), true);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void connectToServer(String host, int port) throws IOException {
        serverSocket = new Socket(host, port);
        out = new PrintWriter(serverSocket.getOutputStream(), true);
    }

    public void disconnectFromServer() throws IOException {
        if (out != null) {
            out.close();
        }
        if (serverSocket != null) {
            serverSocket.close();
        }
    }

    // Метод для отправки произвольного сообщения на сервер
    public void sendMessage(String message) {
        System.out.println(message);
    }

    // Метод для перемещения Pacman в указанном направлении на заданное количество шагов
    public void move(Direction direction, int steps) {
        int dx = 0;
        int dy = 0;

        switch (direction) {
            case UP:
                dy = speed;
                break;
            case DOWN:
                dy = -speed;
                break;
            case LEFT:
                dx = -speed;
                break;
            case RIGHT:
                dx = speed;
                break;
        }

        this.x += dx * steps;
        this.y += dy * steps;

        String moveMessage = "Pacman переместился на (" + dx * steps + ", " + dy * steps + ")";
        out.println(moveMessage);
    }

    // Перечисление для представления направлений движения

}
