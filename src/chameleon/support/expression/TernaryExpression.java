package chameleon.support.expression;

import org.rejuse.association.SingleAssociation;

import chameleon.core.expression.BinaryExpression;
import chameleon.core.expression.Expression;

public abstract class TernaryExpression extends BinaryExpression {

	public TernaryExpression(Expression first, Expression second, Expression third) {
		super(first,second);
		setThird(third);
	}
	
	/**
	 * THIRD
	 */
	private SingleAssociation<TernaryExpression,Expression> _third = new SingleAssociation<TernaryExpression,Expression>(this);

  public Expression getThird() {
    return _third.getOtherEnd();
  }

  public void setThird(Expression expression) {
  	if(expression != null) {
      _third.connectTo(expression.parentLink());
  	} else {
  		_third.connectTo(null);
  	}
  }

}
