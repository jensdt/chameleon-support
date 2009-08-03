package chameleon.support.statement;

import java.util.ArrayList;
import java.util.List;

import org.rejuse.association.OrderedReferenceSet;
import org.rejuse.java.collections.RobustVisitor;
import org.rejuse.java.collections.Visitor;

import chameleon.core.declaration.Declaration;
import chameleon.core.lookup.DeclarationSelector;
import chameleon.core.lookup.LookupException;
import chameleon.core.namespace.NamespaceElementImpl;
import chameleon.core.statement.CheckedExceptionList;
import chameleon.core.statement.ExceptionSource;
import chameleon.core.statement.Statement;

/**
 * A list of statement expressions as used in the initialization clause of a 
 * for statement. It contains a list of statement expressions.
 * 
 * @author Marko van Dooren
 */
public class StatementExprList extends NamespaceElementImpl<StatementExprList,SimpleForControl> implements ForInit<StatementExprList,SimpleForControl>, ExceptionSource<StatementExprList,SimpleForControl> {

	public StatementExprList() {
	}
	
	/**
	 * STATEMENT EXPRESSIONS
	 */
	private OrderedReferenceSet<StatementExprList,StatementExpression> _statementExpressions = new OrderedReferenceSet<StatementExprList,StatementExpression>(this);

  public void addStatement(StatementExpression statement) {
    _statementExpressions.add(statement.parentLink());
  }

  public void removeStatement(StatementExpression statement) {
    _statementExpressions.remove(statement.parentLink());
  }

  public List<StatementExpression> getStatements() {
    return _statementExpressions.getOtherEnds();
  }

  /**
   * @FIXME why is this method here?
   */
  public StatementExprList clone() {
    final StatementExprList result = new StatementExprList();
    new Visitor() {
      public void visit(Object element) {
        result.addStatement((StatementExpression)((StatementExpression)element).clone());
      }
    }.applyTo(getStatements());
    return result;
  }

  public CheckedExceptionList getCEL() throws LookupException {
    final CheckedExceptionList cel = new CheckedExceptionList();
    try {
      new RobustVisitor() {
        public Object visit(Object element) throws LookupException {
          cel.absorb(((ExceptionSource)element).getCEL());
          return null;
        }

        public void unvisit(Object element, Object undo) {
          //NOP
        }
      }.applyTo(getStatements());
      return cel;
    }
    catch (LookupException e) {
      throw e;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Error();
    }

  }

  public CheckedExceptionList getAbsCEL() throws LookupException {
    final CheckedExceptionList cel = new CheckedExceptionList();
    try {
      new RobustVisitor() {
        public Object visit(Object element) throws LookupException {
          cel.absorb(((ExceptionSource)element).getAbsCEL());
          return null;
        }

        public void unvisit(Object element, Object undo) {
          //NOP
        }
      }.applyTo(getStatements());
      return cel;
    }
    catch (LookupException e) {
      throw e;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Error();
    }

  }

  public int getIndexOf(Statement statement) {
    return getStatements().indexOf(statement) + 1;
  }

  public int getNbStatements() {
    return getStatements().size();
  }

 /*@
   @ also public behavior
   @
   @ post \result.equals(getStatements());
   @*/
  public List<StatementExpression> children() {
    return getStatements();
  }

	public List<? extends Declaration> declarations() throws LookupException {
		return new ArrayList<Declaration>();
	}

	public <D extends Declaration> List<D> declarations(DeclarationSelector<D> selector) throws LookupException {
		return new ArrayList<D>();
	}

}
