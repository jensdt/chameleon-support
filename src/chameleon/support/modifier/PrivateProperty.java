package chameleon.support.modifier;

import org.rejuse.property.PropertyMutex;
import org.rejuse.property.PropertyUniverse;

import chameleon.core.MetamodelException;
import chameleon.core.compilationunit.CompilationUnitScope;
import chameleon.core.element.Element;
import chameleon.core.scope.ScopeProperty;
import chameleon.core.scope.Scope;
import chameleon.core.type.Type;

public class PrivateProperty extends ScopeProperty {
	
	public final static String ID = "accessibility.private";
	
	public PrivateProperty(PropertyUniverse<Element> universe, PropertyMutex<Element> family) {
		this(ID, universe, family);
  }
	public PrivateProperty(String name, PropertyUniverse<Element> universe, PropertyMutex<Element> family) {
		super(name, universe, family);
	}

	public Scope scope(Element element) throws MetamodelException {
		try {
			return new CompilationUnitScope(((Type)element).getTopLevelType());
		} catch (ClassCastException exc) {
			throw new MetamodelException();
		}
	}

}
