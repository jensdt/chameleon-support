package chameleon.support.modifier;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.language.ObjectOrientedLanguage;
import chameleon.core.modifier.ModifierImpl;

/**
 * A class of modifiers marking a method as being a constructor.
 * 
 * @author Marko van Dooren
 */
public class Constructor extends ModifierImpl<Constructor,Element> {
	
	  public Constructor() {
		  
	  }

		@Override
		public Constructor clone() {
			return new Constructor();
		}

		/**
		 * A constructor modifier assigns the language().CONSTRUCTOR property to
		 * an element. Subclasses can add additional properties.
		 */
	 /*@
	   @ public behavior
	   @
	   @ post \result.contains(language().CONSTRUCTOR);
	   @*/
    public PropertySet<Element> impliedProperties() {
      return createSet(language(ObjectOrientedLanguage.class).CONSTRUCTOR);
    }

}
