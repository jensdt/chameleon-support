package chameleon.support.statement;

import java.util.List;

import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.language.ObjectOrientedLanguage;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.exception.TypeExceptionDeclaration;
import chameleon.core.statement.CheckedExceptionList;
import chameleon.core.statement.ExceptionPair;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;
import chameleon.core.validation.BasicProblem;
import chameleon.core.validation.Valid;
import chameleon.core.validation.VerificationResult;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class ThrowStatement extends ExpressionContainingStatement<ThrowStatement> {

 /*@
   @ public behavior
   @
   @ post getExpression() == expression;
   @*/
  public ThrowStatement(Expression expression) {
    super(expression);
  }

  public ThrowStatement clone() {
    return new ThrowStatement(getExpression().clone());
  }

 /*@
   @ also public behavior
   @
   @ post \result.contains(getExpression());
   @ post \result.size() == 1;
   @*/
  public List children() {
    return Util.createNonNullList(getExpression());
  }

  public CheckedExceptionList getDirectCEL() throws LookupException {
	    CheckedExceptionList cel = new CheckedExceptionList();
	    Type type = getExpression().getType();
	    TypeReference tr = new TypeReference(null, type.getFullyQualifiedName());
	    TypeExceptionDeclaration ted = new TypeExceptionDeclaration(tr);
	    cel.add(new ExceptionPair(type, ted, this));
	    return cel;
	  }

	@Override
	public VerificationResult verifyThis() {
		try {
		  Expression expr = getExpression();
			if(expr != null && language(ObjectOrientedLanguage.class).isException(expr.getType())) {
				return Valid.create();
			} else {
				return new ExpressionIsNoException(this);
			}
		} catch (LookupException e) {
			return new ExpressionIsNoException(this);
		}
	}
	
	public static class ExpressionIsNoException extends BasicProblem {

		public ExpressionIsNoException(Element element) {
			super(element,"The expression is not an exception.");
		}
		
	}
    
}
