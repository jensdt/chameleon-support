package chameleon.support.member.simplename;

import java.util.ArrayList;
import java.util.List;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.Signature;
import chameleon.core.method.Method;
import chameleon.core.method.MethodSignature;

public class SimpleNameMethodSignature extends MethodSignature<SimpleNameMethodSignature, Method>{

  public SimpleNameMethodSignature(String name) {
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
  public SimpleNameMethodSignature cloneThis() {
    return new SimpleNameMethodSignature(getName());
  }

	public boolean sameAs(Signature other) throws MetamodelException {
		boolean result = false;
		if(other instanceof SimpleNameMethodSignature) {
			SimpleNameMethodSignature sig = (SimpleNameMethodSignature) other;
			result = getName().equals(sig.getName()) && sameParameterTypesAs(sig);
		}
		return result;
	}

	@Override
	public List<String> identifiers() {
		List<String> result = new ArrayList<String>();
		result.add(getName());
		return result;
	}

}
