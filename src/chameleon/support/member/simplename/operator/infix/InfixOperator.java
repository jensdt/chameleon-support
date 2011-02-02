package chameleon.support.member.simplename.operator.infix;

import chameleon.core.declaration.SimpleNameDeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersSignature;
import chameleon.core.method.Method;
import chameleon.oo.type.TypeReference;
import chameleon.support.member.simplename.operator.Operator;

/**
 * @author Marko van Dooren
 */
public class InfixOperator <E extends InfixOperator<E,H,S>, H extends SimpleNameDeclarationWithParametersHeader<H,S>, S extends SimpleNameDeclarationWithParametersSignature> extends Operator<E,H,S,InfixOperator> {

  public InfixOperator(H header, TypeReference returnType) {
    super(header, returnType);
  }

  protected E cloneThis() {
    return (E) new InfixOperator(header().clone(), (TypeReference)returnTypeReference().clone());
  }

  public boolean sameKind(Method other) {
	  return(other instanceof InfixOperator);
  }

}
