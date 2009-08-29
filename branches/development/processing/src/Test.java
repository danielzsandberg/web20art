import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Twitter client = new Twitter();
		List<Status> newStatuses = client.search("Snow Leopard");
		
		for(int i = 0; i < newStatuses.size(); i++)
		{
			System.out.println(i + ". " + newStatuses.get(i));
		}
	}

}
