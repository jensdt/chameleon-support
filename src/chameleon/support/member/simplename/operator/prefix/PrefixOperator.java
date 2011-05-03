package chameleon.support.member.simplename.operator.prefix;

import chameleon.core.declaration.SimpleNameDeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersSignature;
import chameleon.core.method.Method;
import chameleon.oo.type.TypeReference;
import chameleon.support.member.simplename.operator.Operator;

public class PrefixOperator<E extends PrefixOperator<E,H,S>, H extends SimpleNameDeclarationWithParametersHeader<H,S>, S extends SimpleNameDeclarationWithParametersSignature> extends Operator<E,H,S> {
  
  public PrefixOperator(H header, TypeReference returnType) {
    super(header, returnType);
  }

  public boolean sameKind(Method other) {
  	return(other instanceof PrefixOperator);
  }

  protected E cloneThis() {
    return (E) new PrefixOperator(header().clone(), (TypeReference)returnTypeReference().clone());
  }

}
