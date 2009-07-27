package chameleon.support.expression;

import chameleon.core.expression.InvocationTarget;
import chameleon.core.lookup.LookupException;
import chameleon.core.type.Type;
import chameleon.support.member.simplename.method.NormalMethod;

/**
 * @author Marko van Dooren
 */
public class SuperConstructorDelegation extends ConstructorDelegation<SuperConstructorDelegation> {

  public SuperConstructorDelegation(){
    super(null);
  }

  protected Type actualType() throws LookupException {
    return language().voidType();
  }
  
  // @FIXME: does not work with multiple inheritance. Call is ambiguous.
  public NormalMethod getMethod() throws LookupException {
	    return nearestAncestor(Type.class).getDirectSuperTypes().get(0).lexicalLookupStrategy().lookUp(selector());
  }
  
  public boolean superOf(InvocationTarget target) {
    return false;
  }

  protected SuperConstructorDelegation cloneInvocation(InvocationTarget target) {
    return new SuperConstructorDelegation();
  }

}
