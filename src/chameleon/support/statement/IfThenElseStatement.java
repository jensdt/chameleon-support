package chameleon.support.statement;


import java.util.List;

import org.rejuse.association.SingleAssociation;

import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.lookup.LookupException;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementImpl;
import chameleon.core.type.Type;
import chameleon.core.validation.BasicProblem;
import chameleon.core.validation.VerificationResult;
import chameleon.oo.language.ObjectOrientedLanguage;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class IfThenElseStatement extends ExpressionContainingStatement<IfThenElseStatement> {

  public IfThenElseStatement(Expression expression, Statement ifStatement, Statement elseStatement) {
    super(expression);
    setIfStatement(ifStatement);
    setElseStatement(elseStatement);
  }

	/**
	 * IF STATEMENT
	 */
	private SingleAssociation<IfThenElseStatement,Statement> _ifStatement = new SingleAssociation<IfThenElseStatement,Statement>(this);


  public void setIfStatement(Statement statement) {
  	setAsParent(_ifStatement, statement);
  }

  public Statement getIfStatement() {
    return _ifStatement.getOtherEnd();
  }

	/**
	 * ELSE STATEMENT
	 */
	private SingleAssociation<IfThenElseStatement,Statement> _elseStatement = new SingleAssociation<IfThenElseStatement,Statement>(this);

  public void setElseStatement(Statement statement) {
    setAsParent(_elseStatement, statement);
  }

  public Statement getElseStatement() {
    return _elseStatement.getOtherEnd();
  }


  public IfThenElseStatement clone() {
    Statement ifStatement = null;
    if(getIfStatement() != null) {
      ifStatement = getIfStatement().clone();
    }
    Statement elseStatement = null;
    if(getElseStatement() != null) {
      elseStatement = getElseStatement().clone();
    }
    return new IfThenElseStatement(getExpression().clone(), ifStatement, elseStatement);
  }

  public List<Element> children() {
    List<Element> result = Util.createNonNullList(getExpression());
    Util.addNonNull(getIfStatement(), result);
    Util.addNonNull(getElseStatement(), result);
    return result;
  }
  
  @Override
  public VerificationResult verifySelf() {
  	VerificationResult result = super.verifySelf();
  	try {
			Type expressionType = getExpression().getType();
			if(! expressionType.subTypeOf(language(ObjectOrientedLanguage.class).booleanType())) {
				result = result.and(new BasicProblem(getExpression(),"The type of the condition of the if statement is not a boolean type."));
			}
		} catch (LookupException e) {
			result = result.and(new BasicProblem(getExpression(),"Cannot determine the type of the condition of the if statement."));
		}
  	return result;
  }
}
