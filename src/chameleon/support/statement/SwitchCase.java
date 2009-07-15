package chameleon.support.statement;

import java.util.List;

import org.rejuse.association.OrderedReferenceSet;
import org.rejuse.association.Reference;
import org.rejuse.java.collections.RobustVisitor;
import org.rejuse.java.collections.Visitor;

import chameleon.core.element.Element;
import chameleon.core.lookup.LookupException;
import chameleon.core.namespacepart.NamespaceElementImpl;
import chameleon.core.statement.CheckedExceptionList;
import chameleon.core.statement.ExceptionSource;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementListContainer;

/**
 * @author Marko van Dooren
 */
public class SwitchCase extends NamespaceElementImpl<SwitchCase,SwitchStatement> implements StatementListContainer<SwitchCase,SwitchStatement>, ExceptionSource<SwitchCase,SwitchStatement> {

  public SwitchCase() {
	}
  
  public SwitchCase(SwitchLabel label) {
  	setLabel(label);
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
	private Reference<SwitchCase,SwitchLabel> _labels = new Reference<SwitchCase,SwitchLabel>(this);

  public void setLabel(SwitchLabel label) {
  	if(label != null) {
    _labels.connectTo(label.parentLink());
  	} else {
  		_labels.connectTo(null);
  	}
  }

  public SwitchLabel getLabel() {
    return _labels.getOtherEnd();
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
    result.setLabel(getLabel().clone());
    return result;
  }

  public CheckedExceptionList getCEL() throws LookupException {
    final CheckedExceptionList cel = new CheckedExceptionList(getNamespace().language());
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
    final CheckedExceptionList cel = new CheckedExceptionList(getNamespace().language());
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

 /*@
   @ also public behavior
   @
   @ post \result.containsAll(getStatements());
   @ post \result.containsAll(getLabels());
   @*/
  public List<Element> children() {
    List result = getStatements();
    result.add(getLabel());
    return result;
  }
  
	public List<Statement> statementsAfter(Statement statement) {
		List<Statement> statements = getStatements(); 
		int index = statements.indexOf(statement);
		// returns a view on a clone of _statements (getStatements() clones the list).
		// the view depends on the local variable, but since no other references exist
		// this is not a problem.
		return statements.subList(index, statements.size());
	}

}
