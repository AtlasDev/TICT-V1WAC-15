package nl.atlasdev.v1wac.standalone.les1.p3;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) throws IOException {
		// Copy pasta from 1.2
		ServerSocket ss = new ServerSocket(4711);
		Scanner scanner = new Scanner(ss.accept().getInputStream());
		while(scanner.hasNext()) System.out.println(scanner.nextLine());
		scanner.close();
		ss.close();
	}
}