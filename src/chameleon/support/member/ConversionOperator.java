package chameleon.support.member;

import chameleon.core.method.Method;
import chameleon.core.type.TypeReference;
import chameleon.support.member.simplename.SimpleNameMethodHeader;
import chameleon.support.member.simplename.SimpleNameMethodSignature;
import chameleon.support.member.simplename.operator.Operator;

/**
 * @author Marko van Dooren
 * @author Tim Laeremans
 */
public class ConversionOperator<E extends ConversionOperator<E,H,S>, H extends SimpleNameMethodHeader<H,E,S>, S extends SimpleNameMethodSignature> extends Operator<E,H,S,ConversionOperator> {

	  public ConversionOperator(TypeReference returnType) {
		    super((H)new SimpleNameMethodHeader(""), returnType);
	  }

	  public boolean sameKind(Method other) {
		  return(other instanceof ConversionOperator);
	}

	  protected E cloneThis() {
	    return (E) new ConversionOperator((TypeReference)returnTypeReference().clone());
	  }

}
