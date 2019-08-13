package Structural;

import java.util.ArrayList;
import java.util.Arrays;

public class Proxy_Posts {
	public static void main (String [] args) {
		Proxy gateway = new Proxy(new PostsServer());
		
		System.out.println(gateway.getPost(0));
		System.out.println(gateway.getPost(1));
		System.out.println(gateway.getPost(2));
		System.out.println(gateway.getPost(2));
		System.out.println(gateway.getPost(3));
		System.out.println(gateway.getPost(1));
		System.out.println(gateway.getPost(3));
	}

}


interface IPostsServer {
	public String getPost(int id);
}

class PostsServer implements IPostsServer {

	public String getPost(int id) {
		try {
			// Grabbing the post from the database
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Lorem Ipsum " + id;
	}
}

class Proxy implements IPostsServer {
	ArrayList<String> posts; 
	IPostsServer realPostServer ;
	public Proxy(PostsServer ps) {
		this.realPostServer = ps;
		String []x = {"","","","","",""};
		posts = new ArrayList<String>(Arrays.asList(x));
	}
	public String getPost(int id) {
		String postFromCache = posts.get(id);
		if (postFromCache.isEmpty()) {
			String postFromRealSubject = realPostServer.getPost(id);
			posts.set(id, postFromRealSubject);
			System.out.print("got from db: ");
			return postFromRealSubject;				
		}
		else {
			System.out.print("got from cache: ");
			return postFromCache;
		}			
	}
	
}