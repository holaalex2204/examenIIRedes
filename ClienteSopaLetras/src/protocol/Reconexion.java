/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package protocol;

/**
 *
 * @author holaalex2204
 */
public class Reconexion implements java.io.Serializable {
    private String ip;
    private int port;

    public Reconexion(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Reconexion{" + "ip=" + ip + ", port=" + port + '}';
    }
    
}
