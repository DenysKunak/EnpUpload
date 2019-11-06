package generalpreps;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import regcellsplitgetter.WindowsReqistry;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InfAdd {

    WindowsReqistry windowsReqistry = new WindowsReqistry();
    char separator = windowsReqistry.retrieveSeparator();
    String stringseparator = String.valueOf(separator);

    public void addLnCdForSeas(String file, String outfile) {

        LocalDate date = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader, separator);
            FileWriter fileWriter = new FileWriter(outfile, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter, separator, CSVWriter.NO_QUOTE_CHARACTER);

            List<String[]> allRows = csvReader.readAll();
            List<String[]> modfRows = new ArrayList<String[]>();

            for (String[] row : allRows) {
                List<LocalDate> valToFromDateArr = new ArrayList<LocalDate>();

                String LineCode = Arrays.toString(row).substring(1, 7);
                String WholeRow = (LineCode + Arrays.toString(row)).replace("[", stringseparator);
                WholeRow = WholeRow.replace("]", "");
                WholeRow = WholeRow.replace(" ", "");
                WholeRow = WholeRow.replace(",", stringseparator);

                for (String cell : row) {
                    if (cell.matches("(\\d+)-(\\d+)-(\\d+)") && WholeRow.contains("Seasonal")) {

                        LocalDate valToFromDate = LocalDate.parse(cell, format);
                        String valToFromDate2 = valToFromDate.format(format2);
                        valToFromDateArr.add(valToFromDate);
                        WholeRow = WholeRow.replaceAll(cell, valToFromDate2);
                        WholeRow = WholeRow + stringseparator + valToFromDate2;
                    }
                }
                if (valToFromDateArr.get(1).isAfter(date)) {
                    WholeRow = WholeRow.replace(stringseparator + "0" + stringseparator, stringseparator + "accepted" + stringseparator);
                } else if (valToFromDateArr.get(1).equals(date) || valToFromDateArr.get(1).isBefore(date)) {
                    WholeRow = WholeRow.replace(stringseparator + "0" + stringseparator, stringseparator + "expired" + stringseparator);
                }
                WholeRow = WholeRow.replace("CrossBorder", "Cross Border");
                WholeRow = WholeRow.replace("+1Week", "+1 Week");
                WholeRow = WholeRow.replace("+2Week", "+2 Week");
                WholeRow = WholeRow.replace("+3Week", "+3 Week");
                String[] WholeRowObj = WholeRow.split(stringseparator);
                modfRows.add(WholeRowObj);
            }
            csvWriter.writeAll(modfRows);
            csvReader.close();
            csvWriter.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public void addLnCdForStd(String file, String outfile) {

        LocalDate date = LocalDate.now();
        LocalDate valToDate = date;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader, separator);
            FileWriter fileWriter = new FileWriter(outfile, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter, separator, CSVWriter.NO_QUOTE_CHARACTER, stringseparator + stringseparator + "\n");

            List<String[]> allRows = csvReader.readAll();
            List<String[]> modfRows = new ArrayList<String[]>();

            for (String[] row : allRows) {
                List<String> cellArr = new ArrayList<String>();

                String LineCode = Arrays.toString(row).substring(1, 7);
                String WholeRow = (LineCode + Arrays.toString(row)).replace("[", stringseparator);
                WholeRow = WholeRow.replace("]", "");
                WholeRow = WholeRow + stringseparator;
                WholeRow = WholeRow.replace(" ", "");
                WholeRow = WholeRow.replace(",", stringseparator);
                WholeRow = WholeRow.replace(stringseparator + "Standard" + stringseparator + "0" + stringseparator, stringseparator + "Standard" + stringseparator + "accepted");
                WholeRow = WholeRow.replace(stringseparator + "Additional" + stringseparator + "0" + stringseparator, stringseparator + "Additional" + stringseparator + "accepted");

                for (String cell : row) {
                    cellArr.add(cell);
                    if (cell.matches("(\\d+)-(\\d+)-(\\d+)")) {

                        LocalDate valToFromDate = LocalDate.parse(cell, format);
                        String valToFromDate2 = valToFromDate.format(format2);
                        WholeRow = WholeRow.replaceAll(cell, valToFromDate2);
                    }
                }
                for (int i = 1; i < cellArr.size(); i++) {
                    if (cellArr.get(i).matches("(\\d+)-(\\d+)-(\\d+)") && (cellArr.get(i - 1).matches("(\\d+)-(\\d+)-(\\d+)") || cellArr.get(i - 1).matches("")) && WholeRow.contains("Standard")) {
                        valToDate = LocalDate.parse(cellArr.get(i), format);
                        if (valToDate.equals(date) || valToDate.isBefore(date)) {
                            WholeRow = WholeRow.replace(stringseparator + "accepted", stringseparator + "expired");
                        }
                    }
                }
                WholeRow = WholeRow.replace("CrossBorder", "Cross Border");
                WholeRow = WholeRow.replace("+1Week", "+1 Week");
                WholeRow = WholeRow.replace("+2Week", "+2 Week");
                WholeRow = WholeRow.replace("+3Week", "+3 Week");
                String[] WholeRowObj = WholeRow.split(stringseparator);
                modfRows.add(WholeRowObj);
            }
            csvWriter.writeAll(modfRows);
            csvReader.close();
            csvWriter.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
