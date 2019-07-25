
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class cli {
    public static void main(String args[]) throws UnknownHostException, IOException {
        
        Socket s = new Socket("127.0.0.1", 64);
        Scanner sc1 = new Scanner(System.in);           //input from user
        
        while(1<2) {
            System.out.println("Enter a message: ");


            String mess = sc1.nextLine();                                //number has the input

            System.out.println("Sending to server...");

            PrintStream p = new PrintStream(s.getOutputStream());       //pass the number to server
            p.println(mess);          //pass the number using object p

            Scanner sc2 = new Scanner(s.getInputStream());          //get the output from server as input
            String temp = sc2.nextLine();           //temp has the result
            
            if ("EXIT".equals(temp)) {
                s.close();
                System.out.println("Connection has been broken!");
                break;
            }
            
            System.out.println("Received back from server, which is: " + temp);
        }
    }
}
