import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) {

        List<String> gameCitiesList = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(8000);) {

            System.out.println("Сервер запущен");

            while (true) {

                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    if (gameCitiesList.size() == 0) {
                        writer.println("???");
                        writer.flush();
                        gameCitiesList.add(reader.readLine());
                        writer.println("OK");
                    } else {

                        writer.println(gameCitiesList.get(gameCitiesList.size() - 1));

                        String request = reader.readLine();
                        String lastWord = gameCitiesList.get(gameCitiesList.size() - 1).toLowerCase();
                        String curWord = request.toLowerCase();

                        char tmp1 = lastWord.charAt(lastWord.length() - 1);
                        char tmp2 = curWord.charAt(0);

                        if (tmp1 == 'ь') {
                            tmp1 = lastWord.charAt(lastWord.length() - 2);
                        }

                        if (tmp1 == tmp2)  {
                            writer.println("OK");
                            gameCitiesList.add(request);
                        } else {
                            writer.println("NOT OK");
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}