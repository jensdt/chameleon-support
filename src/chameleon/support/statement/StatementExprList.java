package chameleon.support.statement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.rejuse.association.OrderedReferenceSet;
import org.rejuse.java.collections.RobustVisitor;
import org.rejuse.java.collections.Visitor;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.Declaration;
import chameleon.core.statement.CheckedExceptionList;
import chameleon.core.statement.ExceptionSource;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementContainer;
import chameleon.core.type.Type;
import chameleon.core.type.TypeDescendantImpl;

/**
 * A list of statement expressions as used in the initialization clause of a 
 * for statement. It contains a list of statement expressions.
 * 
 * @author Marko van Dooren
 */
public class StatementExprList extends TypeDescendantImpl<StatementExprList,SimpleForControl> implements ForInit<StatementExprList,SimpleForControl>, StatementContainer<StatementExprList,SimpleForControl>, ExceptionSource<StatementExprList,SimpleForControl> {

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

  public Type getNearestType() {
    return parent().getNearestType();
  }

  /**
   * @FIXME why is this method here?
   */
  public StatementExprList cloneUpdate() {
    final StatementExprList result = new StatementExprList();
    new Visitor() {
      public void visit(Object element) {
        result.addStatement((StatementExpression)((StatementExpression)element).clone());
      }
    }.applyTo(getStatements());
    return result;
  }

  public StatementExprList clone() {
    return cloneUpdate();
  }

  public CheckedExceptionList getCEL() throws MetamodelException {
    final CheckedExceptionList cel = new CheckedExceptionList(getNamespace().language());
    try {
      new RobustVisitor() {
        public Object visit(Object element) throws MetamodelException {
          cel.absorb(((ExceptionSource)element).getCEL());
          return null;
        }

        public void unvisit(Object element, Object undo) {
          //NOP
        }
      }.applyTo(getStatements());
      return cel;
    }
    catch (MetamodelException e) {
      throw e;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Error();
    }

  }

  public CheckedExceptionList getAbsCEL() throws MetamodelException {
    final CheckedExceptionList cel = new CheckedExceptionList(getNamespace().language());
    try {
      new RobustVisitor() {
        public Object visit(Object element) throws MetamodelException {
          cel.absorb(((ExceptionSource)element).getAbsCEL());
          return null;
        }

        public void unvisit(Object element, Object undo) {
          //NOP
        }
      }.applyTo(getStatements());
      return cel;
    }
    catch (MetamodelException e) {
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

	public Set<? extends Declaration> declarations() throws MetamodelException {
		return new HashSet<Declaration>();
	}

}
