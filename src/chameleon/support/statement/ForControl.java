package chameleon.support.statement;

import chameleon.core.statement.StatementContainer;
import chameleon.core.type.TypeDescendantImpl;

public abstract class ForControl<E extends ForControl> extends TypeDescendantImpl<E, ForStatement> {
	
	public abstract E clone();

}
