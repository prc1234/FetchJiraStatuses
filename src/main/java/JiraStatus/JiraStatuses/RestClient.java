package JiraStatus.JiraStatuses;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;


public class RestClient {


		public static void main(String[] args) {
			try {
				
				String str = RestClient.fetchJiraDetails("status");
				System.out.println("The string is "+str);
			} catch (Exception e) {

				e.printStackTrace();

			}

		}
		
		public static String fetchJiraDetails(String query) throws ParseException {

			Client client = Client.create();			
			client.addFilter(new HTTPBasicAuthFilter("pujaranichand", "tsting"));
			WebResource webResource = client.resource("https://localhost:8080/rest/api/2/issue/TEST-35?fields="+query);			
			ClientResponse response = webResource.type("application/json").get(ClientResponse.class);	
			String output = response.getEntity(String.class);
			String value = RestClient.readJson(output, query).toString();
			return value;
		}
		
		
		public static Object readJson(String output, String val ) throws ParseException {
			Object value = null;
			  JSONParser parser = new JSONParser();
			  Object object = (JSONObject)parser.parse(output);
			  JSONObject jsonObject = (JSONObject)object;	  
			  JSONObject jsonObj2 = (JSONObject) jsonObject.get("fields");	
			  JSONObject jsonObj3 = (JSONObject) jsonObj2.get(val);
			  if(val.equals("assignee")) {
				  	value=jsonObj3.get("displayName");
			  }
			  else if(val.equals("status")) {
				  	value=jsonObj3.get("name");
		}  
			  return value;
	}
}

