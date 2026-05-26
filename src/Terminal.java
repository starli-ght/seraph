import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

import java.io.File;

public class Terminal {

    String TERMINAL_VERSION = "v0.1.11b";
    String SPH_VERSION = "v0.1.11a";

    File currentDirectory = new File("~");

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
                                        + Formatting.bold()
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

                case "echo":

                    if(parts.length > 1){
                        for(int i = 1; i < parts.length; i++){
                            System.out.print(parts[i] + " ");
                        }
                        System.out.println("");
                    } else {
                        System.out.println("-seraph: Missing arguments after command echo.");
                    }

                    break;

                case "rm":
                case "remove":
                
                    boolean recursive = false;
                    boolean force = false;
                
                    for(String part : parts) {
                
                        if(part.startsWith("-")) {
                
                            if(part.contains("r")) {
                                recursive = true;
                            }
                
                            if(part.contains("f")) {
                                force = true;
                            }
                        }
                    }
                
                    if(parts.length > 1) {
                
                        String target = parts[parts.length - 1];
                
                        File file =
                                new File(currentDirectory, target);
                
                        if(file.exists()) {
                
                            if(file.isDirectory()) {
                
                                File[] contents = file.listFiles();
                
                                if(contents != null
                                        && contents.length > 0
                                        && !recursive) {
                
                                    System.out.println(
                                            "-seraph: Directory is not empty. Use -r to remove recursively."
                                    );
                
                                    break;
                                }
                
                                if(recursive) {
                
                                    deleteDirectory(file);
                
                                    System.out.println(
                                            "Removed directory: " + target
                                    );
                
                                } else {
                
                                    if(file.delete()) {
                
                                        System.out.println(
                                                "Removed directory: " + target
                                        );
                
                                    } else {
                
                                        System.out.println(
                                                "-seraph: Failed to remove directory."
                                        );
                                    }
                                }
                
                            } else {
                
                                if(file.delete()) {
                
                                    if(force) {
                                        // silent-ish removal
                                    } else {
                
                                        System.out.println(
                                                "Removed: " + target
                                        );
                                    }
                
                                } else {
                
                                    System.out.println(
                                            "-seraph: Failed to remove file."
                                    );
                                }
                            }
                
                        } else {
                
                            System.out.println(
                                    "-seraph: File does not exist."
                            );
                        }
                
                    } else {
                
                        System.out.println(
                                "-seraph: Missing filename."
                        );
                    }
                
                    break;

                case "logout":

                    System.out.println("logout");

                    running = false;

                    break;

                case "sph":
                    if(parts.length > 1){
                        for(String part : parts){
                            if(part.startsWith("-")){
                                if(part.contains("-version") || part.contains("v")){
                                    System.out.println("====== sph ====== \nsph " + SPH_VERSION + "\nseraph terminal " + TERMINAL_VERSION + " \n=================");
                                    break;
                                } else if (part.contains("-help")){
                                    System.out.println("""
                                    ====== sph ======
                                    sph - A package manager for seraph.
                                    Available arguments:
                                    sph install [package] - Installs a package.
                                    sph remove [package] - Removes a package.
                                    sph list - Lists installed packages.
                                    sph search [search_pattern] - Searches for a package.
                                    sph autoremove - Removes all unrequired dependencies.
                                    =================
                                    """);
                                    break;
                                }
                            }
                        }
                        break;
                    } else {
                        System.out.println("-seraph: Too few arguments after command sph. Run sph --help for help.");
                        break;
                    }

                case "but":
                    System.out.print("oh ");
                    for(int i = 0; i < 4; i++){
                        System.out.print("no, ");
                        Thread.sleep(890);
                    }
                    System.out.println();
                    for(int i = 0; i < 3; i++){
                        System.out.print("no, ");
                        Thread.sleep(900);
                    }
                    System.out.print("ohhhh \n");
                    Thread.sleep(820);
                    System.out.println("oh, i still wanna be your favourite ");
                    Thread.sleep(890);
                    System.out.print("b");
                    for(int i = 0; i < 19; i++){
                        System.out.print("o");
                        Thread.sleep(90);
                    }
                    System.out.print("y");
                    System.out.print(", oh ohh\n");
                    break;

                case "you":
                    System.out.print("asked me, \n");
                    Thread.sleep(2000);
                    System.out.println("how i've been,");
                    Thread.sleep(2200);
                    System.out.println("but how, could i begin,");
                    Thread.sleep(3000);
                    System.out.println("to tell you, i should've chased you");
                    Thread.sleep(3400);
                    System.out.println("""
                    [cross every single state]    
                    [i should be who you're engaged to]
                    """);
                    Thread.sleep(4500);
                    System.out.println("""
                    [i lay down my sword for fate]
                    [lost my fight with fate]
                    """);
                    Thread.sleep(4200);
                    System.out.println("a tug-of-war of leave and stay,");
                    Thread.sleep(4000);
                    System.out.print("i give");
                    Thread.sleep(900);
                    System.out.print(" in,");
                    Thread.sleep(1000);
                    System.out.print(" i ab");
                    Thread.sleep(450);
                    System.out.print("di");
                    Thread.sleep(300);
                    System.out.print("cate \n");
                    Thread.sleep(1000);
                    System.out.println("i lay my sword down anwyays,\n");
                    Thread.sleep(4500);
                    System.out.print("i'll see you at heav");
                    Thread.sleep(1000);
                    System.out.print("ens gate \n");
                    Thread.sleep(3200);
                    System.out.print("'cause it's too little, ");
                    Thread.sleep(1000);
                    System.out.print("way, too late \n");
                    Thread.sleep(1500);
                    System.out.print("User, what's in your inventory? \n");
                    Thread.sleep(1500);
                    System.out.println("This terminal is all yours.");
                    System.out.println("""                          
                        .%%##############%%.
                     .###%%::::::::::::::::%%###.
                 .###%:::::;;;;;;;;;;;;;;;;:::::%###.
              .##%::::;;;;::::::::::::::::::;;;;::::%##.
            .#%:::;;;::::::##############::::::;;;:::%#.
           #%::;;::::::####%::::::::::::%####::::::;;::%#
          #::;;:::::###%::::::::::::::::::::%###:::::;;::#
         #::;;::::##%::::::::::::::::::::::::::%##::::;;::#
        #::;;:::##%::::::::::::::::::::::::::::::%##:::;;::#
       #::;;:::##::::::::::::::::::::::::::::::::::##:::;;::#
      #::;;:::##::::::::::::::::::::::::::::::::::::##:::;;::#
     #::;;:::##::::::::::::::::::::::::::::::::::::::##:::;;::#
    #::;;:::##::::::::::::::::::::::::::::::::::::::::##:::;;::#
    #::;;:::##========##::::::::::::::::::::##========##:::;;::#
   #::;;:::##::::::::##                      ##::::::::##:::;;::#
   #::;;:::##::::::::##                      ##::::::::##:::;;::#
  #::;;:::##::::::::##                        ##::::::::##:::;;::#
  #::;;:::##::::::::##                        ##::::::::##:::;;::#
  #::;;:::##::::::::##                        ##::::::::##:::;;::#
  #::;;:::##::::::::##                        ##::::::::##:::;;::#
  #::;;:::##::::::::##                        ##::::::::##:::;;::#
  #::;;:::##::::::::##                        ##::::::::##:::;;::#
  #::;;:::##::::::::##                        ##::::::::##:::;;::#
  #::;;:::##::::::::##                        ##::::::::##:::;;::#
  #::;;:::##::::::::##                        ##::::::::##:::;;::#
  #::;;:::##::::::::##                        ##::::::::##:::;;::#
  #::;;:::##::::::::##                        ##::::::::##:::;;::#
  #::;;:::##::::::::##                        ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
 #::;;:::##::::::::##                          ##::::::::##:::;;::#
####################################################################
####################################################################
""");
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

        public void deleteDirectory(File dir) {
    
        File[] files = dir.listFiles();
    
        if(files != null) {
    
            for(File file : files) {
    
                if(file.isDirectory()) {
    
                    deleteDirectory(file);
    
                } else {
    
                    file.delete();
                }
            }
        }
    
        dir.delete();
    }
}