package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {

            while (!server.isClosed()) {
                Socket socket = server.accept();

                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());

                    String firstLine = input.readLine();
                    System.out.println(firstLine);

                    String msg = "";
                    if (firstLine != null && firstLine.contains("msg=")) {
                        msg = firstLine.split("msg=")[1].split(" ")[0];
                    }

                    if ("Exit".equals(msg)) {
                        server.close();
                    }

                    String responce;
                    if ("Hello".equals(msg)) {
                        responce = "Hello";
                    } else if ("Exit".equals(msg)) {
                        responce = "Shut down the server";
                        server.close();
                        LOG.info("Server closed");
                    } else {
                        responce = msg.isEmpty() ? "" : msg;
                    }

                    output.write(responce.getBytes());

                    for (String string = input.readLine();
                         string != null && !string.isEmpty();
                         string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in EchoServer", e);
        }
    }
}
