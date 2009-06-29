package chameleon.support.variable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.rejuse.association.Reference;

import chameleon.core.context.Context;
import chameleon.core.context.LexicalContext;
import chameleon.core.context.LookupException;
import chameleon.core.context.TargetContext;
import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.DeclarationContainer;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.type.Type;
import chameleon.core.type.TypeDescendantImpl;
import chameleon.core.variable.Variable;
import chameleon.util.Util;

public class VariableDeclaration<V extends Variable> extends TypeDescendantImpl<VariableDeclaration<V>,VariableDeclarator<?,V,?>> implements DeclarationContainer<VariableDeclaration<V>,VariableDeclarator<?,V,?>> {

	public VariableDeclaration(String name) {
		this(new SimpleNameSignature(name), null);
	}
	
	public VariableDeclaration(String name, Expression expr) {
		this(new SimpleNameSignature(name), expr);
	}
	
	public VariableDeclaration(SimpleNameSignature sig, Expression expr) {
		setExpression(expr);
		setSignature(sig);
	}
	
	@Override
	public VariableDeclaration clone() {
		return new VariableDeclaration(signature().clone(), expression().clone());
	}

	public Type getNearestType() {
		return parent().getNearestType();
	}
	
	 public void setSignature(SimpleNameSignature signature) {
	    if(signature != null) {
	    	_signature.connectTo(signature.parentLink());
	    } else {
	    	_signature.connectTo(null);
	    }
	  }
	  
	  /**
	   * Return the signature of this member.
	   */
	  public SimpleNameSignature signature() {
	    return _signature.getOtherEnd();
	  }
	  
	  private Reference<VariableDeclaration, SimpleNameSignature> _signature = new Reference<VariableDeclaration, SimpleNameSignature>(this);


	public List<Element> children() {
		List<Element> result = new ArrayList<Element>();
		Util.addNonNull(expression(), result);
		result.add(signature());
		return result;
	}

	/**
	 * EXPRESSION
	 */
	private Reference<VariableDeclaration,Expression> _expression = new Reference<VariableDeclaration,Expression>(this);

  
  public Expression expression() {
    return _expression.getOtherEnd();
  }
  
  public void setExpression(Expression expression) {
    if(expression != null) {
    	_expression.connectTo(expression.parentLink());
    }
    else {
      _expression.connectTo(null); 
    }
  }
  
  public V variable() {
  	Expression expression = expression();
		Expression initClone = (expression == null ? null : expression.clone());
		V result = parent().createVariable(signature().clone(),initClone);
  	result.setUniParent(parent().parent());
  	transform(result);
  	return result;
  }
  
  protected void transform(V variable) {
  }

	public Set<? extends Declaration> declarations() throws LookupException {
		Set<Variable> result = new HashSet<Variable>();
		result.add(variable());
		return result;
	}
	
	/**
	 * Return a standard lexical context that is attached to this variable declaration,
	 * and to a target context which is also attached to this variable declaration.
	 */
	public Context lexicalContext(Element element) throws LookupException {
		return new LexicalContext(new TargetContext<VariableDeclaration>(this),this);
	}
  
}
