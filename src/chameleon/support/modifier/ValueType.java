package chameleon.support.modifier;

import java.util.Set;

import org.rejuse.property.Property;

import chameleon.core.element.Element;
import chameleon.core.modifier.ModifierContainer;
import chameleon.core.modifier.ModifierImpl;

/**
 * 
 * @author Marko van Dooren
 */
public class ValueType extends ModifierImpl<ValueType, ModifierContainer> {
	
	public ValueType() {
		
	}

	@Override
	public ValueType clone() {
		return new ValueType();
	}

  public Set<Property<Element>> impliedProperties() {
    return createSet(language().VALUE_TYPE);
  }
	
}

