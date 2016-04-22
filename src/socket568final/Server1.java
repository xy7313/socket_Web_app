package socket568final;



import javax.naming.ldap.SortKey;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.lang.String;


public class Server1 {

    public static final int PORT = 12345;

    public static void main(String[] args) {
        System.out.println("Server launching...\n");
        Socket socket = null;

            ServerSocket serverSocket = null;
			try {
				serverSocket = new ServerSocket(PORT);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            System.out.print("Server UP\n");

            while (true) {

                try {
					socket = serverSocket.accept();
					PrintStream out = new PrintStream(socket.getOutputStream());
		                //input stream

		             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		                //output stream
		                
		                boolean flag =true;

		                while (flag){
		                    String clientInputStr = input.readLine();
		                    if (clientInputStr != null) {
								
							
		                    System.out.println("Client Input:" + clientInputStr);
		                    //                get file
		                    String s = "";
		                    if (clientInputStr.startsWith("GET")) {
		                        String name = clientInputStr.split(" ")[1];
		                        System.out.print("name:" + name+"\n");
		                        try {
		                            File filename = new File("./src/socket568final/" + name);
		                            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
		                            BufferedReader br = new BufferedReader(reader);
		                            String line = "";
		                            line = br.readLine();
		                            s = line;
		                            while (line != null) {
		                                line = br.readLine();
		                                if (line != null) {
		                                    s = s + line;
		                                }
		                            }
		                            System.out.print("file content:" + s + "\n");
		                            


		                        } 
		                        catch (Exception e) {
		                            System.out.print("ERROR: no such file\n");
		                            
		                            s = "ERROR: no such file";
		                        }
		                        out.println(s);
		                    }

//		                BOUNCE
		                    if (clientInputStr.startsWith("BOUNCE ")) {
		                        s = clientInputStr.replaceFirst("BOUNCE ", "");
		                        System.out.print("back:" + s + "\n");
		                        out.println(s);
		                    }

//		                EXIT
		                    if (clientInputStr.startsWith("EXIT ")) {
		                        String temp = clientInputStr;
		                        if (clientInputStr.split(" ").length > 1) {
		                            s = clientInputStr.replaceFirst("EXIT ", "");
		                            System.out.print("Exit Code:" + s + "\n");
		                        } else if (clientInputStr.split(" ").length == 1) {
		                            System.out.print("Exit Code:Default\n");
		                        } 

		                        flag = false;
		                       // System.out.print(flag);
		                    }
		                    else if (clientInputStr.equals("EXIT")) {
	                            System.out.print("Exit Code:Default\n");
	                            flag = false;
		                       // System.out.print(flag);

	                        }
		                    
//		                    out.println(s);
		                }
		                else {
							flag = false;
						}    
		                }
		                out.close();
		                socket.close();
		                
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
              
            }
//        serverSocket.close();
    }
}

