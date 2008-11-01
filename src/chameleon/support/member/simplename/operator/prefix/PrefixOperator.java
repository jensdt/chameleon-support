package chameleon.support.member.simplename.operator.prefix;

import chameleon.core.method.Method;
import chameleon.core.type.TypeReference;
import chameleon.support.member.simplename.SimpleNameMethodSignature;
import chameleon.support.member.simplename.operator.Operator;

public class PrefixOperator extends Operator<PrefixOperator,SimpleNameMethodSignature> {
  
  public PrefixOperator(SimpleNameMethodSignature sig, TypeReference returnType) {
    super(sig, returnType);
  }

  public boolean sameKind(Method other) {
  	return(other instanceof PrefixOperator);
  }

  protected PrefixOperator cloneThis() {
    return new PrefixOperator(signature().clone(), (TypeReference)getReturnTypeReference().clone());
  }

	
	
	


	
  
  
}
