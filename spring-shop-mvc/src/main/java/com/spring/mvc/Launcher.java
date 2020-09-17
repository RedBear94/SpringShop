package com.spring.mvc;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

public class Launcher {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8190);

        // Стандартная конфигурация для сервера
        ProtectionDomain domain = Launcher.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        // Указание контекстного пути
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/app"); // Запросы принимет диспетчер
        // http://localhost:8190/app/hello
        webAppContext.setWar(location.toExternalForm());

        server.setHandler(webAppContext);
        server.start();
        server.join();
    }
}