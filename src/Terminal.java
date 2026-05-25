import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

public class Terminal {

    // for formatting, use Formatting.[colorname]();

    public void start() throws InterruptedException {

        LineReader reader = LineReaderBuilder.builder().build();

        for(int i = 0; i < 101; i++) {

            System.out.print("\rLoading... (" + i + "%)");

            Thread.sleep(10);
        }

        System.out.println("\rDone!               ");

        boolean running = true;

        while(running) {

            String prompt =
                    Formatting.bold()
                    + Formatting.magenta()
                    + "root@seraph > "
                    + Formatting.blue()
                    + "~/"
                    + Formatting.reset()
                    + "$ ";

            String command = reader.readLine(prompt);

            if(command.isBlank()) {
                continue;
            }

            String[] parts = command.split(" ");

            switch(parts[0]) {

                case "echo":

                    if(parts.length > 1) {

                        for(int i = 1; i < parts.length; i++) {

                            System.out.print(parts[i]);

                            if(i < parts.length - 1) {
                                System.out.print(" ");
                            }
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

                    if(parts.length > 1) {

                        switch(parts[1]) {

                            case "--help":

                                System.out.println("""
                                sph install [package] - Installs a package to seraph.
                                sph search [package] - Searches for similar package names.
                                sph remove [package] - Removes selected package.
                                sph autoremove - Autoremove unused dependencies.

                                === FLAGS ===
                                --version - Displays current sph version.
                                -d - Turns on debug mode
                                """);

                                break;

                            case "--version":

                                System.out.println("""
                                Package Manager for Seraph - sph
                                Version 0.1.10a.
                                """);

                                break;

                            default:

                                System.out.println(
                                    "-seraph: Unknown sph argument: " + parts[1]
                                );
                        }

                    } else {

                        System.out.println(
                            "Too few arguments. Use --help for help."
                        );
                    }

                    break;

                case "help":

                    System.out.println("""
                    seraph Terminal - V0.1.11a - by Archangels.

                    Unavailable commands will be marked with an asterisk(*),
                    and WIP commands will be marked with a hashtag(#).

                    sph install | remove | autoremove [flags] - Built-in package manager for seraph.
                    echo [arguments] - Echoes(prints out) the arguments.
                    clear - Clears the console history.
                    logout - Logs out from seraph terminal.
                    """);

                    break;

                case "clear":

                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    break;

                default:

                    System.out.println(
                        "-seraph: Unknown Command: " + parts[0]
                    );
            }
        }
    }
}