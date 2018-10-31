package ch.robinglauser.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {

    public static int a = 0;

    public static void main(String[] args) {

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/", new FileHandler());
            server.createContext("/api", new MyHandler());
            server.start();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    server.stop(0);
                    System.out.println("Stopping");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) {
            a++;
            try {
                String response = String.valueOf(a);
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    static class FileHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String fileId = exchange.getRequestURI().getPath();
            File file = getFile(fileId);
            if (file == null) {
                String response = "Error 404 File not found.";
                exchange.sendResponseHeaders(404, response.length());
                OutputStream output = exchange.getResponseBody();
                output.write(response.getBytes());
                output.flush();
                output.close();
            } else {
                exchange.sendResponseHeaders(200, 0);
                OutputStream output = exchange.getResponseBody();
                FileInputStream fs = new FileInputStream(file);
                final byte[] buffer = new byte[0x10000];
                int count = 0;
                while ((count = fs.read(buffer)) >= 0) {
                    output.write(buffer, 0, count);
                }
                final Headers hds = exchange.getResponseHeaders();
                hds.set("Cache-Control", "no-cache");
                hds.set("Cache-Control", "no-store");
                output.flush();
                output.close();
                fs.close();
            }
        }

        private File getFile(String fileId) {
            if (fileId.equals("/")) {
                fileId = "/index.html";
            }

            return new File("/Users/robin/IdeaProjects/bfhexercise/src/main/resources/ch/robinglauser/web" + fileId);
        }
    }

}