package chameleon.support.member.simplename.operator.infix;

import chameleon.core.method.Method;
import chameleon.core.type.TypeReference;
import chameleon.support.member.simplename.SimpleNameMethodHeader;
import chameleon.support.member.simplename.SimpleNameMethodSignature;
import chameleon.support.member.simplename.operator.Operator;

/**
 * @author Marko van Dooren
 */
public class InfixOperator <E extends InfixOperator<E,H,S>, H extends SimpleNameMethodHeader<H,E,S>, S extends SimpleNameMethodSignature> extends Operator<E,H,S,InfixOperator> {

  public InfixOperator(H header, TypeReference returnType) {
    super(header, returnType);
  }

  protected E cloneThis() {
    return (E) new InfixOperator(header().clone(), (TypeReference)getReturnTypeReference().clone());
  }

  public boolean sameKind(Method other) {
	  return(other instanceof InfixOperator);
}

	public Class<InfixOperator> introducedDeclarationType() {
		return InfixOperator.class;
	}

}
