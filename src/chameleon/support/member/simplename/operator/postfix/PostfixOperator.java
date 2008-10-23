package chameleon.support.member.simplename.operator.postfix;

import chameleon.core.method.Method;
import chameleon.core.type.TypeReference;
import chameleon.support.member.simplename.SimpleNameSignature;
import chameleon.support.member.simplename.operator.Operator;

/**
 * @author Marko van Dooren
 */
public class PostfixOperator extends Operator<PostfixOperator,SimpleNameSignature> {

  public PostfixOperator(SimpleNameSignature sig, TypeReference returnType) {
    super(sig, returnType);
  }
  
  public boolean sameKind(Method other) {
	  	return(other instanceof PostfixOperator);
	  }  

  protected PostfixOperator cloneThis() {
    return new PostfixOperator(signature().clone(), (TypeReference)getReturnTypeReference().clone());
  }
  
	
	
	


	
  
  
}
