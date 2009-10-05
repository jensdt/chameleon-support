package chameleon.support.expression;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chameleon.core.element.Element;
import chameleon.core.expression.BinaryExpression;
import chameleon.core.expression.Expression;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.language.ObjectOrientedLanguage;
import chameleon.core.lookup.LookupException;
import chameleon.core.type.Type;
import chameleon.core.validation.BasicProblem;
import chameleon.core.validation.Valid;
import chameleon.core.validation.VerificationResult;


/**
 * @author Marko van Dooren
 */
public abstract class ConditionalBooleanExpression extends BinaryExpression {

  /**
   * @param first
   * @param second
   */
  public ConditionalBooleanExpression(Expression first, Expression second) {
    super(first, second);
  }
  
  protected Type actualType() throws LookupException {
    return language(ObjectOrientedLanguage.class).booleanType(); 
  }
  
  public Set<Type> getDirectExceptions() throws LookupException {
    return new HashSet<Type>();
  }
  
  public List<Element> children() {
    List<Element> result = new ArrayList<Element>();
    result.add(getFirst());
    result.add(getSecond());
    return result;
  }
  
}
