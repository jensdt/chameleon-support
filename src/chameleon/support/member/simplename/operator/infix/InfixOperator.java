package chameleon.support.member.simplename.operator.infix;

import chameleon.core.method.Method;
import chameleon.core.type.TypeReference;
import chameleon.support.member.simplename.SimpleNameMethodSignature;
import chameleon.support.member.simplename.operator.Operator;

/**
 * @author Marko van Dooren
 */
public class InfixOperator extends Operator<InfixOperator,SimpleNameMethodSignature> {

  public InfixOperator(SimpleNameMethodSignature sig, TypeReference returnType) {
    super(sig, returnType);
  }

  protected InfixOperator cloneThis() {
    return new InfixOperator((SimpleNameMethodSignature) signature().clone(), 
                             (TypeReference)getReturnTypeReference().clone());
  }

  public boolean sameKind(Method other) {
	  return(other instanceof InfixOperator);
}

}
