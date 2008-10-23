package chameleon.support.member;

import chameleon.core.method.Method;
import chameleon.core.type.TypeReference;
import chameleon.support.member.simplename.SimpleNameSignature;
import chameleon.support.member.simplename.operator.Operator;

/**
 * @author Marko van Dooren
 * @author Tim Laeremans
 */
public class ConversionOperator extends Operator<ConversionOperator, SimpleNameSignature> {

	  public ConversionOperator(TypeReference returnType) {
		    super(new SimpleNameSignature(""), returnType);
	  }

	  public boolean sameKind(Method other) {
		  return(other instanceof ConversionOperator);
	}

	  protected ConversionOperator cloneThis() {
	    return new ConversionOperator((TypeReference)getReturnTypeReference().clone());
	  }


}
