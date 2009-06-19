package chameleon.support.statement;

import java.util.List;

import chameleon.core.expression.Expression;
import chameleon.core.statement.Statement;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class DoStatement extends IterationStatementWithExpression<DoStatement> {

  public DoStatement(Expression expression, Statement statement) {
    super(statement, expression);
  }

  public DoStatement clone() {
    return new DoStatement(condition().clone(), getStatement().clone());
  }

  public List children() {
    List result = Util.createNonNullList(condition());
    Util.addNonNull(getStatement(), result);
    return result;
  }
}
