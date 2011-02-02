package chameleon.support.member;

import chameleon.core.declaration.SimpleNameDeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersSignature;
import chameleon.core.method.Method;
import chameleon.oo.type.TypeReference;
import chameleon.support.member.simplename.operator.Operator;

/**
 * @author Marko van Dooren
 * @author Tim Laeremans
 */
public class ConversionOperator<E extends ConversionOperator<E,H,S>, H extends SimpleNameDeclarationWithParametersHeader<H,S>, S extends SimpleNameDeclarationWithParametersSignature> extends Operator<E,H,S,ConversionOperator> {

	  public ConversionOperator(TypeReference returnType) {
		    super((H)new SimpleNameDeclarationWithParametersHeader(""), returnType);
	  }

	  public boolean sameKind(Method other) {
		  return(other instanceof ConversionOperator);
	}

	  protected E cloneThis() {
	    return (E) new ConversionOperator((TypeReference)returnTypeReference().clone());
	  }

}
