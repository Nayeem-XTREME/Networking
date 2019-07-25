
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class clie2 {
    
    
    public static Messege m1 = new Messege();
    
    public static void checksum() {
        
            StringBuilder str = new StringBuilder(m1.message);
            
            int length = m1.message.length();
            if (length % 4 != 0) {
                
                int rem = 4 - (length % 4);
                for (int i = 0; i < rem; i++) {
                    str.insert(i, "0");
                }
                
                System.out.println(str);
                
            }
            
            int length2 = str.length() / 4;
            
            StringBuilder checksum = new StringBuilder("0000");
            
            for (int i = (str.length() - 1); i >= 0; i = i - 4) {
                
                int carry = 0;
                for (int j = i, k = 3; j > i - 4; j--, k--) {
                   
                      if (str.charAt(j) == '0' && checksum.charAt(k) == '0' && carry == 0)  {
                          carry = 0;
                          checksum.setCharAt(k, '0');
                      }
                      
                      else if (str.charAt(j) == '0' && checksum.charAt(k) == '0' && carry == 1) {
                          carry = 0;
                          checksum.setCharAt(k, '1');
                      }
                      
                      else if (str.charAt(j) == '0' && checksum.charAt(k) == '1' && carry == 0) {
                          carry = 0;
                          checksum.setCharAt(k, '1');
                      }
                      
                      else if (str.charAt(j) == '0' && checksum.charAt(k) == '1' && carry == 1) {
                          carry = 1;
                          checksum.setCharAt(k, '0');
                      }
                      
                      else if (str.charAt(j) == '1' && checksum.charAt(k) == '0' && carry == 0) {
                          carry = 0;
                          checksum.setCharAt(k, '1');
                      }
                      
                      else if (str.charAt(j) == '1' && checksum.charAt(k) == '0' && carry == 1) {
                          carry = 1;
                          checksum.setCharAt(k, '0');
                      }
                      
                      else if (str.charAt(j) == '1' && checksum.charAt(k) == '1' && carry == 0) {
                          carry = 1;
                          checksum.setCharAt(k, '0');
                      }
                      
                      else if (str.charAt(j) == '1' && checksum.charAt(k) == '1' && carry == 1) {
                          carry = 1;
                          checksum.setCharAt(k, '1');
                      }
                    
                } 
                
                if (carry == 1) {    
                    while (carry != 0) {
                        for (int k = 3; k >= 0; k--) {
                            if (checksum.charAt(k) == '0') {
                                checksum.setCharAt(k, '1');
                                carry = 0;
                                break;
                            }
                            else if (checksum.charAt(k) == '1') {
                                checksum.setCharAt(k, '0');
                            }
                        }
                    } 
                }
                
            }
            
            System.out.println("Result = " + checksum);
            for (int i = 0; i < 4; i++) {
                if (checksum.charAt(i) == '1') {
                    checksum.setCharAt(i, '0');
                }
                else if (checksum.charAt(i) == '0') {
                    checksum.setCharAt(i, '1');
                }
            }
            
            System.out.println("One's complement Result = " + checksum);
            
            m1.message = str.toString()+checksum.toString();
            m1.checksum = checksum.toString();
            
            System.out.println("Total Result = " + m1.message);

    }
    
    
    
    
    public static void main(String args[]) throws UnknownHostException, IOException, ClassNotFoundException {
        
        Socket s = new Socket("127.0.0.1", 64);
        Scanner sc1 = new Scanner(System.in); 
        
        
        while(1<2) {
            System.out.println("Enter a message: ");
            m1.message = sc1.nextLine();
            checksum();
     

            System.out.println("Sending to server...");
            

            PrintStream p = new PrintStream(s.getOutputStream());  
            ObjectOutputStream output = new ObjectOutputStream(p);
            output.writeObject(m1);


            ObjectInputStream input = new ObjectInputStream(s.getInputStream());
            Messege m3 = new Messege();
            m3 = (Messege) input.readObject();
            
            
            
//            if ("EXIT".equals(temp)) {
//                s.close();
//                System.out.println("Connection has been broken!");
//                break;
//            }
            
            System.out.println("Received back from server, which is: " + m3.message);
        }
    }
}
