package chameleon.support.modifier;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.modifier.ModifierContainer;
import chameleon.core.modifier.ModifierImpl;

/**
 * @author Marko van Dooren
 */
public class Protected extends ModifierImpl<Protected, ModifierContainer> {

	
	
  public Protected() {
    
  }
  
//  public boolean atLeastAsVisibleAs(AccessModifier other) {
//    return ! (other instanceof Public);
//  }

//  public AccessibilityDomain getAccessibilityDomain(Type type) throws LookupException {
//    return new HierarchyDomain(type).union(new NamespaceDomain(type.getNamespace()));
//  }

	@Override
	public Protected clone() {
		return new Protected();
	}

	public PropertySet<Element> impliedProperties() {
		return createSet(language().property(ProtectedProperty.ID));
	}


}
