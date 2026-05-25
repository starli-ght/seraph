import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

import java.io.File;

public class Terminal {

    File currentDirectory = new File("seraphfs");

    public void start() throws InterruptedException {

        LineReader reader =
                LineReaderBuilder.builder().build();

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
                    + Formatting.cyan()
                    + currentDirectory.getPath()
                    + Formatting.reset()
                    + "$ ";

            String command = reader.readLine(prompt);

            if(command.isBlank()) {
                continue;
            }

            String[] parts = command.split(" ");

            switch(parts[0]) {

                case "help":

                    System.out.println("""
═══════════════════════════════════════════════════════
                 SERAPH TERMINAL
═══════════════════════════════════════════════════════

 Version : 0.1.11b
 Kernel  : AngelsOS CLI
 Shell   : seraph

───────────────────────────────────────────────────────
 BASIC COMMANDS
───────────────────────────────────────────────────────

 help
    Displays this menu.

 clear
    Clears terminal output.

 logout
    Exits the terminal session.

 echo [text]
    Prints provided arguments.

───────────────────────────────────────────────────────
 FILESYSTEM
───────────────────────────────────────────────────────

 ls
    Lists current directory contents.

 mkdir [name]
    Creates a directory.

 touch [name]
    Creates a file.

 cd [directory]
    Changes current directory.

 cd ..
    Returns to parent directory.

───────────────────────────────────────────────────────
 PACKAGE MANAGER
───────────────────────────────────────────────────────

 sph --help
    Displays sph help page.

 sph --version
    Displays current sph version.

""");

                    break;

                case "ls":

                    File[] files =
                            currentDirectory.listFiles();

                    if(files != null) {

                        for(File file : files) {

                            if(file.isDirectory()) {

                                System.out.println(
                                        Formatting.blue()
                                        + file.getName()
                                        + Formatting.reset()
                                );

                            } else {

                                System.out.println(
                                        file.getName()
                                );
                            }
                        }
                    }

                    break;

                case "mkdir":

                    if(parts.length > 1) {

                        File dir =
                                new File(
                                        currentDirectory,
                                        parts[1]
                                );

                        if(dir.mkdir()) {

                            System.out.println(
                                    "Directory created."
                            );

                        } else {

                            System.out.println(
                                    "-seraph: Failed to create directory."
                            );
                        }

                    } else {

                        System.out.println(
                                "-seraph: Missing directory name."
                        );
                    }

                    break;

                case "touch":

                    if(parts.length > 1) {

                        try {

                            File file =
                                    new File(
                                            currentDirectory,
                                            parts[1]
                                    );

                            if(file.createNewFile()) {

                                System.out.println(
                                        "File created."
                                );

                            } else {

                                System.out.println(
                                        "-seraph: File already exists."
                                );
                            }

                        } catch(Exception e) {

                            System.out.println(
                                    "-seraph: Failed to create file."
                            );
                        }

                    } else {

                        System.out.println(
                                "-seraph: Missing filename."
                        );
                    }

                    break;

                case "cd":

                    if(parts.length > 1) {

                        File newDir;

                        if(parts[1].equals("..")) {

                            newDir =
                                    currentDirectory
                                            .getParentFile();

                            if(newDir == null) {

                                System.out.println(
                                        "-seraph: Already at root."
                                );

                                break;
                            }

                        } else {

                            newDir =
                                    new File(
                                            currentDirectory,
                                            parts[1]
                                    );
                        }

                        if(newDir.exists()
                                && newDir.isDirectory()) {

                            currentDirectory = newDir;

                        } else {

                            System.out.println(
                                    "-seraph: Directory does not exist."
                            );
                        }

                    } else {

                        System.out.println(
                                "-seraph: Missing directory."
                        );
                    }

                    break;

                case "clear":

                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    break;

                case "logout":

                    System.out.println("logout");

                    running = false;

                    break;

                default:

                    System.out.println(
                            "-seraph: Unknown command: "
                            + parts[0]
                    );
            }
        }
    }
}