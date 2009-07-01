/**
 * 
 */
package chameleon.support.member;

import java.util.Iterator;
import java.util.List;

import chameleon.core.lookup.LookupException;
import chameleon.core.relation.WeakPartialOrder;
import chameleon.core.type.Type;

public class MoreSpecificTypesOrder extends WeakPartialOrder<List<Type>> {
  @Override
  public boolean contains(List<Type> first, List<Type> second)
      throws LookupException {
//    MethodSignature firstSig = first.signature();
//    MethodSignature secondSig = second.signature();
    boolean result = first.size() == second.size();
    Iterator<Type> firstIter = first.iterator();
    Iterator<Type> secondIter = second.iterator();
    while(result && firstIter.hasNext()) {
      Type firstType = firstIter.next();
      Type secondType = secondIter.next();
      result = result && firstType.assignableTo(secondType);
    }
    return result;
  }
}