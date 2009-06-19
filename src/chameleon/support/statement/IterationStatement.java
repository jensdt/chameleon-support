package chameleon.support.statement;

import java.util.Set;

import org.rejuse.java.collections.Visitor;

import chameleon.core.expression.Expression;
import chameleon.core.statement.Statement;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public abstract class IterationStatement<E extends IterationStatement> extends StatementContainingStatement<E> {
  
  /**
   * @param expression
   */
  public IterationStatement(Statement statement) {
    super(statement);
  }

//  public abstract Expression condition();

}
