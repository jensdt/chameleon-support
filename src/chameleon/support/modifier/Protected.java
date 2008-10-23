package chameleon.support.modifier;

import java.util.Set;

import org.rejuse.property.Property;

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

//  public AccessibilityDomain getAccessibilityDomain(Type type) throws MetamodelException {
//    return new HierarchyDomain(type).union(new NamespaceDomain(type.getNamespace()));
//  }

	@Override
	public Protected clone() {
		return new Protected();
	}

	public Set<Property<Element>> impliedProperties() {
		return createSet(language().property(ProtectedProperty.ID));
	}


}
