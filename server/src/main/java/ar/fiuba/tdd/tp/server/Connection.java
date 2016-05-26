package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.server.communication.Request;
import ar.fiuba.tdd.tp.server.communication.Response;
import ar.fiuba.tdd.tp.server.io.ServerOutput;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

public class Connection extends Thread {
    private final ServerSocket serverSocket;
    private final Game game;
    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Request request;
    private Response response;

    public Connection(ServerSocket serverSocket, Game game) {
        this.serverSocket = requireNonNull(serverSocket);
        this.game = game;
    }

    public void closeConnection() {
        try {
            if (nonNull(clientSocket) && !clientSocket.isClosed()) {
                clientSocket.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getStream(Socket clientSocket) throws IOException {
        this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    private void speak() throws IOException, ClassNotFoundException {
        welcome();
        boolean exit = false;
        while (!exit && nonNull(request = (Request) inputStream.readObject())) {
            if (request.isExit()) {
                exit = true;
                response = new Response("exit");
            } else {
                response = new Response(game.command(request.getSomething()));
            }
            outputStream.writeObject(response);
            outputStream.flush();
        }
    }

    private void welcome() throws IOException {
        response = new Response(game.getWelcomeMessage());
        outputStream.writeObject(response);
        outputStream.flush();
    }

    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                clientSocket = serverSocket.accept();
                ServerOutput.clientConnected(serverSocket.getLocalPort());
                getStream(clientSocket);
                speak();
                clientSocket.close();
                ServerOutput.clientDisconnected(serverSocket.getLocalPort());
            }
        } catch (ClassNotFoundException | IOException e) {
            ServerOutput.threadFinished();
        }
    }
}