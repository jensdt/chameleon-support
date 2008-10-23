package chameleon.support.modifier;

import org.rejuse.property.PropertyMutex;
import org.rejuse.property.PropertyUniverse;

import chameleon.core.MetamodelException;
import chameleon.core.accessibility.AccessibilityDomain;
import chameleon.core.accessibility.AccessibilityProperty;
import chameleon.core.compilationunit.CompilationUnitDomain;
import chameleon.core.element.Element;
import chameleon.core.type.Type;

public class PrivateProperty extends AccessibilityProperty {
	
	public final static String ID = "accessibility.private";
	
	public PrivateProperty(PropertyUniverse<Element> universe, PropertyMutex<Element> family) {
		this(ID, universe, family);
  }
	public PrivateProperty(String name, PropertyUniverse<Element> universe, PropertyMutex<Element> family) {
		super(name, universe, family);
	}

	public AccessibilityDomain accessibilityDomain(Element element) throws MetamodelException {
		try {
			return new CompilationUnitDomain(((Type)element).getTopLevelType());
		} catch (ClassCastException exc) {
			throw new MetamodelException();
		}
	}

}
