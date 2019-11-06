package generalpreps;

import com.opencsv.CSVWriter;
import regcellsplitgetter.WindowsReqistry;

import java.io.FileWriter;

public class HeadersAdd {

    WindowsReqistry windowsReqistry = new WindowsReqistry();
    char separator = windowsReqistry.retrieveSeparator();

    public void headersAdd(String outfile) {

        try {
            FileWriter fileWriter = new FileWriter(outfile);
            CSVWriter csvWriter = new CSVWriter(fileWriter, separator, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            String[] header = {"lineCode", "departureID", "scheduleType", "departureCountry", "departureTerminal", "arrivalCountry", "arrivalTerminal", "loadHandlingTime", "departureDay", "departureTime",
                    "arrivalDay", "arrivalWeek", "arrivalTime", "unloadHandlingTime", "validFrom", "validTo", "enpDistance", "lineHaulType", "productName", "departureType", "departureStatus",
                    "scheduleValidFrom", "scheduleValidTo"};
            csvWriter.writeNext(header);
            csvWriter.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
