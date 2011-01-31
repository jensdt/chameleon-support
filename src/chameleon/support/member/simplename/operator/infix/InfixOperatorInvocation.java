package chameleon.support.member.simplename.operator.infix;


import chameleon.core.expression.InvocationTarget;
import chameleon.core.lookup.DeclarationSelector;
import chameleon.core.lookup.LookupException;
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
    return (InfixOperator)getElement();
  }

  protected InfixOperatorInvocation cloneInvocation(InvocationTarget target) {
    return new InfixOperatorInvocation(name(), target);
  }

  @Override
  public DeclarationSelector<InfixOperator> createSelector() {
    return new SimpleNameMethodSelector() {
      @Override
      public Class<InfixOperator> selectedClass() {
        return InfixOperator.class;
      }

    };
  }

}
