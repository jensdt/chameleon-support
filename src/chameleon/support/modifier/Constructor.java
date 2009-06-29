/*
 * Created on Aug 27, 2004
 */
package chameleon.support.modifier;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.modifier.ModifierContainer;
import chameleon.core.modifier.ModifierImpl;

/**
 * A class of modifiers marking a method as being a constructor.
 * 
 * @author Marko van Dooren
 */
public class Constructor extends ModifierImpl<Constructor,ModifierContainer> {
	
	  public Constructor() {
		  
	  }

		@Override
		public Constructor clone() {
			return new Constructor();
		}

    public PropertySet<Element> impliedProperties() {
      return createSet(language().CONSTRUCTOR);
    }

}
