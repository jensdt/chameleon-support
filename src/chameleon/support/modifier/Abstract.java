package chameleon.support.modifier;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.language.ObjectOrientedLanguage;
import chameleon.core.modifier.ModifierImpl;


/**
 * @author Marko van Dooren
 */
public class Abstract extends ModifierImpl<Abstract,Element> {

  public Abstract() {
    
  }

	@Override
	public Abstract clone() {
		return new Abstract();
	}

 /*@
   @ behavior
   @
   @ post \result.contains(language().OVERRIDABLE);
   @ post \result.contains(language().DEFINED.inverse());
   @ post \result.size() == 2;
   @*/
  public PropertySet<Element> impliedProperties() {
    return createSet(language(ObjectOrientedLanguage.class).OVERRIDABLE,language(ObjectOrientedLanguage.class).DEFINED.inverse());
  }
  
}
