package chameleon.support.statement;

import java.util.List;

import org.rejuse.association.SingleAssociation;

import chameleon.core.element.Element;
import chameleon.core.expression.Expression;

public class AssertStatement  extends ExpressionContainingStatement<AssertStatement> {

  /**
   * @param expression
   */
  public AssertStatement(Expression expression) {
    super(expression);
  }

  public AssertStatement clone() {
    return new AssertStatement(getExpression().clone());
  }

	/**
	 * EXPRESSION
	 */
	private SingleAssociation<AssertStatement,Expression> _messageExpression = new SingleAssociation<AssertStatement,Expression>(this);

  
  public Expression messageExpression() {
    return _messageExpression.getOtherEnd();
  }
  
  public void setMessageExpression(Expression expression) {
    if(expression != null) {
    	_messageExpression.connectTo(expression.parentLink());
    }
    else {
      _messageExpression.connectTo(null); 
    }
  }

  public List<Element> children() {
  	List<Element> result = super.children();
  	if(messageExpression() != null) {
  		result.add(messageExpression());
  	}
  	return result;
  }


}
