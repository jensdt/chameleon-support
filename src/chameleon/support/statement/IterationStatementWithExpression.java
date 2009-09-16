package chameleon.support.statement;

import org.rejuse.association.SingleAssociation;

import chameleon.core.expression.Expression;
import chameleon.core.statement.Statement;

public abstract class IterationStatementWithExpression<E extends IterationStatementWithExpression> extends IterationStatement<E> {
	
	
	
	public IterationStatementWithExpression(Statement statement, Expression condition) {
		super(statement);
		setCondition(condition);
	}

	/**
	 * EXPRESSION
	 */
	private SingleAssociation<IterationStatementWithExpression,Expression> _expression = new SingleAssociation<IterationStatementWithExpression,Expression>(this);

  
  public Expression condition() {
    return _expression.getOtherEnd();
  }
  
  public void setCondition(Expression expression) {
    if(expression != null) {
    	_expression.connectTo(expression.parentLink());
    }
    else {
      _expression.connectTo(null); 
    }
  }



}
