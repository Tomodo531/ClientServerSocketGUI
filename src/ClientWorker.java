import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientWorker implements Runnable
{
    private Socket client;
    private Socket modtager;

    ClientWorker(Socket client, Socket modtager) throws IOException {
        this.client = client;
        this.modtager = modtager;
    }
    public void run()
    {
        BufferedReader in = null;
        PrintWriter out = null;
        try
        {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(modtager.getOutputStream(), true);

        } catch (IOException e) {
            System.out.println("IO failed: " + e.getMessage());
            System.exit(-1);
        }

        while(true)
        {
            try
            {
                String coordinates = in.readLine();

                if (coordinates != null) {
                    System.out.println(coordinates);
                    out.println(coordinates);
                }

            }catch (IOException e) {
                System.out.println("Read failed");
                System.exit(-1);
            }
        }
    }
}