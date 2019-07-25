
import java.io.Serializable;


public class Messege implements Serializable{
    
    public String message;
    public String checksum;
    public String acknowledge;
    
    
    Messege() {
        
        message = "100011010011";
        
    }
    
}
