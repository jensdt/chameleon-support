package chameleon.support.modifier;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.language.ObjectOrientedLanguage;
import chameleon.core.modifier.ModifierImpl;
import chameleon.core.property.ChameleonProperty;

public class Interface extends ModifierImpl<Interface, Element> {
	
	  public Interface() {
		  
	  }

		@Override
		public Interface clone() {
			return new Interface();
		}

		/**
		 * An interface is abstract, thus not defined.
		 */
    public PropertySet<Element,ChameleonProperty> impliedProperties() {
      return createSet(language(ObjectOrientedLanguage.class).DEFINED.inverse());
    }
}
