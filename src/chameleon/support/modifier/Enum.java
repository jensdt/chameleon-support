package chameleon.support.modifier;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.language.ObjectOrientedLanguage;
import chameleon.core.modifier.ModifierImpl;
import chameleon.core.property.ChameleonProperty;

public class Enum extends ModifierImpl<Enum, Element> {
	
	  public Enum() {
		  
	  }

		@Override
		public Enum clone() {
			return new Enum();
		}

    public PropertySet<Element,ChameleonProperty> impliedProperties() {
      return createSet(language(ObjectOrientedLanguage.class).EXTENSIBLE);
    }
}
