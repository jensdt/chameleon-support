package chameleon.support.variable;

import java.util.ArrayList;
import java.util.List;

import org.rejuse.association.Reference;

import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.ChameleonProgrammerException;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.type.Type;
import chameleon.core.type.TypeDescendantImpl;
import chameleon.core.variable.Variable;

public class VariableDeclaration<V extends Variable> extends TypeDescendantImpl<VariableDeclaration<V>,VariableDeclarator<?,V,?>> {

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
		result.add(expression());
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
  	V result = parent().createVariable(signature().clone(),expression().clone());
  	result.setUniParent(parent().parent());
  	transform(result);
  	return result;
  }
  
  protected void transform(V variable) {
  }
  
}
