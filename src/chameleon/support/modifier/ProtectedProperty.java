package chameleon.support.modifier;

import org.rejuse.property.PropertyMutex;
import org.rejuse.property.PropertyUniverse;

import chameleon.core.MetamodelException;
import chameleon.core.accessibility.AccessibilityDomain;
import chameleon.core.accessibility.AccessibilityProperty;
import chameleon.core.element.Element;
import chameleon.core.namespace.NamespaceDomain;
import chameleon.core.namespace.NamespaceElement;
import chameleon.core.type.Type;
import chameleon.support.property.accessibility.HierarchyDomain;

public class ProtectedProperty extends AccessibilityProperty {
	
	public final static String ID = "accessibility.protected";
	
	public ProtectedProperty(PropertyUniverse<Element> universe, PropertyMutex<Element> family) {
		super(ID, universe, family);
	}

	public ProtectedProperty(String name, PropertyUniverse<Element> universe, PropertyMutex<Element> family) {
		super(name, universe, family);
	}

	public AccessibilityDomain accessibilityDomain(Element element) throws MetamodelException {
		try {
			return new HierarchyDomain((Type) element).union(new NamespaceDomain(((NamespaceElement) element).getNamespace()));
		} catch (ClassCastException exc) {
			throw new MetamodelException();
		}
	}
}