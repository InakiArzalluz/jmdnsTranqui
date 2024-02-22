package src.DnsSdJava;
import java.io.IOException;
import java.net.InetAddress;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class ServiceRegistration {

    private static JmDNS jmdns;

    public static void main(String[] args) throws InterruptedException {
        registerService(args);
        unregisterAllServices();
    }

    public static void registerService(String[] args) throws InterruptedException {
        try {
            if (args.length == 2){
                // JmDNS con la IP
                jmdns = JmDNS.create(InetAddress.getByName(args[0]));

                // Register a service
                int port = Integer.parseInt(args[1]);
                ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.", "Tranquilidad 360", port, "{'text': 'texto'}");
                jmdns.registerService(serviceInfo);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void unregisterAllServices(){
        try{
            if(jmdns != null){
                jmdns.unregisterAllServices();
                jmdns.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}