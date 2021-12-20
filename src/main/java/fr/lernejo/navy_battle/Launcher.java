package fr.lernejo.navy_battle;

import java.io.IOException;

class Launcher {

    public static void main(String[] args) throws IOException, InterruptedException {

        Server serv = new Server(args[0]);
        if (args.length == 2){
            serv.get_url(args[1]);
        }
        serv.create();


    }
}
