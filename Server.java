import java.net.*;
import java.io.*;
public class Server {

	
	
	ServerSocket server;
	Socket socket;
	
	BufferedReader ob;
	PrintWriter out;   //enables you to write formatted data to an underlying Writer.
	
	//constructor
	public Server()
	{
		try
		{
			server=new ServerSocket(7777);
			System.out.println("Hp's server,Ready to Connect");
			System.out.println("Waiting....");
			socket=server.accept();
			ob=new BufferedReader(new InputStreamReader(socket.getInputStream()));//imp
			out=new PrintWriter(socket.getOutputStream());//imp
			
			
			StartReading();
			StartWriting();
	    }
		catch(Exception e)
		{
			e.printStackTrace();//it is a method of Java’s throwable class which prints the throwable along with
			                    //other details like the line number and class name where the exception occurred.
		}
		
	}
	public void StartReading()
	{
		Runnable r1=()->{     //Multi_threading used
			System.out.println("reader started");
			try {
				
			while(true)
			{
				String msg=ob.readLine();
				if(msg.equals("exit"))
					{
					System.out.println("client terminated");
				    break;
					}
					System.out.println("client:"+msg);	
			}}
			catch(Exception e)
			{
				e.printStackTrace();
			
			}
		};
		new Thread(r1).start();
	}
	public void StartWriting()
	{
		Runnable r2=()->{
			try
			{
		
			while(true)
			{
				BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));//imp
				String content=br1.readLine();
				out.println(content);
				out.flush();  //The flush() method of OutputStream class is used to flush
				              //the content of the buffer to the output stream.
			}}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		};
		new Thread(r2).start();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
new Server();
	}

}