
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class ser {
    public static void main(String args[]) throws IOException {
        
        System.out.println("Waiting...");
        
        ServerSocket s1 = new ServerSocket(64);
        Socket s = s1.accept();         //accept the request on port 1342
        
        
        
        while(1<2) {

            Scanner sc = new Scanner(s.getInputStream());           //get the message from client
            String mess = sc.nextLine();

            System.out.println("Get the message, which is: " + mess);

            
            System.out.println("Enter your reply:\n");
            
            Scanner sc3 = new Scanner(System.in);
            String mess2 = sc3.nextLine();
            
            PrintStream p = new PrintStream(s.getOutputStream());           //send it back to client
            
            
            if ("EXIT".equals(mess2)) {
                p.println(mess2);
                System.out.println("Client rejected!");
            
                s.close();
                break;
            }
            
            p.println(mess2);
            System.out.println("Sending that back to client");
            System.out.println("Waiting...");
        }
    }
}
