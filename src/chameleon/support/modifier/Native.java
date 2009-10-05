package chameleon.support.modifier;



import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.language.ObjectOrientedLanguage;
import chameleon.core.modifier.ModifierImpl;
import chameleon.core.property.ChameleonProperty;

/**
 * @author Marko van Dooren
 */
public class Native extends ModifierImpl<Native, Element> {

  public Native() {
    
  }

	@Override
	public Native clone() {
		return new Native();
	}
	
 /*@
   @ behavior
   @
   @ post \result.contains(language(ObjectOrientedLanguage.class).OVERRIDABLE);
   @ post \result.contains(language(ObjectOrientedLanguage.class).DEFINED);
   @ post \result.size() == 2;
   @*/
  public PropertySet<Element,ChameleonProperty> impliedProperties() {
    return createSet(language(ObjectOrientedLanguage.class).DEFINED);
  }

}
