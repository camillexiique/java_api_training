package fr.lernejo.navy_battle;

import java.io.IOException;

class Launcher {

    public static void main(String[] args) throws IOException {

        Server serv = new Server(args[0]);
        serv.create();


    }
}
