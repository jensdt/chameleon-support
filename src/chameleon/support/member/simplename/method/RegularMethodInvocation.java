package chameleon.support.member.simplename.method;


import chameleon.core.expression.InvocationTarget;
import chameleon.core.lookup.DeclarationSelector;
import chameleon.support.member.simplename.SimpleNameMethodInvocation;


/**
 * @author Marko van Dooren
 */
public class RegularMethodInvocation extends SimpleNameMethodInvocation<RegularMethodInvocation,NormalMethod> {

  public RegularMethodInvocation(String name, InvocationTarget target) {
    super(target, name);
  }

  /********
   * MISC *
   ********/
  
  protected RegularMethodInvocation cloneInvocation(InvocationTarget target) {
    return new RegularMethodInvocation(getName(), target);
  }

  @Override
  public DeclarationSelector<NormalMethod> selector() {
    return new SimpleNameMethodSelector() {
        @Override
        public Class<NormalMethod> selectedClass() {
          return NormalMethod.class;
        }

      };
  }

}
