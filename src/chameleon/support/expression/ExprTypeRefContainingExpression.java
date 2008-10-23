package chameleon.support.expression;

import java.util.List;

import org.rejuse.association.Reference;

import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.type.TypeReference;
import chameleon.util.Util;

public abstract class ExprTypeRefContainingExpression<E extends ExprTypeRefContainingExpression> 
                extends Expression<E> {

	/**
	 * EXPRESSION
	 */
	private Reference<ExprTypeRefContainingExpression,Expression> _expression = new Reference<ExprTypeRefContainingExpression,Expression>(this);


  public Expression<? extends Expression> getExpression() {
    return _expression.getOtherEnd();
  }

  public void setExpression(Expression expression) {
  	if(expression != null) {
      _expression.connectTo(expression.getParentLink());
  	} 
  	else {
  		_expression.connectTo(null);
  	}
  }

	/**
	 * TYPE
	 */
	private Reference<ExprTypeRefContainingExpression,TypeReference> _typeReference = new Reference<ExprTypeRefContainingExpression,TypeReference>(this);

  public TypeReference getTypeReference() {
    return _typeReference.getOtherEnd();
  }

  public void setTypeReference(TypeReference type) {
    _typeReference.connectTo(type.getParentLink());
  }

  public List<Element> getChildren() {
  	List<Element> result = Util.createNonNullList(getExpression());
  	Util.addNonNull(getTypeReference(), result);
    return result;
  }

}
