package chameleon.support.modifier;

import org.rejuse.property.PropertyMutex;
import org.rejuse.property.PropertyUniverse;

import chameleon.core.MetamodelException;
import chameleon.core.element.Element;
import chameleon.core.scope.ScopeProperty;
import chameleon.core.scope.Scope;
import chameleon.core.scope.UniversalScope;

public class PublicProperty extends ScopeProperty {
	
	public final static String ID = "accessibility.public";
	
	public PublicProperty(PropertyUniverse<Element> universe, PropertyMutex<Element> family) {
		super(ID, universe, family);
	}

	public PublicProperty(String name, PropertyUniverse<Element> universe, PropertyMutex<Element> family) {
		super(name, universe, family);
	}

	public Scope scope(Element element) throws MetamodelException {
		try {
			return new UniversalScope();
		} catch (ClassCastException exc) {
			throw new MetamodelException();
		}
	}
}