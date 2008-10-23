package chameleon.support.modifier;

import org.rejuse.property.PropertyMutex;
import org.rejuse.property.PropertyUniverse;

import chameleon.core.MetamodelException;
import chameleon.core.accessibility.AccessibilityDomain;
import chameleon.core.accessibility.AccessibilityProperty;
import chameleon.core.element.Element;
import chameleon.support.property.accessibility.All;

public class PublicProperty extends AccessibilityProperty {
	
	public final static String ID = "accessibility.public";
	
	public PublicProperty(PropertyUniverse<Element> universe, PropertyMutex<Element> family) {
		super(ID, universe, family);
	}

	public PublicProperty(String name, PropertyUniverse<Element> universe, PropertyMutex<Element> family) {
		super(name, universe, family);
	}

	public AccessibilityDomain accessibilityDomain(Element element) throws MetamodelException {
		try {
			return new All();
		} catch (ClassCastException exc) {
			throw new MetamodelException();
		}
	}
}