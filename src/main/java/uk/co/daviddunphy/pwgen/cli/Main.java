package uk.co.daviddunphy.pwgen.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Main {

    @Parameter(names = { "--copy", "-c" }, description = "Copy key to clipboard.")
    private boolean copy = false;

    @Parameter(names = { "--help", "-h" }, help = true, description = "Show this help message.")
    private boolean help;

    public static void main(String[] args) {
        Main main = new Main();
        JCommander jcmd = JCommander.newBuilder().addObject(main).build();
        jcmd.setProgramName("pwgen");
        jcmd.parse(args);
        main.run(jcmd);
    }

    public void run(JCommander jcmd) {
        if (help) {
            jcmd.usage();
        } else {
        }
    }

}
