package chameleon.support.statement;

import java.util.List;

import org.rejuse.association.OrderedReferenceSet;
import org.rejuse.java.collections.RobustVisitor;
import org.rejuse.java.collections.Visitor;

import chameleon.core.MetamodelException;
import chameleon.core.statement.CheckedExceptionList;
import chameleon.core.statement.ExceptionSource;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementContainer;
import chameleon.core.statement.StatementListContainer;
import chameleon.core.type.Type;
import chameleon.core.type.TypeDescendantImpl;

/**
 * @author Marko van Dooren
 */
public class SwitchCase extends TypeDescendantImpl<SwitchCase,SwitchStatement> implements StatementContainer<SwitchCase,SwitchStatement>, StatementListContainer<SwitchCase,SwitchStatement>, ExceptionSource<SwitchCase,SwitchStatement> {

  public SwitchCase() {
	}

	/**
	 * STATEMENTS
	 */
	private OrderedReferenceSet<SwitchCase,Statement> _statements = new OrderedReferenceSet<SwitchCase,Statement>(this);


  public OrderedReferenceSet getStatementsLink() {
    return _statements;
  }

  public void addStatement(Statement statement) {
    _statements.add(statement.parentLink());
  }

  public void removeStatement(Statement statement) {
    _statements.add(statement.parentLink());
  }

  public List<Statement> getStatements() {
    return _statements.getOtherEnds();
  }

	/**
	 * LABELS
	 */
	private OrderedReferenceSet<SwitchCase,SwitchLabel> _labels = new OrderedReferenceSet<SwitchCase,SwitchLabel>(this);

  public OrderedReferenceSet getLabelsLink() {
    return _labels;
  }

  public void addLabel(SwitchLabel label) {
    _labels.add(label.parentLink());
  }

  public void removeLabel(SwitchLabel label) {
    _labels.add(label.parentLink());
  }

  public List<SwitchLabel> getLabels() {
    return _labels.getOtherEnds();
  }

  public Type getNearestType() {
    return parent().getNearestType();
  }


  /**
   * @return
   */
  public SwitchCase clone() {
    final SwitchCase result = new SwitchCase();
    new Visitor<Statement>() {
      public void visit(Statement element) {
        result.addStatement(element.clone());
      }
    }.applyTo(getStatements());
    new Visitor<SwitchLabel>() {
      public void visit(SwitchLabel element) {
        result.addLabel(((SwitchLabel<? extends SwitchLabel>)element).clone());
      }
    }.applyTo(getLabels());
    return result;
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

 /*@
   @ also public behavior
   @
   @ post \result.containsAll(getStatements());
   @ post \result.containsAll(getLabels());
   @*/
  public List children() {
    List result = getStatements();
    result.addAll(getLabels());
    return result;
  }
}
