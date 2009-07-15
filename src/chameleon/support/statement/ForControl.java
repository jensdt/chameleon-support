package chameleon.support.statement;

import java.util.List;

import chameleon.core.declaration.DeclarationContainer;
import chameleon.core.lookup.LookupException;
import chameleon.core.namespacepart.NamespaceElementImpl;
import chameleon.core.variable.Variable;

public abstract class ForControl<E extends ForControl> extends NamespaceElementImpl<E, ForStatement> implements DeclarationContainer<E, ForStatement>{
	
	public abstract List<? extends Variable> declarations() throws LookupException;
	
	public abstract E clone();


}
