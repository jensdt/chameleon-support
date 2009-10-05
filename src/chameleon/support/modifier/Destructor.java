package chameleon.support.modifier;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.language.ObjectOrientedLanguage;
import chameleon.core.modifier.ModifierImpl;
import chameleon.core.property.ChameleonProperty;

/**
 * @author Tim Laeremans
 * @author Marko van Dooren
 */
public class Destructor extends ModifierImpl<Destructor,Element> {

	public Destructor() {
		
	}

	@Override
	public Destructor clone() {
		return new Destructor();
	}

  public PropertySet<Element,ChameleonProperty> impliedProperties() {
    return createSet(language(ObjectOrientedLanguage.class).DESTRUCTOR);
  }
}
