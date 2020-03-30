package filechooser;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import generalpreps.GeneralPreps;

public class FileChooser extends JPanel implements ActionListener {

    ///Creating layout and buttons
    static private final String newline = "\n";
    JButton openButton, enpStandbtn, enpSeasbtn, bothSepfiles, bothOnefile;
    JTextArea log;
    JFileChooser fc;
    public String filelocation;
    public String filelocationstrict;

    public FileChooser() {
        super(new BorderLayout());

        log = new JTextArea(5, 20);
        log.setMargin(new Insets(5, 5, 5, 5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        fc = new JFileChooser();

        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        openButton = new JButton("Select a File");
        openButton.addActionListener(this);

        enpStandbtn = new JButton("ENP STANDARD");
        enpStandbtn.addActionListener(this);

        enpSeasbtn = new JButton("ENP SEASONAL");
        enpSeasbtn.addActionListener(this);

        bothSepfiles = new JButton("Both in separate files");
        bothSepfiles.addActionListener(this);

        bothOnefile = new JButton("Both in one file");
        bothOnefile.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(enpStandbtn);
        buttonPanel.add(enpSeasbtn);
        buttonPanel.add(bothSepfiles);
        buttonPanel.add(bothOnefile);

        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String fileorg = filelocation;
        String outfile = filelocationstrict + "linehaul2.csv";
        String outfile2 = filelocationstrict + "linehaul3.csv";
        String standard = filelocationstrict + "standard.csv";
        String standard2 = filelocationstrict + "standard2.csv";
        String seasonal = filelocationstrict + "seasonal.csv";
        String seasonal2 = filelocationstrict + "seasonal2.csv";
        String both = filelocationstrict + "standAndseas.csv";

        //Handle button action.
        if (e.getSource() == openButton) {
            handleOpenFileBtn();
        } else if (e.getSource() == enpStandbtn) {
            handleStandardsIntoFile(fileorg, outfile, outfile2, standard, standard2);
        } else if (e.getSource() == enpSeasbtn) {
            handleSeasonalsIntoFile(fileorg, outfile, outfile2, seasonal, seasonal2);
        } else if (e.getSource() == bothSepfiles) {
            handleBothIntoSeparateFiles(fileorg, outfile, outfile2, standard, standard2, seasonal, seasonal2);
        } else if (e.getSource() == bothOnefile) {
            handleBothIntoOneFile(fileorg, outfile, outfile2, both);
        }
    }

    private void handleOpenFileBtn() {
        int returnVal = fc.showOpenDialog(FileChooser.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            filelocation = file.getPath();
            filelocationstrict = filelocation.replace(file.getName(), "");

            log.append("Selected file: " + file.getPath() + "." + newline);

        } else {
            log.append("Select command cancelled by user." + newline);
        }
        log.setCaretPosition(log.getDocument().getLength());
    }

    private void handleStandardsIntoFile(String fileorg, String outfile, String outfile2, String standard, String standard2) {
        log.append("Preparing Standards." + newline);
        GeneralPreps generalPreps = new GeneralPreps();
        generalPreps.prepOfStandard(fileorg, outfile, outfile2, standard, standard2);
        log.append("Done, check your file." + newline);
        log.setCaretPosition(log.getDocument().getLength());
    }

    private void handleSeasonalsIntoFile(String fileorg, String outfile, String outfile2, String seasonal, String seasonal2) {
        log.append("Preparing Seasonals." + newline);
        GeneralPreps generalPreps = new GeneralPreps();
        generalPreps.prepOfSeasonal(fileorg, outfile, outfile2, seasonal, seasonal2);
        log.append("Done, check your file." + newline);
        log.setCaretPosition(log.getDocument().getLength());
    }

    private void handleBothIntoSeparateFiles(String fileorg, String outfile, String outfile2, String standard, String standard2, String seasonal, String seasonal2) {
        log.append("Preparing both types in different files." + newline);
        GeneralPreps generalPreps = new GeneralPreps();
        generalPreps.prepOfStandard(fileorg, outfile, outfile2, standard, standard2);
        generalPreps.prepOfSeasonal(fileorg, outfile, outfile2, seasonal, seasonal2);
        log.append("Done, check your file." + newline);
        log.setCaretPosition(log.getDocument().getLength());
    }

    private void handleBothIntoOneFile(String fileorg, String outfile, String outfile2, String both) {
        log.append("Preparing both types in one file." + newline);
        GeneralPreps generalPreps = new GeneralPreps();
        generalPreps.prepOfAll(fileorg, outfile, outfile2, both);
        log.append("Done, check your file." + newline);
        log.setCaretPosition(log.getDocument().getLength());
    }
}
