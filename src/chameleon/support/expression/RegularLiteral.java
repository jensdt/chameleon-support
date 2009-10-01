package chameleon.support.expression;

import chameleon.core.expression.InvocationTarget;
import chameleon.core.lookup.LookupException;
import chameleon.core.type.TypeReference;

/**
 * @author Marko van Dooren
 */
public class RegularLiteral extends LiteralWithTypeReference<RegularLiteral> {

  public RegularLiteral(TypeReference type, String value) {
    super(value);
    setTypeReference(type);
  }

  public boolean superOf(InvocationTarget target) throws LookupException {
    return (target instanceof RegularLiteral) && 
           (getValue().equals(((RegularLiteral)target).getValue())) &&
    	   (getType().equals(((RegularLiteral)target).getType()));
    
  }

  public RegularLiteral clone() {
    return new RegularLiteral((TypeReference)getTypeReference().clone(), getValue());
  }


}
