package chameleon.support.statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.rejuse.association.Reference;

import chameleon.core.context.LookupException;
import chameleon.core.declaration.Declaration;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.statement.StatementContainer;
import chameleon.core.type.Type;
import chameleon.util.Util;

public class SimpleForControl extends ForControl<SimpleForControl> implements StatementContainer<SimpleForControl, ForStatement> {

	public SimpleForControl(ForInit init, Expression condition, StatementExprList update) {
		setForInit(init);
		setExpression(condition);
		setUpdate(update);
	}
	
  /**
	 * FOR INIT
	 */
	private Reference<SimpleForControl,ForInit> _forInit = new Reference<SimpleForControl,ForInit>(this);


  public Reference<SimpleForControl,ForInit> getInitLink() {
    return _forInit;
  }

  public void setForInit(ForInit forInit) {
    if (forInit != null) {
      _forInit.connectTo(forInit.parentLink());
    }
    else {
      _forInit.connectTo(null);
    }
  }

  public ForInit getForInit() {
    return _forInit.getOtherEnd();
  }

	/**
	 * UPDATE
	 */

  private Reference<SimpleForControl,StatementExprList> _update = new Reference<SimpleForControl,StatementExprList>(this);

  public Reference<SimpleForControl,StatementExprList> getUpdateLink() {
    return _update;
  }

  public void setUpdate(StatementExprList update) {
    if (update != null) {
      _update.connectTo(update.parentLink());
    }
    else {
      _update.connectTo(null);
    }
  }

  public StatementExprList getUpdate() {
    return _update.getOtherEnd();
  }

  public SimpleForControl clone() {
    Expression cond = null;
    if(condition() != null) {
      cond = condition().clone();
    }
    ForInit init = null;
    if(getForInit() != null) {
      init = ((ForInit<? extends ForInit, ? extends Element>)getForInit()).clone();
    }
    StatementExprList update = null;
    if(getUpdate() != null) {
      update = getUpdate().cloneUpdate();
    }
    return new SimpleForControl(init, cond, update);
  }

	public Type getNearestType() {
		return parent().getNearestType();
	}

	public List<? extends Element> children() {
		List<Element> result = new ArrayList<Element>();
		Util.addNonNull(condition(), result);
		Util.addNonNull(getUpdate(), result);
		Util.addNonNull(getForInit(), result);
		return result;
	}

	/**
	 * EXPRESSION
	 */
	private Reference<SimpleForControl,Expression> _expression = new Reference<SimpleForControl,Expression>(this);

  
  public Expression<? extends Expression> condition() {
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

	public Set<? extends Declaration> declarations() throws LookupException {
		return getForInit().declarations();
	}

}
