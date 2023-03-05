import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try(Socket clientSocket = new Socket("127.0.0.1", 8000);
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String request = reader.readLine();
            System.out.println(request);
            String response;

            response = scanner.nextLine();
            writer.println(response);
            writer.flush();

            System.out.println(reader.readLine());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}