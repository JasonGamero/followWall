/* Testing class for running the Client
 * 
 **/
package Client;
import java.io.IOException;
import java.net.UnknownHostException;

import Client.*;

public class Main {
	public static void main(String[] args) throws UnknownHostException, IOException{
		Client cl = new ClientImpl();
		cl.Control();
		cl.send("hallo server");
	}
}
