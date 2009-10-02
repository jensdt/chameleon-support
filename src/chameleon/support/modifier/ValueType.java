package chameleon.support.modifier;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.language.ObjectOrientedLanguage;
import chameleon.core.modifier.ModifierImpl;
import chameleon.core.validation.VerificationResult;

/**
 * 
 * @author Marko van Dooren
 */
public class ValueType extends ModifierImpl<ValueType, Element> {
	
	public ValueType() {
		
	}

	@Override
	public ValueType clone() {
		return new ValueType();
	}

  public PropertySet<Element> impliedProperties() {
    return createSet(language(ObjectOrientedLanguage.class).VALUE_TYPE);
  }

}

