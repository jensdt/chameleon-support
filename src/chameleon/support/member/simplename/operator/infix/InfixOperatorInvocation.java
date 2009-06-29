package chameleon.support.member.simplename.operator.infix;


import chameleon.core.context.DeclarationSelector;
import chameleon.core.context.LookupException;
import chameleon.core.expression.InvocationTarget;
import chameleon.support.member.simplename.SimpleNameMethodInvocation;


/**
 * @author Marko van Dooren
 */
public class InfixOperatorInvocation extends SimpleNameMethodInvocation<InfixOperatorInvocation,InfixOperator> {

  /**
   * @param target
   * @param name
   */
  public InfixOperatorInvocation(String name, InvocationTarget target) {
    super(target, name);
  }

  public InfixOperator getInfixOperator() throws LookupException {
    return (InfixOperator)getMethod();
  }

  protected InfixOperatorInvocation cloneInvocation(InvocationTarget target) {
    return new InfixOperatorInvocation(getName(), target);
  }

  @Override
  public DeclarationSelector<InfixOperator> selector() {
    return new SimpleNameMethodSelector() {
      @Override
      public Class<InfixOperator> selectedClass() {
        return InfixOperator.class;
      }

    };
  }

}
