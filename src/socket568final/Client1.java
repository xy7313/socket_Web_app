package socket568final;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.io.PrintStream;

public class Client1 {
    public static final String IP_ADDR = "localhost";
    public static final int PORT = 12345;

    public static void main(String[] args) {

        System.out.println("client launching...\n");

        Socket socket = null;
        try {
			socket = new Socket(IP_ADDR, PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			socket.setSoTimeout(10000);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



        System.out.println("Client UP\n");

            //Read the server input
        BufferedReader input;
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Send output to the server
        PrintStream out = new PrintStream(socket.getOutputStream());

        BufferedReader typein = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Input:\n");
                String str = typein.readLine();
                System.out.print("CMD:" + str + "\n");

                out.println(str);

//                GET
                if (str.startsWith("GET ")) {


                    String ret = input.readLine();
                    System.out.println("response:" + ret);
//
                }
//                BOUNCE
                else if (str.startsWith("BOUNCE ")) {


                    String ret = input.readLine();
                    System.out.println("Bounce: " + ret);

                }
//                EXIT
                else if (str.startsWith("EXIT ") || str.startsWith("EXIT")) {


                    System.out.println("Client will be closed");

                    break;
                } else {
                    System.out.print("Please use right cmds:'GET ','BOUNCE ','EXIT',or 'EXIT '\n");
                }
            }

        typein.close();
        if(socket != null){
            socket.close();
        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			
			
		}
		

    }
}



