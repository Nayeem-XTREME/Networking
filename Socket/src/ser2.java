
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;


public class ser2 implements Serializable {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        
        System.out.println("Waiting...");
        
        ServerSocket s1 = new ServerSocket(64);
        Socket s = s1.accept();         
        
        
        
        while(1<2) {       
            
            ObjectInputStream input = new ObjectInputStream(s.getInputStream());


            Messege m2 = new Messege();
            m2 = (Messege) input.readObject();
            

            System.out.println("Get the message, which is: " + m2.message);

            
            PrintStream p = new PrintStream(s.getOutputStream()); 
            ObjectOutputStream output = new ObjectOutputStream(p);
            output.writeObject(m2);
            
            
//            if ("EXIT".equals(mess2)) {
//                p.println(mess2);
//                System.out.println("Client rejected!");
//            
//                s.close();
//                break;
//            }
            
            p.println(m2);
            System.out.println("Sending that back to client");
            System.out.println("Waiting...");
        }
    }
}
