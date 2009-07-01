package chameleon.support.modifier;

import org.rejuse.property.PropertyMutex;
import org.rejuse.property.PropertyUniverse;

import chameleon.core.MetamodelException;
import chameleon.core.element.Element;
import chameleon.core.lookup.LookupException;
import chameleon.core.namespace.NamespaceElement;
import chameleon.core.namespace.NamespaceScope;
import chameleon.core.scope.Scope;
import chameleon.core.scope.ScopeProperty;
import chameleon.core.type.Type;
import chameleon.support.property.accessibility.HierarchyScope;

public class ProtectedProperty extends ScopeProperty {
	
	public final static String ID = "accessibility.protected";
	
	public ProtectedProperty(PropertyUniverse<Element> universe, PropertyMutex<Element> family) {
		super(ID, universe, family);
	}

	public ProtectedProperty(String name, PropertyUniverse<Element> universe, PropertyMutex<Element> family) {
		super(name, universe, family);
	}

	public Scope scope(Element element) throws MetamodelException {
		try {
			return new HierarchyScope((Type) element).union(new NamespaceScope(((NamespaceElement) element).getNamespace()));
		} catch (ClassCastException exc) {
			throw new MetamodelException("The given element is of the wrong type.");
		}
	}
}