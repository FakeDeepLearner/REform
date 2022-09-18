package useCases.messageUseCases;
import entities.ReportMessage;

public class OpenAndCloseReports {

    public void openReport(ReportMessage reportMessage){
        reportMessage.open();
    }

    public void closeReport(ReportMessage reportMessage){
        reportMessage.close();
    }

}
