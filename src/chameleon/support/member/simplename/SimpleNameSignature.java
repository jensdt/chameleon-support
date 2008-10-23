package chameleon.support.member.simplename;

import chameleon.core.MetamodelException;
import chameleon.core.method.Method;
import chameleon.core.method.MethodSignature;
import chameleon.core.type.Type;

public class SimpleNameSignature extends MethodSignature<SimpleNameSignature, Method>{

  public SimpleNameSignature(String name) {
    setName(name);
  }
  
  public String getName() {
    return _name;
  }
  
  public void setName(String name) {
    _name = name;
  }
  
  private String _name;

  @Override
  public SimpleNameSignature cloneThis() {
    return new SimpleNameSignature(getName());
  }

	public boolean sameAs(Object other) throws MetamodelException {
		boolean result = false;
		if(other instanceof SimpleNameSignature) {
			SimpleNameSignature sig = (SimpleNameSignature) other;
			result = getName().equals(sig.getName()) && sameParameterTypesAs(sig);
		}
		return result;
	}

}
