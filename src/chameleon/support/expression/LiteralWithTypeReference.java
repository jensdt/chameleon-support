package chameleon.support.expression;

import org.rejuse.association.Reference;

import chameleon.core.MetamodelException;
import chameleon.core.expression.Literal;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;

/**
 * @author marko
 */
public abstract class LiteralWithTypeReference<E extends LiteralWithTypeReference> extends Literal<E> {
  
  public LiteralWithTypeReference(String value) {
    super(value);
  }

	/**
	 * TARGET
	 */
	private Reference<LiteralWithTypeReference,TypeReference> _typeReference = new Reference<LiteralWithTypeReference,TypeReference>(this);

  
  public TypeReference getTypeReference() {
    return _typeReference.getOtherEnd();
  }
  
  public void setTypeReference(TypeReference type) {
    _typeReference.connectTo(type.getParentLink());
  }
  
  public Type getType() throws MetamodelException {
  	return getTypeReference().getType();
  }
}
