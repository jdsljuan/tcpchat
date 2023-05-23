import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientTCP{
	public static void main(String[] args) throws Exception{
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Socket socket = null;

		try{
			String ipserver = args[0];
			String request = args[1];
			socket = new Socket(ipserver, 10000);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());

			oos.writeObject(request);

			String serverResponse = (String) ois.readObject();
			System.out.println(serverResponse);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(ois != null){ ois.close(); }
			if(oos != null){ oos.close(); }
			if(socket != null){ socket.close(); } 
		}
	}
}
