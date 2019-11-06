package regcellsplitgetter;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class WindowsReqistry {
    public static final String readRegistry(String location, String key){
        try {
            // Run reg query, then read output with StreamReader (internal class)
            Process process = Runtime.getRuntime().exec("reg query " +
                    '"'+ location + "\" /v " + key);

            StreamReader reader = new StreamReader(process.getInputStream());
            reader.start();
            process.waitFor();
            reader.join();
            String output = reader.getResult();

            // Parse out the value
            String[] parsed = output.split("\t");
            return parsed[parsed.length-1];
        }
        catch (Exception exc) {
            return null;
        }

    }

    static class StreamReader extends Thread {
        private InputStream is;
        private StringWriter sw= new StringWriter();

        public StreamReader(InputStream is) {
            this.is = is;
        }

        public void run() {
            try {
                int c;
                while ((c = is.read()) != -1)
                    sw.write(c);
            }
            catch (IOException e) {
            }
        }

        public String getResult() {
            return sw.toString();
        }
    }
    public char retrieveSeparator() {

        String value = WindowsReqistry.readRegistry("HKCU\\Control Panel\\" + "International", "sList");
        int valuelangth = value.length();
        char valueInchar = value.charAt(valuelangth-5);

        return valueInchar;
    }
}
