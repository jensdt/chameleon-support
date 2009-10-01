package chameleon.support.statement;

import org.rejuse.association.SingleAssociation;

import chameleon.core.statement.Statement;

/**
 * @author Marko van Dooren
 */
public abstract class StatementContainingStatement<E extends StatementContainingStatement> extends Statement<E> {
  
  public StatementContainingStatement(Statement statement) {
    setStatement(statement);
  }

	/**
	 * STATEMENT
	 */
  
	private SingleAssociation<StatementContainingStatement,Statement> _statement = new SingleAssociation<StatementContainingStatement,Statement>(this);

  
  public void setStatement(Statement statement) {
    _statement.connectTo(statement.parentLink());
  }
  
  public void removeStatement() {
    _statement.connectTo(null);
  }
  
  public Statement getStatement() {
    return _statement.getOtherEnd();
  }
}
