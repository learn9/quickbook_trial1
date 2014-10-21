package quickbook_trial1;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.security.OAuthAuthorizer;
import com.intuit.ipp.services.DataService;

public class Trial2 {

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
				
				DataService service = new DataService(context);
				
				Customer customer=new Customer();
				customer.setId("1");
				//customer.setDisplayName("Mary");
				
				//Customer resultCustomer = service.add(customer);
				
				Customer resultCustomer = service.findById(customer);
				
				System.out.println(resultCustomer);
				System.out.println(resultCustomer.getFullyQualifiedName());
	}
}
