package chameleon.support.statement;

import java.util.ArrayList;
import java.util.List;

import org.rejuse.association.Reference;

import chameleon.core.element.Element;
import chameleon.core.expression.Expression;

/**
 * @author Marko van Dooren
 */
public class CaseLabel extends SwitchLabel<CaseLabel> {
  
  public CaseLabel(Expression expr) {
    setExpression(expr);
  }

	/**
	 * EXPRESSION
	 */
	private Reference<CaseLabel,Expression> _expression = new Reference<CaseLabel,Expression>(this);

  
  public Expression<? extends Expression> getExpression() {
    return _expression.getOtherEnd();
  }
  
  public void setExpression(Expression expression) {
    _expression.connectTo(expression.parentLink());
  }

  public void removeExpression(Expression expr) {
    if(getExpression() == expr) {
      _expression.connectTo(null);
    }
  }

  public CaseLabel clone() {
    return new CaseLabel(getExpression().clone());
  }

  public List<Element> children() {
    return new ArrayList<Element>();
  }

}
