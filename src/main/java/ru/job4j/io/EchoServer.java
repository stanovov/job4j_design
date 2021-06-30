package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    boolean closed = false;
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (!closed) {
                            closed = str.contains("msg=Bye ");
                        }
                        System.out.println(str);
                    }
                    out.write("HTTP/1.1 200 OK\r\n".getBytes());
                    if (closed) {
                        out.write("SERVER CLOSED\r\n".getBytes());
                        server.close();
                    }
                }
            }
        }
    }
}