package chameleon.support.modifier;

import org.rejuse.property.PropertyUniverse;
import org.rejuse.property.StaticProperty;

import chameleon.core.element.Element;
import chameleon.core.property.ChameleonProperty;
import chameleon.core.scope.Scope;

public class Accessible extends StaticProperty<Element,ChameleonProperty>{

	
	public Accessible(String name, PropertyUniverse universe) {
		super("accessible", universe);
	}
	
	public Scope accessibilityDomain() {
		return _accessibilityDomain;
	}
	
	private Scope _accessibilityDomain;

}
