package chameleon.support.statement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.rejuse.association.OrderedReferenceSet;
import org.rejuse.java.collections.Visitor;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.DeclarationSelector;
import chameleon.core.element.Element;
import chameleon.core.namespace.NamespaceElement;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementContainer;
import chameleon.core.variable.VariableContainer;
import chameleon.support.variable.LocalVariable;

/**
 * @author Marko van Dooren
 */
public class LocalVariableDeclarationStatement extends Statement<LocalVariableDeclarationStatement> 
       implements VariableContainer<LocalVariableDeclarationStatement,StatementContainer>, ForInit<LocalVariableDeclarationStatement,StatementContainer> {

  public LocalVariableDeclarationStatement() {
	  
  }
  
	 /************
	  * VARIABLE *
	  ************/
	    
		private OrderedReferenceSet<LocalVariableDeclarationStatement,LocalVariable> _variables = new OrderedReferenceSet<LocalVariableDeclarationStatement,LocalVariable>(this);

		public List<LocalVariable> getVariables() {
	    return _variables.getOtherEnds();
	  }
	    
	  public void addVariable(LocalVariable v) {
	    _variables.add(v.parentLink());
	  }
	    
	  public void removeVariable(LocalVariable v) {
	    _variables.remove(v.parentLink());
	  }
	    
	  public OrderedReferenceSet<LocalVariableDeclarationStatement,LocalVariable> getVariableLink() {
	  	return _variables;
	  }
  
  public LocalVariableDeclarationStatement clone() {
    final LocalVariableDeclarationStatement result = new LocalVariableDeclarationStatement();
    new Visitor() {
      public void visit(Object element) {
        result.addVariable((LocalVariable)((LocalVariable)element).clone());
      }
    }.applyTo(getVariables());
    return result;
  }

  public List<? extends Element> children() {
    return getVariables();
  }

  public int getIndexOf(Statement statement) {
    return (equals(statement) ? 1 : 0);
  }

  public int getNbStatements() {
    return 1;
  }
  
  public Set<Declaration> declarations() {
    Set<Declaration> result = new HashSet<Declaration>();
    result.addAll(getVariables());
    return result;
  }
  
  //COPIED FROM chameleon.core.type.Type
  public <T extends Declaration> Set<T> declarations(DeclarationSelector<T> selector) throws MetamodelException {
    Set<Declaration> tmp = declarations();
    Set<T> result = selector.selection(tmp);
    return result;
  }

	public NamespaceElement variableScopeElement() {
		return this;
	}

}
