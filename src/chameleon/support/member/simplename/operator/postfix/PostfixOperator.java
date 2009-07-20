package chameleon.support.member.simplename.operator.postfix;

import chameleon.core.method.Method;
import chameleon.core.type.TypeReference;
import chameleon.support.member.simplename.SimpleNameMethodHeader;
import chameleon.support.member.simplename.SimpleNameMethodSignature;
import chameleon.support.member.simplename.operator.Operator;

/**
 * @author Marko van Dooren
 */
public class PostfixOperator<E extends PostfixOperator<E,H,S>, H extends SimpleNameMethodHeader<H,E,S>, S extends SimpleNameMethodSignature> extends Operator<E,H,S,PostfixOperator> {

  public PostfixOperator(H header, TypeReference returnType) {
    super(header, returnType);
  }
  
  public boolean sameKind(Method other) {
	  	return(other instanceof PostfixOperator);
	  }  

  protected E cloneThis() {
    return (E) new PostfixOperator(header().clone(), (TypeReference)getReturnTypeReference().clone());
  }

}
