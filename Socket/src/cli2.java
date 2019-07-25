
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class cli2 {
    public static void main(String args[]) throws UnknownHostException, IOException {
        
        Socket s = new Socket("127.0.0.1", 1064);
        System.out.println("Enter a message: ");
        
        Scanner sc1 = new Scanner(System.in);                               //input from user
        String mess = sc1.nextLine();                                               //mess has the input
        
        System.out.println("Sending to server...");
        
        PrintStream p = new PrintStream(s.getOutputStream());       //pass the message to server
        p.println(mess);                                                                    //pass the message using object p
        
        Scanner sc2 = new Scanner(s.getInputStream());                  //get the output from server as input
        String temp = sc2.nextLine();                                               //temp has the result
        System.out.println("Received back from server, which is: " + temp);
    }
}
