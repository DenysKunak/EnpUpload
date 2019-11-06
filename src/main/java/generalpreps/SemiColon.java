package generalpreps;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import regcellsplitgetter.WindowsReqistry;

public class SemiColon {

    WindowsReqistry windowsReqistry = new WindowsReqistry();
    char separator = windowsReqistry.retrieveSeparator();


    public void semiColon(String file, String outfile) {


        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {

            FileReader fileReader = new FileReader(file);
//            CSVReader csvReader = new CSVReader(fileReader);
            FileWriter fileWriter = new FileWriter(outfile, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter, separator, CSVWriter.NO_QUOTE_CHARACTER);

            String[] nextRecord;
            List<String[]> modfCell = new ArrayList<String[]>();

            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    cell = cell.replace(".", ":");
                    String[] cellsplit = cell.split("\\t", 0);
                    modfCell.add(cellsplit);
                }
            }
            csvWriter.writeAll(modfCell);
//            csvReader.close();
            csvWriter.close();
        } catch (Exception exp) {

            exp.printStackTrace();
        }
    }
}
