package chameleon.support.expression;

import java.util.ArrayList;
import java.util.List;

import org.rejuse.association.SingleAssociation;

import chameleon.core.element.Element;
import chameleon.core.expression.Literal;
import chameleon.core.lookup.LookupException;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;
import chameleon.core.validation.BasicProblem;
import chameleon.core.validation.Valid;
import chameleon.core.validation.VerificationResult;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public abstract class LiteralWithTypeReference<E extends LiteralWithTypeReference> extends Literal<E> {
  
  public LiteralWithTypeReference(String value) {
    super(value);
  }

  public LiteralWithTypeReference(String value, TypeReference ref) {
    super(value);
    setTypeReference(ref);
  }
  
  public List<Element> children() {
  	List<Element> result = super.children();
  	Util.addNonNull(getTypeReference(), result);
  	return result;
  }


	/**
	 * TARGET
	 */
	private SingleAssociation<LiteralWithTypeReference,TypeReference> _typeReference = new SingleAssociation<LiteralWithTypeReference,TypeReference>(this);

  
  public TypeReference getTypeReference() {
    return _typeReference.getOtherEnd();
  }
  
  public void setTypeReference(TypeReference type) {
    _typeReference.connectTo(type.parentLink());
  }
  
  protected Type actualType() throws LookupException {
  	return getTypeReference().getType();
  }
  
}
