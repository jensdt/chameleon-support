package chameleon.support.statement;

import chameleon.core.statement.Statement;

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
