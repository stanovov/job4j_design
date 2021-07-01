package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    boolean messageFound = false;
                    boolean closed = false;
                    String answer = "What";
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("/favicon")) {
                            break;
                        }
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
                    if (messageFound) {
                        if (closed) {
                            server.close();
                        } else {
                            out.write("HTTP/1.1 200 OK\r\n\r\n");
                            out.write((answer + "\n"));
                            out.flush();
                        }
                    }
                }
                socket.close();
            }
        } catch (IOException e) {
            LOG.error("There is an error in the bot", e);
        }
    }
}