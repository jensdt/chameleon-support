package chameleon.support.statement;

import java.util.List;

import org.rejuse.association.Reference;

import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.statement.ExpressionContainingStatement;
import chameleon.util.Util;

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
	private Reference<AssertStatement,Expression> _messageExpression = new Reference<AssertStatement,Expression>(this);

  
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
