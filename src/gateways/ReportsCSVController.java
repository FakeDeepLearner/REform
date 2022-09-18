package gateways;

import entities.ReportMessage;
import entities.containers.ReportContainer;
import useCases.messageUseCases.SendReportMessage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public non-sealed class ReportsCSVController extends CSVController {
    private final ReportContainer<ReportMessage, Integer> reports;

    public ReportsCSVController(ReportContainer<ReportMessage, Integer> reports){
        super("Reports.csv");
        this.reports = reports;
    }

    @Override
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath.getFilePath(), false));
        for(ReportMessage message : reports.keySet()){
            String lineToWrite = message.getSender().getUsername() + "," + message.getReportedUser().getUsername() +
                    "," + message.getContents() + "," + message.getMessageID() + "," + message.getFormattedDateTime() +
                    "," + String.valueOf(message.isResolved()).toUpperCase();
            writer.write(lineToWrite + "\n");
        }
        writer.close();
    }
}
