public class Formatting {

    // Base Escape Code
    public static final String EC = "\u001B[";
    
    // =========================================================================
    // Text Styles
    // =========================================================================
    
    public static String reset() {
        return EC + "0m";
    }
    
    public static String bold() {
        return EC + "1m";
    }

    public static String faint() {
        return EC + "2m";
    }

    public static String italic() {
        return EC + "3m";
    }

    public static String underline() {
        return EC + "4m";
    }

    public static String blink() {
        return EC + "5m";
    }

    public static String rapidBlink() {
        return EC + "6m";
    }

    public static String reverse() {
        return EC + "7m";
    }

    public static String conceal() {
        return EC + "8m";
    }

    public static String strikethrough() {
        return EC + "9m";
    }

    // =========================================================================
    // Foreground Colors (Text)
    // =========================================================================

    public static String black()   { return EC + "30m"; }
    public static String red()     { return EC + "31m"; }
    public static String green()   { return EC + "32m"; }
    public static String yellow()  { return EC + "33m"; }
    public static String blue()    { return EC + "34m"; }
    public static String magenta() { return EC + "35m"; }
    public static String cyan()    { return EC + "36m"; }
    public static String white()   { return EC + "37m"; }
    
    // Bright Foreground Colors
    public static String brightBlack()   { return EC + "90m"; }
    public static String brightRed()     { return EC + "91m"; }
    public static String brightGreen()   { return EC + "92m"; }
    public static String brightYellow()  { return EC + "93m"; }
    public static String brightBlue()    { return EC + "94m"; }
    public static String brightMagenta() { return EC + "95m"; }
    public static String brightCyan()    { return EC + "96m"; }
    public static String brightWhite()   { return EC + "97m"; }

    // =========================================================================
    // Background Colors
    // =========================================================================

    public static String bgBlack()   { return EC + "40m"; }
    public static String bgRed()     { return EC + "41m"; }
    public static String bgGreen()   { return EC + "42m"; }
    public static String bgYellow()  { return EC + "43m"; }
    public static String bgBlue()    { return EC + "44m"; }
    public static String bgMagenta() { return EC + "45m"; }
    public static String bgCyan()    { return EC + "46m"; }
    public static String bgWhite()   { return EC + "47m"; }
    
    // Bright Background Colors
    public static String bgBrightBlack()   { return EC + "100m"; }
    public static String bgBrightRed()     { return EC + "101m"; }
    public static String bgBrightGreen()   { return EC + "102m"; }
    public static String bgBrightYellow()  { return EC + "103m"; }
    public static String bgBrightBlue()    { return EC + "104m"; }
    public static String bgBrightMagenta() { return EC + "105m"; }
    public static String bgBrightCyan()    { return EC + "106m"; }
    public static String bgBrightWhite()   { return EC + "107m"; }

    // =========================================================================
    // Advanced 256-Color & Custom RGB Methods
    // =========================================================================

    /**
     * Set foreground color using a 256-color ID (0-255).
     */
    public static String color256(int id) {
        return EC + "38;5;" + id + "m";
    }

    /**
     * Set background color using a 256-color ID (0-255).
     */
    public static String bgColor256(int id) {
        return EC + "48;5;" + id + "m";
    }

    /**
     * Set true color foreground using RGB values (0-255).
     */
    public static String rgb(int r, int g, int b) {
        return EC + "38;2;" + r + ";" + g + ";" + b + "m";
    }

    /**
     * Set true color background using RGB values (0-255).
     */
    public static String bgRgb(int r, int g, int b) {
        return EC + "48;2;" + r + ";" + g + ";" + b + "m";
    }
}
