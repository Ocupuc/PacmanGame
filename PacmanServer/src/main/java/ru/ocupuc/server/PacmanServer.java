package ru.ocupuc.server;

import java.io.*;
import java.net.*;

public class PacmanServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("The server is running and waiting for connection...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("The client has connected");

            // Для каждого подключения создаем новый поток
            new Thread(() -> {
                try {
                    // Получение входного и выходного потока сокета
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    // Обработка сообщений от клиента
                    String messageFromClient;
                    while ((messageFromClient = in.readLine()) != null) {
                        System.out.println("Message from client: " + messageFromClient);

                        // Обработка сообщения и отправка ответа клиенту
                        String response = "Message received: " + messageFromClient;
                        out.println(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();  // Запускаем новый поток
        }
    }
}