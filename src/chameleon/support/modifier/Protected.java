package chameleon.support.modifier;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.modifier.ModifierImpl;

/**
 * @author Marko van Dooren
 */
public class Protected extends ModifierImpl<Protected, Element> {

	
	
  public Protected() {
    
  }
  
	@Override
	public Protected clone() {
		return new Protected();
	}

	public PropertySet<Element> impliedProperties() {
		return createSet(language().property(ProtectedProperty.ID));
	}


}
