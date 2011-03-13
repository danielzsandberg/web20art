package Repository;

public class WinterwellRepositoryFactory implements ITweetRepositoryFactory 
{

	public ITweetRepository getTweetRepository() 
	{
		return new WinterwellTweetRepository();
	}

}
