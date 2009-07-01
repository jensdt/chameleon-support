package chameleon.support.statement;

import java.util.List;
import java.util.Set;

import org.rejuse.association.Reference;

import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.DeclarationContainer;
import chameleon.core.element.Element;
import chameleon.core.lookup.LexicalLookupStrategy;
import chameleon.core.lookup.LocalLookupStrategy;
import chameleon.core.lookup.LookupException;
import chameleon.core.lookup.LookupStrategy;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementContainer;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class ForStatement extends IterationStatement<ForStatement> implements DeclarationContainer<ForStatement, StatementContainer> {

	@SuppressWarnings("unchecked")
	public LookupStrategy lexicalContext(Element element) throws LookupException {
		return new LexicalLookupStrategy(new LocalLookupStrategy<ForStatement>(this),this);
	}
	
  /**
   * @param expression
   * @param statement
   */
  public ForStatement(ForControl control, Statement statement) {
    super(statement);
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

	public Set<? extends Declaration> declarations() throws LookupException {
		return forControl().declarations();
	}

}
