package cc.alessandro.jpocket.filters;

import java.util.List;

import org.joda.time.DateTime;

import cc.alessandro.jpocket.ContentType;
import cc.alessandro.jpocket.State;
import cc.alessandro.jpocket.Tag;
import cc.alessandro.jpocket.session.Request.Type;

public interface Parameter {

	State getState();

	boolean isfavorite();

	List<Tag> getTags();

	ContentType getContentType();

	Sort getSort();

	DetailType getDetailType();

	String getSearch();

	String getDomain();

	DateTime getSince();

	Integer getCount();

	Integer getOffSet();
	
	List<Type> asType();

}
