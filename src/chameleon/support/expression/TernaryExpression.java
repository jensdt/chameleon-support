package chameleon.support.expression;

import java.util.List;
import java.util.Set;

import org.rejuse.association.Reference;

import chameleon.core.MetamodelException;
import chameleon.core.accessibility.AccessibilityDomain;
import chameleon.core.expression.BinaryExpression;
import chameleon.core.expression.Expression;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.type.Type;

public abstract class TernaryExpression extends BinaryExpression {

	public TernaryExpression(Expression first, Expression second, Expression third) {
		super(first,second);
		setThird(third);
	}
	
	/**
	 * THIRD
	 */
	private Reference<TernaryExpression,Expression> _third = new Reference<TernaryExpression,Expression>(this);

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
