package com.timwe.selenium;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.openqa.selenium.remote.server.DriverServlet;

/**
 * 
 * @author Rashidi Zin
 *
 */
public class AppServer {

    private Server server = new Server();
    
    public AppServer() {
        WebAppContext context = new WebAppContext();
        context.setContextPath(EMPTY);
        context.setWar(".");
        server.setHandler(context);
        
        context.addServlet(DriverServlet.class, "/wd/*");
        
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(8080);
        server.addConnector(connector);
    }
    
    public void start() throws Exception {
        server.start();
    }
    
    public void stop() throws Exception {
        server.stop();
    }
}
