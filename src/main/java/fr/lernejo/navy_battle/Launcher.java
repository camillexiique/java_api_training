package fr.lernejo.navy_battle;

import java.io.IOException;

class Launcher {

    public static void main(String[] args) {

        String url = "";
        if (args.length == 1 || args.length == 2) {

            if (args.length == 2) {
                url = args[1];
            }
            Server serv = new Server(args[0], url);
            serv.create();
        }

    }
}
