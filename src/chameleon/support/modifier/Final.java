package chameleon.support.modifier;

import java.util.Set;

import org.rejuse.property.Property;
import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.modifier.ModifierContainer;
import chameleon.core.modifier.ModifierImpl;


/**
 * @author Marko van Dooren
 */
public class Final extends ModifierImpl<Final, ModifierContainer> {

  public Final() {
  }

	@Override
	public Final clone() {
		return new Final();
	}

 /*@
   @ behavior
   @
   @ post \result.contains(language().OVERRIDABLE.inverse());
   @ post \result.contains(language().DEFINED);
   @ post \result.size() == 2;
   @*/
  public PropertySet<Element> impliedProperties() {
    return createSet(language().OVERRIDABLE.inverse(),language().DEFINED);
  }

}
