package chameleon.support.member.simplename.operator.postfix;

import chameleon.core.declaration.SimpleNameDeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersSignature;
import chameleon.core.method.Method;
import chameleon.oo.type.TypeReference;
import chameleon.support.member.simplename.operator.Operator;

/**
 * @author Marko van Dooren
 */
public class PostfixOperator<E extends PostfixOperator<E,H,S>, H extends SimpleNameDeclarationWithParametersHeader<H,S>, S extends SimpleNameDeclarationWithParametersSignature> extends Operator<E,H,S> {

  public PostfixOperator(H header, TypeReference returnType) {
    super(header, returnType);
  }
  
  public boolean sameKind(Method other) {
	  	return(other instanceof PostfixOperator);
	  }  

  protected E cloneThis() {
    return (E) new PostfixOperator(header().clone(), (TypeReference)returnTypeReference().clone());
  }

}
