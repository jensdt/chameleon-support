package chameleon.support.modifier;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.language.ObjectOrientedLanguage;
import chameleon.core.modifier.ModifierImpl;
import chameleon.core.property.ChameleonProperty;

/**
 * @author Marko van Dooren
 */
public class Private extends ModifierImpl<Private, Element> {

  public Private() {
    
  }
  
	@Override
	public Private clone() {
		return new Private();
	}

	/**
	 * A private element has a private scope, and is not inheritable.
	 */
  public PropertySet<Element,ChameleonProperty> impliedProperties() {
	  return createSet(language().property(PrivateProperty.ID), language(ObjectOrientedLanguage.class).INHERITABLE.inverse());
  }

}
