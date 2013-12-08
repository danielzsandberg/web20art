package Repository;

import winterwell.jtwitter.OAuthSignpostClient;
import winterwell.jtwitter.Twitter;

public class WinterwellRepositoryFactory implements ITweetRepositoryFactory 
{	
	private Twitter twitterClient;
	public WinterwellRepositoryFactory()
	{
		OAuthSignpostClient client = new OAuthSignpostClient("ji8shrkoi7G0WbHNXCaQ", "GDV2piAWC6iFdRGRWgHcARc6VKJTBzCUxpmQS9oNpA", "14600980-hAgsYHbVJHtd5aZv9MtShoOpkrSIRs7b2MeH3Ghcr", "tNFUZDBfC0QuEZ4JyrA5oSVcCmYG2uib2ismjK1XdcclX");
        twitterClient = new Twitter("danielsandberg", client);
	}
	public ITweetRepository getTweetRepository() 
	{
		return new WinterwellTweetRepository(twitterClient);
	}

}
