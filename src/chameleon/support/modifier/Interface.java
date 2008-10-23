package chameleon.support.modifier;

import java.util.HashSet;
import java.util.Set;

import org.rejuse.property.Property;

import chameleon.core.element.Element;
import chameleon.core.modifier.ModifierContainer;
import chameleon.core.modifier.ModifierImpl;

public class Interface extends ModifierImpl<Interface, ModifierContainer> {
	
	  public Interface() {
		  
	  }

		@Override
		public Interface clone() {
			return new Interface();
		}

		/**
		 * An interface is abstract, thus not defined.
		 */
    public Set<Property<Element>> impliedProperties() {
      return createSet(language().DEFINED.inverse());
    }
}
