import java.net.*;
import java.io.*;
public class Client {
	
	Socket socket;
	BufferedReader ob;
	PrintWriter out;  
	
	
	
	public Client() 
	{
		try {
			System.out.println("Sending request  to Hp's server");
			socket=new Socket("127.0.0.1",7777);
			System.out.println("Connection done");
			
			ob=new BufferedReader(new InputStreamReader(socket.getInputStream()));//imp
			out=new PrintWriter(socket.getOutputStream());//imp
			
			
			StartReading();
			StartWriting();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}}
			public void StartReading()
			{
				Runnable r1=()->{     //multithreading used
					System.out.println("reader started");
					try {
						
					while(true)
					{
						String msg=ob.readLine();
						if(msg.equals("exit"))
							{
							System.out.println("  server terminated");
						    break;
							}
							System.out.println("server:"+msg);	
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
		new Client();

	}

}
