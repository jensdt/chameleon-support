package chameleon.support.statement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.rejuse.association.Reference;

import chameleon.core.MetamodelException;
import chameleon.core.context.Context;
import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.DeclarationContainer;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.namespace.NamespaceElement;
import chameleon.core.statement.StatementContainer;
import chameleon.core.type.Type;
import chameleon.support.variable.LocalVariableDeclarator;
import chameleon.util.Util;

public class EnhancedForControl extends ForControl<EnhancedForControl> implements StatementContainer<EnhancedForControl,ForStatement> {

	
	
	public EnhancedForControl(LocalVariableDeclarator variable, Expression expression) {
		setVariableDeclarator(variable);
		setCollection(expression);
	}

	public LocalVariableDeclarator variableDeclarator() {
		return _variable.getOtherEnd();
	}
	
	public void setVariableDeclarator(LocalVariableDeclarator variable) {
		if(variable != null) {
			_variable.connectTo(variable.parentLink());
		} else {
			_variable.connectTo(null);
		}
	}
	
	private Reference<EnhancedForControl,LocalVariableDeclarator> _variable = new Reference<EnhancedForControl,LocalVariableDeclarator>(this);
	
	@Override
	public EnhancedForControl clone() {
		return new EnhancedForControl(variableDeclarator().clone(),collection().clone());
	}

	public Type getNearestType() {
		return parent().getNearestType();
	}

	public List<Element> children() {
		List<Element> result = new ArrayList<Element>();
		Util.addNonNull(collection(), result);
		Util.addNonNull(variableDeclarator(), result);
		return result;
	}

	/**
	 * EXPRESSION
	 */
	private Reference<EnhancedForControl,Expression> _expression = new Reference<EnhancedForControl,Expression>(this);

  
  public Expression collection() {
    return _expression.getOtherEnd();
  }
  
  public void setCollection(Expression expression) {
    if(expression != null) {
    	_expression.connectTo(expression.parentLink());
    }
    else {
      _expression.connectTo(null); 
    }
  }

	public NamespaceElement variableScopeElement() {
		return parent();
	}

	public Set<? extends Declaration> declarations() throws MetamodelException {
		Set<Declaration> result = new HashSet<Declaration>();
		result.addAll(variableDeclarator().variables());
		return result;
	}

}
