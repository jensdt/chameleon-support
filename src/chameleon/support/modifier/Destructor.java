package chameleon.support.modifier;

import java.util.Set;

import org.rejuse.property.Property;

import chameleon.core.element.Element;
import chameleon.core.modifier.ModifierContainer;
import chameleon.core.modifier.ModifierImpl;

/**
 * @author Tim Laeremans
 * @author Marko van Dooren
 */
public class Destructor extends ModifierImpl<Destructor,ModifierContainer> {

	public Destructor() {
		
	}

	@Override
	public Destructor clone() {
		return new Destructor();
	}

  public Set<Property<Element>> impliedProperties() {
    return createSet(language().DESTRUCTOR);
  }
}
