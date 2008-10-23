package chameleon.support.modifier;

import org.rejuse.property.Property;
import org.rejuse.property.PropertyUniverse;
import org.rejuse.property.StaticProperty;

import chameleon.core.accessibility.AccessibilityDomain;
import chameleon.core.element.Element;

public class Accessible extends StaticProperty<Element>{

	
	public Accessible(String name, PropertyUniverse universe) {
		super("accessible", universe);
	}
	
	public AccessibilityDomain accessibilityDomain() {
		return _accessibilityDomain;
	}
	
	private AccessibilityDomain _accessibilityDomain;

}
