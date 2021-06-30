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
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    boolean messageFound = false;
                    boolean closed = false;
                    String answer = "What";
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (!messageFound) {
                            int startIndex = str.indexOf("/?msg=");
                            int lastIndex = str.lastIndexOf(" HTTP/1.1");
                            if (startIndex != -1 && lastIndex != -1) {
                                String message = str.substring(startIndex + 6, lastIndex);
                                if (message.equals("Exit")) {
                                    closed = true;
                                } else if (message.equals("Hello")) {
                                    answer = message;
                                }
                                messageFound = true;
                            }
                        }
                        System.out.println(str);
                    }
                    if (closed) {
                        server.close();
                    } else {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write(answer.getBytes());
                    }
                }
            }
        }
    }
}