package chameleon.support.modifier;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.language.ObjectOrientedLanguage;
import chameleon.core.modifier.ModifierImpl;

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
  public PropertySet<Element> impliedProperties() {
	  return createSet(language().property(PrivateProperty.ID), language(ObjectOrientedLanguage.class).INHERITABLE.inverse());
  }

}
