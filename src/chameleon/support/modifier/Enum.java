package chameleon.support.modifier;

import java.util.Set;

import org.rejuse.property.Property;

import chameleon.core.element.Element;
import chameleon.core.modifier.ModifierContainer;
import chameleon.core.modifier.ModifierImpl;

public class Enum extends ModifierImpl<Enum, ModifierContainer> {
	
	  public Enum() {
		  
	  }

		@Override
		public Enum clone() {
			return new Enum();
		}

    public Set<Property<Element>> impliedProperties() {
      return createSet(language().EXTENSIBLE);
    }
}
