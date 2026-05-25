import java.util.Scanner;

public class Terminal {

    Scanner scanner = new Scanner(System.in);

    // for formatting, use sph.Color.[colorname](); for colors check Formatting.java

    public void start() throws InterruptedException {

        for(int i = 0; i < 101; i++) {

            System.out.print("\rLoading... (" + i + "%)");

            Thread.sleep(10);
        }

        System.out.println("\rDone!               ");

        boolean running = true;

        while(running) {

            System.out.print(Formatting.bold() + Formatting.magenta() + "root@seraph > " + Formatting.blue() + "~/" + Formatting.reset() + "$ ");

            String command = scanner.nextLine();

            String[] parts = command.split(" ");

            if(command.isBlank()) {
                    continue;
            }
            
            switch(parts[0]) {

                case "echo":

                    if(parts.length > 1) {

                        for(int i = 1; i < parts.length; i++) {
                            System.out.print(parts[i] + " ");
                        }

                        System.out.println();

                    } else {

                        System.out.println(
                            "-seraph: Missing argument after echo; Expected a string but got null."
                        );
                    }

                    break;

                case "logout":

                    System.out.println("logout");

                    running = false;

                    break;

                case "sph":

                    boolean debug = false;

                    if(parts.length > 1){
                        switch(parts[1]) {
                            case "--help":
                                System.out.println("sph install [package] - Installs a package to seraph. \n sph search [package] - Searches for similar package names. \n sph remove [package] - Removes selected package. sph autoremove - Autoremove any unused dependencies. \n === FLAGS === \n --version - Displays the current sph version. \n -d - Turns on debug mode");
                                break;
                            case "--version":
                                System.out.println("Package Manager for Seraph - sph \nVersion 0.1.10a.");
                        }
                    } else {
                        System.out.println("Too few arguments. Use --help for help.");
                    }
                    break;

                case "help":
                    System.out.println("seraph Terminal - V0.1.11a - by Archangels. \nUnavailable commands will be marked with an asterisk(*), and broken commands will be marked with a hashtag(#).");
                    System.out.println("sph install | remove | autoremove [flags] : sph command, which is a built-in package manager like apt.");
                    System.out.println("echo [arguments] : Echoes (or prints out) the arguments.");
                    System.out.println("clear : Clears the console history.");
                    System.out.println("logout : Logs out from the seraph terminal.");
                    break;

                case "clear":

                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    break;

                default:

                    System.out.println("-seraph: Unknown Command: " + parts[0]);
            }
        }
    }
}