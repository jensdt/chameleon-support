package chameleon.support.statement;


import java.util.List;

import org.rejuse.association.SingleAssociation;

import chameleon.core.expression.Expression;
import chameleon.core.statement.Statement;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class IfThenElseStatement extends ExpressionContainingStatement<IfThenElseStatement> {

  public IfThenElseStatement(Expression expression, Statement ifStatement, Statement elseStatement) {
    super(expression);
    setIfStatement(ifStatement);
    setElseStatement(elseStatement);
  }

	/**
	 * IF STATEMENT
	 */
	private SingleAssociation<IfThenElseStatement,Statement> _ifStatement = new SingleAssociation<IfThenElseStatement,Statement>(this);


  public void setIfStatement(Statement statement) {
    _ifStatement.connectTo(statement.parentLink());
  }

  public Statement getIfStatement() {
    return _ifStatement.getOtherEnd();
  }

	/**
	 * ELSE STATEMENT
	 */
	private SingleAssociation<IfThenElseStatement,Statement> _elseStatement = new SingleAssociation<IfThenElseStatement,Statement>(this);

  public void setElseStatement(Statement statement) {
    if (statement != null) {
      _elseStatement.connectTo(statement.parentLink());
    }
    else {
      _elseStatement.connectTo(null);
    }
  }

  public Statement getElseStatement() {
    return _elseStatement.getOtherEnd();
  }


  public IfThenElseStatement clone() {
    Statement ifStatement = null;
    if(getIfStatement() != null) {
      ifStatement = getIfStatement().clone();
    }
    Statement elseStatement = null;
    if(getElseStatement() != null) {
      elseStatement = getElseStatement().clone();
    }
    return new IfThenElseStatement(getExpression().clone(), ifStatement, elseStatement);
  }

  public List children() {
    List result = Util.createNonNullList(getExpression());
    Util.addNonNull(getIfStatement(), result);
    Util.addNonNull(getElseStatement(), result);
    return result;
  }
}
