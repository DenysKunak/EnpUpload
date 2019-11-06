package generalpreps;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import regcellsplitgetter.WindowsReqistry;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Removal {

    WindowsReqistry windowsReqistry = new WindowsReqistry();
    char separator = windowsReqistry.retrieveSeparator();


    public void remSchSysAndStd(String file, String outfile) {

        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
            FileWriter fileWriter = new FileWriter(outfile, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter, separator, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER);

            List<String[]> allRows = csvReader.readAll();
            List<String[]> modfRows = new ArrayList<String[]>();

            for (String[] row : allRows) {
                if (Arrays.toString(row).contains("SCHENKERsystem") && Arrays.toString(row).contains("Seasonal")) {
                    modfRows.add(row);
                }
            }
            csvWriter.writeAll(modfRows);
            csvReader.close();
            csvWriter.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public void remSchSysAndSeas(String file, String outfile) {

        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
            FileWriter fileWriter = new FileWriter(outfile, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter, separator, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER);

            List<String[]> allRows = csvReader.readAll();
            List<String[]> modfRows = new ArrayList<String[]>();

            for (String[] row : allRows) {

                if (Arrays.toString(row).contains("SCHENKERsystem") && !Arrays.toString(row).contains("Seasonal")) {
                    modfRows.add(row);
                }
            }

            csvWriter.writeAll(modfRows);
            csvReader.close();
            csvWriter.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
