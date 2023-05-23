import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ServerTCP{

	public static void main(String[] args) throws Exception{
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Socket socket = null;
		ServerSocket serverSocket = new ServerSocket(10000);

		while(true){
			//Esperando la Conexion
			try{
				System.out.println("Esperando Conexion ... "+System.currentTimeMillis());
				socket = serverSocket.accept();
				System.out.println("Conexion desde IP: "+socket.getInetAddress()+" a las "+System.currentTimeMillis());
				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());
				String clientRequest = (String) ois.readObject();
				String serverResponse = "Respuesta del Servidor a "+clientRequest+" <---";
				oos.writeObject(serverResponse);
				System.out.println("Respuesta Enviada a las "+System.currentTimeMillis());
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(oos != null){ oos.close(); }
				if(ois != null){ ois.close(); }
				if(socket != null){ socket.close(); }
			}
		}
		
	}
}
