package chameleon.support.member.simplename.operator.prefix;

import chameleon.core.method.Method;
import chameleon.oo.type.TypeReference;
import chameleon.support.member.simplename.SimpleNameMethodHeader;
import chameleon.support.member.simplename.SimpleNameMethodSignature;
import chameleon.support.member.simplename.operator.Operator;

public class PrefixOperator<E extends PrefixOperator<E,H,S>, H extends SimpleNameMethodHeader<H,E,S>, S extends SimpleNameMethodSignature> extends Operator<E,H,S,PrefixOperator> {
  
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
