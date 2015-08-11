package Repository;

import java.util.List;

import mainPackage.Tweet;

public interface ITweetRepository 
{
	List<Tweet> getBySearchTerm(String searchTerm);
}
