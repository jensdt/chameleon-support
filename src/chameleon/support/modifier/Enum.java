package chameleon.support.modifier;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.modifier.ModifierImpl;
import chameleon.core.property.ChameleonProperty;
import chameleon.oo.language.ObjectOrientedLanguage;

public class Enum extends ModifierImpl<Enum> {
	
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
