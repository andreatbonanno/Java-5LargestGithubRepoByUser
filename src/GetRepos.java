import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

public class GetRepos {

	public static void main(String[] args){
		RepositoryService service = new RepositoryService();
		try {
			List<Repository> repos = service.getRepositories(args[0]);
			
			// sort list by descending size
			Collections.sort(repos, new RepositoryComparator());
			
			// get only top 5 repos
			for(int i = 0; i < Math.min(5, repos.size()); i++) {
				System.out.println("Name: " + repos.get(i).getName() + ",  size: " + repos.get(i).getSize());
			}
		} catch (IOException e) {
			System.err.println("User not valid");
		}
	}
	
	private static class RepositoryComparator implements Comparator<Repository> {
		
	    @Override
	    public int compare(Repository a, Repository b) {
	        return b.getSize() - a.getSize();
	    }
	};
	
}
