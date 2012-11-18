package cc.alessandro.jpocket.filters;

import org.joda.time.DateTime;

import cc.alessandro.jpocket.State;
import cc.alessandro.jpocket.Tag;

public interface ParameterBuilder {

	ParameterBuilder withState(State state);

	ParameterBuilder wasFavorited();

	ParameterBuilder withTags(Tag... tags);

	ParameterBuilder sortedBy(Sort option);

	ParameterBuilder withDetailType(DetailType detail);

	ParameterBuilder withTitleOrUrlText(String search);

	ParameterBuilder ofDomain(String domain);

	ParameterBuilder since(DateTime date);

	ParameterBuilder count();	
}