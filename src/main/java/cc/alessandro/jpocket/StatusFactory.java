package cc.alessandro.jpocket;

import java.util.Collections;
import java.util.List;

import cc.alessandro.jpocket.session.Response;

public class StatusFactory {
	
	public static List<Status> parser(Response response) {
		if (response.getJson() != null)
		{
			
		}
		return Collections.<Status>emptyList();
	}
}
