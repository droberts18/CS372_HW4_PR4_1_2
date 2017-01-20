import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebSpider {
	private HashMap<String, Boolean> sites = new HashMap<String, Boolean>();
	
	/**
	 * Creates the web spider, starts at ESPN.com
	 */
	public void initialize(){
		try {
			URL url = new URL("http://www.espn.com/");
			sites.put(url.toString(), false);

			while(checkIfFinished() == false){
				search();
			}
		}
		catch (Exception ex){
			System.out.printf("Oops: %s", ex.getMessage());
		}
	}
	
	/**
	 * Checks to see if the web spider has searched 100 sites
	 * @return	If the web spider has searched 100 sites
	 */
	private Boolean checkIfFinished(){
		int numSearched = 0;
		for(Boolean b : sites.values()){
			if(b == true)
				numSearched++;
		}
		
		if(numSearched < 100){
			return false;
		}
		return true;
	}
	
	/**
	 * The main function that the web spider uses to search each web site
	 */
	public void search(){
		try{
			for(Map.Entry<String, Boolean> entry : sites.entrySet()){
				if(entry.getValue() == false || entry.getKey() != null){
					HttpURLConnection conn = (HttpURLConnection)new URL(entry.getKey()).openConnection();
					conn.setRequestMethod("POST");
					 
					entry.setValue(true);
					
					BufferedReader rdr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line = rdr.readLine();
					
					while(line != null){
						Pattern website = Pattern.compile("<a\\s*?href=\"(http:.*?)\"");
						Matcher matcher = website.matcher(line);
						
						if(matcher.find()){
							System.out.printf("Found site: %s\n", matcher.group(1));
							sites.put(matcher.group(1), false);
						}
						line = rdr.readLine();
					}
					if(checkIfFinished() == true){
						System.out.println("finished");
						return;
					}
				}
			}
		}
		catch (Exception ex){
			System.out.printf("Oops #2: %s\n", ex.getMessage());
		}
	}
	
	/**
	 * Initializes the web spider
	 */
	public WebSpider(){
		initialize();
	}
	
	/**
	 * Main that makes the web spider
	 * @param args	Standard
	 */
	public static void main(String[] args) {
		WebSpider ws = new WebSpider();
	}

}
