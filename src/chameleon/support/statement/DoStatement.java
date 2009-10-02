package chameleon.support.statement;

import java.util.List;

import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.statement.Statement;
import chameleon.core.validation.Valid;
import chameleon.core.validation.VerificationResult;
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

  public List<Element> children() {
    List<Element> result = Util.createNonNullList(condition());
    Util.addNonNull(getStatement(), result);
    return result;
  }

}
