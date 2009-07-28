package chameleon.support.expression;

import chameleon.core.expression.InvocationTarget;
import chameleon.core.language.ObjectOrientedLanguage;
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

  protected Type actualType() throws LookupException {
    return language(ObjectOrientedLanguage.class).voidType();
  }

  public NormalMethod getMethod() throws LookupException {
	   return nearestAncestor(Type.class).lexicalLookupStrategy().lookUp(selector());
  }

  public boolean superOf(InvocationTarget target) {
    return false;
  }

  protected ThisConstructorDelegation cloneInvocation(InvocationTarget target) {
    return new ThisConstructorDelegation();
  }

}
