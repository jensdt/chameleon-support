package chameleon.support.modifier;

import org.rejuse.property.PropertyMutex;
import org.rejuse.property.PropertyUniverse;

import chameleon.core.element.Element;
import chameleon.core.lookup.LookupException;
import chameleon.core.scope.LexicalScope;
import chameleon.core.scope.Scope;
import chameleon.core.scope.ScopeProperty;
import chameleon.core.type.Type;

public class PrivateProperty extends ScopeProperty {
	
	public final static String ID = "accessibility.private";
	
	public PrivateProperty(PropertyUniverse<Element> universe, PropertyMutex<Element> family) {
		this(ID, universe, family);
  }
	public PrivateProperty(String name, PropertyUniverse<Element> universe, PropertyMutex<Element> family) {
		super(name, universe, family);
	}

	public Scope scope(Element element) throws LookupException {
		try {
			return new LexicalScope(((Type)element.nearestAncestor(Type.class)).getTopLevelType());
		} catch (ClassCastException exc) {
			throw new LookupException("Private property does not support elements that are no TypeDescendant.");
		}
	}

}
