import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try(Socket clientSocket = new Socket("127.0.0.1", 4004);
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            writer.println("Victor");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
