import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
    public static void main(String[] args)
    {
        System.out.println("Server Started!");

        int player = 1;
        Socket player1 = null;
        Socket player2 = null;

        try (ServerSocket serverSocket = new ServerSocket(2000))
        {
            while(true)
            {
                Socket newSocket = serverSocket.accept();

                if(player1 == null) {
                    player1 = newSocket;
                    System.out.println("Player 1");
                    player++;
                }else if(player == 2) {
                    player2 = newSocket;
                    System.out.println("Player 2");
                    player++;
                }

                if (player == 3) {
                    ClientWorker player1ClientWorker = new ClientWorker(player1, player2);
                    ClientWorker player2ClientWorker = new ClientWorker(player2, player1);
                    Thread Tplayer1ClientWorker = new Thread(player1ClientWorker);
                    Thread Tplayer2ClientWorker = new Thread(player2ClientWorker);
                    Tplayer1ClientWorker.start();
                    Tplayer2ClientWorker.start();
                    System.out.println("ClientWorkers started");
                    player++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Server Ended!");
    }
}
