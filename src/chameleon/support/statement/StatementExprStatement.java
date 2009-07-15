package chameleon.support.statement;


import org.rejuse.association.Reference;

import chameleon.core.expression.Expression;
import chameleon.core.statement.Statement;

/**
 * @author Marko van Dooren
 */
public abstract class StatementExprStatement<E extends StatementExprStatement> extends StatementContainingStatement<E> {
  
  public StatementExprStatement(Statement statement, Expression expression) {
    super(statement);
    setExpression(expression);
  }

	/**
	 * EXPRESSION
	 */
	private Reference<StatementExprStatement,Expression> _expression = new Reference<StatementExprStatement,Expression>(this);

  
  public Expression<? extends Expression> getExpression() {
    return _expression.getOtherEnd();
  }
  
  public void setExpression(Expression expression) {
    if(expression != null) {
      _expression.connectTo(expression.parentLink());
    }
    else {
      _expression.connectTo(null);
    }
  }

}
