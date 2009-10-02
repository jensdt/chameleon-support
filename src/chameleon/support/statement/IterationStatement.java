package chameleon.support.statement;

import chameleon.core.element.Element;
import chameleon.core.statement.Statement;

/**
 * @author Marko van Dooren
 */
public abstract class IterationStatement<E extends IterationStatement> extends StatementContainingStatement<E> implements BreakableStatement<E, Element>{
  
  /**
   * @param expression
   */
  public IterationStatement(Statement<E, Element> statement) {
    super(statement);
  }

}
