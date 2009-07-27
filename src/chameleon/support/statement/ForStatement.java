package chameleon.support.statement;

import java.util.List;

import org.rejuse.association.Reference;

import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.DeclarationContainer;
import chameleon.core.element.Element;
import chameleon.core.lookup.DeclarationSelector;
import chameleon.core.lookup.LexicalLookupStrategy;
import chameleon.core.lookup.LocalLookupStrategy;
import chameleon.core.lookup.LookupException;
import chameleon.core.lookup.LookupStrategy;
import chameleon.core.statement.Statement;
import chameleon.core.variable.Variable;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class ForStatement extends IterationStatement<ForStatement> implements DeclarationContainer<ForStatement, Element> {

	@SuppressWarnings("unchecked")
	public LookupStrategy lexicalLookupStrategy(Element element) throws LookupException {
		return new LexicalLookupStrategy(new LocalLookupStrategy<ForStatement>(this),this);
	}
	
  /**
   * @param expression
   * @param statement
   */
  public ForStatement(ForControl control, Statement statement) {
    super(statement);
  	setForControl(control);
  }
  
  public ForControl forControl() {
  	return _control.getOtherEnd();
  }
  
  public void setForControl(ForControl control) {
  	if(control != null) {
  		_control.connectTo(control.parentLink());
  	} else {
  		_control.connectTo(null);
  	}
  }
  
  private Reference<ForStatement,ForControl> _control = new Reference<ForStatement, ForControl>(this); 


  public List<Element> children() {
  	List<Element> result = Util.createNonNullList(forControl());
  	result.add(getStatement());
  	return result;
  }

	@Override
	public ForStatement clone() {
		return new ForStatement(forControl().clone(), getStatement().clone());
	}

	public List<? extends Variable> declarations() throws LookupException {
		return forControl().declarations();
	}

	public <D extends Declaration> List<D> declarations(DeclarationSelector<D> selector) throws LookupException {
		return selector.selection(declarations());
	}

}
