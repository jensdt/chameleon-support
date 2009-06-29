package chameleon.support.statement;

import java.util.ArrayList;
import java.util.List;

import chameleon.core.element.Element;

public class DefaultGotoStatement extends GotoStatement<DefaultGotoStatement> {

	@Override
	public DefaultGotoStatement clone() {
		return new DefaultGotoStatement();
	}

	@Override
	public List<Element> children() {
		return new ArrayList<Element>();
	}
}
