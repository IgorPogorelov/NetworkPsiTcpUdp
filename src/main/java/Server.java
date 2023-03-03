import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4004);) {
            System.out.println("Сервер запущен");

            try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                System.out.println("New connection accepted");
                final String name = in.readLine();
                System.out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
