package chameleon.support.expression;

import chameleon.core.expression.InvocationTarget;
import chameleon.core.lookup.LookupException;
import chameleon.core.type.Type;
import chameleon.support.member.simplename.method.NormalMethod;

/**
 * @author Marko van Dooren
 */
public class ThisConstructorDelegation extends ConstructorDelegation<ThisConstructorDelegation> {

  public ThisConstructorDelegation(){
    super(null);
  }

  protected String getName() {
    return "this";
  }

  public Type getType() throws LookupException {
    return language().voidType();
  }

  public NormalMethod getMethod() throws LookupException {
	   return ((Type)getNearestType()).lexicalContext().lookUp(selector());
  }

  public boolean superOf(InvocationTarget target) {
    return false;
  }

  protected ThisConstructorDelegation cloneInvocation(InvocationTarget target) {
    return new ThisConstructorDelegation();
  }

}
