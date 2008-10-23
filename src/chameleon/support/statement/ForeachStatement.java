package chameleon.support.statement;

import java.util.List;

import org.rejuse.association.Reference;

import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.statement.Statement;
import chameleon.core.variable.Variable;
import chameleon.util.Util;

/**
 * @author Tim Laeremans
 *
 */
public class ForeachStatement extends IterationStatement<ForeachStatement> {

	public ForeachStatement(Variable variable, Expression expression, Statement statement){
		super(expression, statement);
		setVariable(variable);
	}
	
	public ForeachStatement clone() {
		Variable v   = getVariable().clone();
		Expression e = getExpression().clone();
		Statement s  = getStatement().clone();
		return new ForeachStatement(v,e,s);
	}

	public List<Element> getChildren() {
	    List<Element> result = Util.createNonNullList(getVariable());
	    Util.addNonNull(getExpression(), result);
	    Util.addNonNull(getStatement(), result);
	    return result;
	}

	private Reference<ForeachStatement,Variable> _variable = new Reference<ForeachStatement,Variable>(this);

	/**
	 * @return Returns the variable.
	 */
	public Variable getVariable() {
		return _variable.getOtherEnd();
	}
	
	/**
	 * @param variable The variable to set.
	 */
	public void setVariable(Variable variable) {
		if(variable != null) {
		  _variable.connectTo(variable.getParentLink());
		} else {
			_variable.connectTo(null);
		}
	}
}
