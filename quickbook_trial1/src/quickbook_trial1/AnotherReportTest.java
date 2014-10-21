package quickbook_trial1;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.Report;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.IAuthorizer;
import com.intuit.ipp.security.OAuthAuthorizer;
import com.intuit.ipp.services.ReportName;
import com.intuit.ipp.services.ReportService;

public class AnotherReportTest {

    public static void main(String[] args) {
        try {
            //IAuthorizer authorizer = new OAuthAuthorizer("consumerkey", "consumersecret", "accesstoken", "access-token-secret");
            IAuthorizer authorizer = new OAuthAuthorizer(
                    "qyprd0QnIHIOmWKQFripUvBCxICItc",
                    "klRkk2CIWRNZg2Ip63FkIbNgCoDWfgCb01BZakPW",
                    "qyprdr1PtMB8GkuTkpGzGcyjWMmswcpN1MimytkH1Y6uXO9L",
                    "4jKNAyzazA1xu9nFpTzN9OPlKkVCpX6JcWsVGUkb");

            Context context;
            try {
                context = new Context(authorizer, ServiceType.QBO, /* company id */"1292681785");
            } catch (FMSException e) {
                throw new RuntimeException("Could not initialize Intuit context object", e);
            }

            ReportService reportService = new ReportService(context);
            reportService.setStart_date("2014-01-01");
            reportService.setEnd_date("2014-10-01");
            reportService.setAccounting_method("Accrual");
            Report report = reportService.executeReport(ReportName.PROFITANDLOSS.toString());
            System.out.println("Success - row count: " + report.getRows().getRow().size());
        } catch (FMSException e) {
            e.printStackTrace();
        }

    }
}