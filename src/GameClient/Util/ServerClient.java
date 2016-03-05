/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameClient.Util;

import java.net.Inet4Address;

/**
 *
 * @author philipp
 */
public interface ServerClient {
    public boolean connect(Inet4Address ip,int port);
    public void writeToServer(Object o);
    public Object listenToServer();
    public boolean disconnect();
}
