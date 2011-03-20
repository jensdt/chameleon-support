package chameleon.support.expression;

import chameleon.core.lookup.LookupException;
import chameleon.oo.type.Type;
import chameleon.oo.type.TypeReference;
import chameleon.util.CreationStackTrace;

/**
 * @author Marko van Dooren
 * @author Tim Laeremans
 */
public class ThisLiteral extends LiteralWithTypeReference<ThisLiteral> {

	private CreationStackTrace _trace = new CreationStackTrace();
	
  public ThisLiteral() {
    super("this");
  }
  
  public ThisLiteral(TypeReference ref) {
    super("this", ref);
  }

  protected Type actualType() throws LookupException {
    TypeReference tref = getTypeReference();
		if (tref == null) {
      return nearestAncestor(Type.class);
    }
    else {
      return tref.getType();
    }
  }

  public ThisLiteral clone() {
    ThisLiteral result = new ThisLiteral();
    result.setOrigin(this);
    TypeReference tref = getTypeReference();
    if(tref != null) {
      result.setTypeReference(tref.clone());
    }
    return result;
  }

}
