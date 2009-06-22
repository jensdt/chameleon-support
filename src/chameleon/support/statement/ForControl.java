package chameleon.support.statement;

import chameleon.core.declaration.DeclarationContainer;
import chameleon.core.type.TypeDescendantImpl;

public abstract class ForControl<E extends ForControl> extends TypeDescendantImpl<E, ForStatement> implements DeclarationContainer<E, ForStatement>{
	
	public abstract E clone();


}
