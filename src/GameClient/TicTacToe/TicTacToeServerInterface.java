/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameClient.TicTacToe;

import GameClient.Util.ServerClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author philipp
 */
public class TicTacToeServerInterface implements ServerClient{

    private Socket socket;
    private PrintWriter pw;
    private BufferedReader br;
    
    public void set(int row, int col)
    {
        writeToServer(String.format("set{%d,%d}", row,col));
    }
    
    public void quit()
    {
        writeToServer("quit{}");
    }
    
    public void keepAlive()
    {
        writeToServer("keepAlive{}");
    }
    
    @Override
    public boolean connect(Inet4Address ip, int port) {
        try {
            socket = new Socket(ip, port);
            pw = new PrintWriter(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public void writeToServer(Object o) {
        pw.println(o.toString());
        pw.flush();
    }

    @Override
    public Object listenToServer() {
        try {
            return br.readLine();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public boolean disconnect() {
        try {
            socket.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
}
