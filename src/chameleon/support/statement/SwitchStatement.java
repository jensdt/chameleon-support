package chameleon.support.statement;

import java.util.List;

import org.rejuse.association.OrderedMultiAssociation;
import org.rejuse.java.collections.Visitor;

import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.validation.BasicProblem;
import chameleon.core.validation.VerificationResult;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class SwitchStatement extends ExpressionContainingStatement<SwitchStatement> {

  public SwitchStatement(Expression expression) {
    super(expression);
  }

	/**
	 * SWITCH CASES
	 */
	private OrderedMultiAssociation<SwitchStatement,SwitchCase> _switchCases = new OrderedMultiAssociation<SwitchStatement,SwitchCase>(this);

  public OrderedMultiAssociation<SwitchStatement,SwitchCase> getCasesLink() {
    return _switchCases;
  }

  public void addCase(SwitchCase switchCase) {
    _switchCases.add(switchCase.parentLink());
  }
  
  public void addAllCases(List<SwitchCase> cases) {
  	for(SwitchCase kees: cases) {
  		addCase(kees);
  	}
  }

  public void removeCase(SwitchCase switchCase) {
    _switchCases.remove(switchCase.parentLink());
  }

  public List<SwitchCase> getSwitchCases() {
    return _switchCases.getOtherEnds();
  }

  public SwitchStatement clone() {
    final SwitchStatement result = new SwitchStatement(getExpression().clone());
    new Visitor() {
      public void visit(Object element) {
        result.addCase(((SwitchCase)element).clone());
      }
    }.applyTo(getSwitchCases());
    return result;
  }

  public List<Element> children() {
    List result = Util.createNonNullList(getExpression());
    result.addAll(getSwitchCases());
    return result;
  }
  
  @Override
  public VerificationResult verifySelf() {
  	VerificationResult result = super.verifySelf();
  	List<DefaultLabel> cases = descendants(DefaultLabel.class);
  	if(cases.size() > 1) {
  		result = result.and(new BasicProblem(this,"A switch statement can contain only one default label."));
  	}
  	return result;
  }

}
