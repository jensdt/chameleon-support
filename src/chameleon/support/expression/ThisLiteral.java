package chameleon.support.expression;

import chameleon.core.expression.InvocationTarget;
import chameleon.core.lookup.LookupException;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;

/**
 * @author Marko van Dooren
 * @author Tim Laeremans
 */
public class ThisLiteral extends LiteralWithTypeReference<ThisLiteral> {

  public ThisLiteral() {
    super("this");
  }
  
  public ThisLiteral(TypeReference ref) {
    super("this", ref);
  }

  public Type getType() throws LookupException {
    if (getTypeReference() == null) {
      return nearestAncestor(Type.class);
    }
    else {
      return getTypeReference().getType();
    }
  }

  public boolean superOf(InvocationTarget target) throws LookupException {
    return (target instanceof ThisLiteral) && ((ThisLiteral)target).getType().assignableTo(getType());
  }

  public ThisLiteral clone() {
    ThisLiteral result = new ThisLiteral();
    TypeReference tref = getTypeReference();
    if(tref != null) {
      result.setTypeReference(tref.clone());
    }
    return result;
  }

}
