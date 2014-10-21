package quickbook_trial1;

import java.util.List;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.ColData;
import com.intuit.ipp.data.Columns;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.Report;
import com.intuit.ipp.data.ReportHeader;
import com.intuit.ipp.data.Row;
import com.intuit.ipp.data.Rows;
import com.intuit.ipp.security.OAuthAuthorizer;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.ReportName;
import com.intuit.ipp.services.ReportService;

public class TrialReport {
	public static void main(String[] args) throws Exception {
		// following https://developer.intuit.com/docs/0025_quickbooksapi/0055_devkits/0201_ipp_java_devkit_3.0/0001_synchronous_calls/0001_data_service_apis
		String consumerKey = "qyprdjjxjhvmRvpAfaYgHfbVsbz1KH";
		String consumerSecret = "mf8ASlocS1DjsZKmThXdWiHkHao81dAvz1TMWZYR";
		String accessToken = "qyprdZfe92xmLpx1IiOKvIVIb39oGXMChsO34csqoIGyzgWH";
		String accessTokenSecret = "mcSpZrugBMCtBh1hAAFQOhMONsqJTLgk5g1XVPcv";

		OAuthAuthorizer oauth = new OAuthAuthorizer(consumerKey, consumerSecret, accessToken, accessTokenSecret);
		
		String appToken = "1b868c6cb467eb4abeba8bab22a839a6fe0e";
		String companyID = "1291602300"; // need to create a company in quickbook connect first.

		Context context = new Context(oauth, appToken, ServiceType.QBO, companyID);
		
		ReportService rService = new ReportService(context);
		rService.setAccounting_method("Accrual");
		rService.setStart_date("2014-10-01");
		rService.setEnd_date("2014-10-20");
		
		Report report = rService.executeReport(ReportName.CASHFLOW.toString());
		
		
		System.out.println(report.getHeader());
		ReportHeader header = report.getHeader();
		System.out.println("header="+header.getReportName());
		Columns columns = report.getColumns();
		System.out.println("columns="+columns);
		Rows rows = report.getRows();
		System.out.println("rows="+rows);
		List<Row> rowList = rows.getRow();
		Row row0 = rowList.get(0); // first element
//		System.out.println("group=" + row.getGroup());
//		System.out.println("type=" + row.getType());
//		System.out.println("ColData=" + row.getColData());
		
		
		Rows rowsInsideRow0 = row0.getRows();
		List<Row> rowListInsideRow0 = rowsInsideRow0.getRow();
		Row netIncomeRow = rowListInsideRow0.get(0);
		List<ColData> colData = netIncomeRow.getColData();
		System.out.println(colData.get(0).getValue());
		System.out.println(colData.get(1).getValue());
		
		System.out.println("");
	}
}
