package chameleon.support.modifier;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.modifier.ModifierContainer;
import chameleon.core.modifier.ModifierImpl;

/**
 * @author Marko van Dooren
 */
public class Private extends ModifierImpl<Private, ModifierContainer> {

  public Private() {
    
  }
  
//  public boolean atLeastAsVisibleAs(AccessModifier other) {
//    return (other instanceof Private);
//  }
//
//  public AccessibilityDomain getAccessibilityDomain(Type type) {
//    return new CompilationUnitDomain(type.getTopLevelType());
//  }

	@Override
	public Private clone() {
		return new Private();
	}

	/**
	 * A private element has a private scope, and is not inheritable.
	 */
  public PropertySet<Element> impliedProperties() {
	  return createSet(language().property(PrivateProperty.ID), language().INHERITABLE.inverse());
  }

}
