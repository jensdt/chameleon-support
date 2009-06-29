package chameleon.support.member.simplename.operator.prefix;


import chameleon.core.context.DeclarationSelector;
import chameleon.core.expression.InvocationTarget;
import chameleon.support.member.simplename.SimpleNameMethodInvocation;

/**
 * @author Marko van Dooren
 */
public class PrefixOperatorInvocation extends SimpleNameMethodInvocation<PrefixOperatorInvocation,PrefixOperator> {

  /**
   * @param target
   * @param name
   */
  public PrefixOperatorInvocation(String name, InvocationTarget target) {
    super(target, name);
  }

  protected PrefixOperatorInvocation cloneInvocation(InvocationTarget target) {
    return new PrefixOperatorInvocation(getName(), target);
  }

  @Override
  public DeclarationSelector<PrefixOperator> selector() {
    return new SimpleNameMethodSelector() {
      @Override
      public Class<PrefixOperator> selectedClass() {
        return PrefixOperator.class;
      }

    };
  }
  
}
