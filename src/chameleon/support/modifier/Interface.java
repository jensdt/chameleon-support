package chameleon.support.modifier;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.language.ObjectOrientedLanguage;
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
    public PropertySet<Element> impliedProperties() {
      return createSet(language(ObjectOrientedLanguage.class).DEFINED.inverse());
    }
}
