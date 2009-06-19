package chameleon.support.statement;

import java.util.List;

import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.statement.Statement;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class WhileStatement extends IterationStatementWithExpression<WhileStatement> {

  public WhileStatement(Expression expression, Statement statement) {
    super(statement, expression);
  }

  public WhileStatement clone() {
    return new WhileStatement(condition().clone(), getStatement().clone());
  }

  public List<Element> children() {
    List<Element> result = Util.createNonNullList(condition());
    Util.addNonNull(getStatement(), result);
    return result;
  }
}
