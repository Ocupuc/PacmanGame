package ru.ocupuc.server;

import java.io.*;
import java.net.*;

public class PacmanServer {
    private Level level;

    public PacmanServer() {
        level = new Level("D:\\libGDX\\PacmanGame\\PacmanServer\\src\\main\\resources\\pacman_field.txt");
    }

    public static void main(String[] args) throws IOException {
        PacmanServer server = new PacmanServer();
        server.start();
    }

    private void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Сервер запущен и ожидает подключений...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Клиент подключился");

            // Для каждого подключения создаем новый поток
            new Thread(() -> {
                try {
                    // Получение входного и выходного потока сокета
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    // Обработка сообщений от клиента
                    String messageFromClient;
                    while ((messageFromClient = in.readLine()) != null) {
                        System.out.println("Сообщение от клиента: " + messageFromClient);

                        // Обработка сообщения и отправка ответа клиенту
                        String response = "Сообщение получено: " + messageFromClient;
                        out.println(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();  // Запускаем новый поток
        }
    }
}