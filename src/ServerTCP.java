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
				System.out.println("Iniciando Conexion ... "+System.currentTimeMillis());
				socket = serverSocket.accept();

				(new ResponseChannel(socket)).start();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	static class ResponseChannel extends Thread{
		private Socket s = null;
		private ObjectInputStream ois = null;
		private ObjectOutputStream oos = null; 

		public ResponseChannel(Socket socket){
			this.s = socket;
		}

		public void run(){
			try{
				System.out.println("Conexion desde IP: "+s.getInetAddress()+" a las "+System.currentTimeMillis());
				ois = new ObjectInputStream(s.getInputStream());
				oos = new ObjectOutputStream(s.getOutputStream());
				String clientRequest = (String) ois.readObject();
				String serverResponse = "Respuesta del Servidor a "+clientRequest+" <---";
				oos.writeObject(serverResponse);
				System.out.println("Respuesta Enviada a las "+System.currentTimeMillis());
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(oos != null){ oos.close(); }
					if(ois != null){ ois.close(); }
					if(s != null){ s.close(); }	
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
