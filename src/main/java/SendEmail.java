
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.SSLSession;



public class SendEmail {
	
	
	public static void sendMail(){
		SendEmail sendEmail = new SendEmail();
    	final String user="unifieduser@apttus.com";//change accordingly 
    	final String password="Y9!tywatJu2J";
    	String host = "smtp.office365.com";	
    
    	// Get system properties
    	Properties props = new Properties();
  	    props.put("mail.smtp.auth", "true");
  	    props.put("mail.smtp.starttls.enable", "true");
  	    props.put("mail.smtp.host", host);
  	    props.put("mail.smtp.port", "587");
  	    props.put("mail.store.protocol", "SMTP");
  	    Session session = Session.getInstance(props,
  	  	      new javax.mail.Authenticator() {
  	  	        @Override
  	  	        protected PasswordAuthentication getPasswordAuthentication() {
  	  	            return new PasswordAuthentication(user, new String(password));
  	  	        }
  	  	      });
  	  	    session.setDebug(true);
  	  	    
  	  	  try {
  	           // Create a default MimeMessage object.
  	           MimeMessage message = new MimeMessage(session);
  	         message.setRecipients(Message.RecipientType.TO, "pchand@apttus.com");

  	           // Set From: header field of the header.
  	           message.setFrom(new InternetAddress(user));
  	           String messagehtml =
  	           		"<html>\r\n" + 
  	           		"<head>\r\n" + 
  	           		"<script>\r\n" + 
  	           		"window.onload = function () {\r\n" + 
  	           		"\r\n" + 
  	           		"//Better to construct options first and then pass it as a parameter\r\n" + 
  	           		"var options = {\r\n" + 
  	           		"	title:{\r\n" + 
  	           		"		text: \"Test Run Trend\"   \r\n" + 
  	           		"	},\r\n" + 
  	           		"	axisY:{\r\n" + 
  	           		"		title:\"Total tests\"\r\n" + 
  	           		"	},\r\n" + 
  	           		"	toolTip: {\r\n" + 
  	           		"		shared: true,\r\n" + 
  	           		"		reversed: true\r\n" + 
  	           		"	},\r\n" + 
  	           		"	data: [{\r\n" + 
  	           		"		type: \"stackedColumn\",\r\n" + 
  	           		"		name: \"Skip\",\r\n" + 
  	           		"		showInLegend: \"true\",\r\n" + 
  	           		"		yValueFormatString: \"#TestCases\",\r\n" + 
  	           		"		dataPoints: [\r\n" + 
  	           		"			{ y: 100 , label: \"Config\" },\r\n" + 
  	           		"			\r\n" + 
  	           		"		]\r\n" + 
  	           		"	},\r\n" + 
  	           		"	{\r\n" + 
  	           		"		type: \"stackedColumn\",\r\n" + 
  	           		"		name: \"Fail\",\r\n" + 
  	           		"		showInLegend: \"true\",\r\n" + 
  	           		"		yValueFormatString: \"#TestCases\",\r\n" + 
  	           		"		dataPoints: [\r\n" + 
  	           		"			{ y: 200 , label: \"Config\" },\r\n" + 
  	           		"					]\r\n" + 
  	           		"	},\r\n" + 
  	           		"	{\r\n" + 
  	           		"		type: \"stackedColumn\",\r\n" + 
  	           		"		name: \"Pass\",\r\n" + 
  	           		"		showInLegend: \"true\",\r\n" + 
  	           		"		yValueFormatString: \"#TestCases\",\r\n" + 
  	           		"		dataPoints: [\r\n" + 
  	           		"			{ y: 500 , label: \"Config\" },\r\n" + 
  	           		"					]\r\n" + 
  	           		"	}]\r\n" + 
  	           		"};\r\n" + 
  	           		"\r\n" + 
  	           		"$(\"#chartContainer\").CanvasJSChart(options);\r\n" + 
  	           		"}\r\n" + 
  	           		"</script>\r\n" + 
  	           		"</head>\r\n" + 
  	           		"<body>\r\n" + 
  	           		"<div id=\"chartContainer\" style=\"height: 300px; width: 70%;\"></div>\r\n" + 
  	           		"<script type=\"text/javascript\" src=\"https://canvasjs.com/assets/script/jquery-1.11.1.min.js\"></script>\r\n" + 
  	           		"<script type=\"text/javascript\" src=\"https://canvasjs.com/assets/script/jquery.canvasjs.min.js\"></script>\r\n" + 
  	           		"</body>\r\n" + 
  	           		"</html>";
  	           Multipart multipart = new MimeMultipart();
  	         	
  	           MimeBodyPart htmlPart = new MimeBodyPart();
  	           htmlPart.setContent(messagehtml, "text/html");
  	           multipart.addBodyPart(htmlPart);
	     	    message.setContent(multipart);
  	           Transport.send(message);
  	  	  }
  	  	  catch(Exception e) {
  	  		  
  	  	  }
    	
	}
	
	public static void main(String[] args) {
		SendEmail.sendMail();
	}
	
	
}