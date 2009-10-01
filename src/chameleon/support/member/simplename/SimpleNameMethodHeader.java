package chameleon.support.member.simplename;

import java.util.List;

import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.declaration.Declaration;
import chameleon.core.lookup.DeclarationSelector;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.Method;
import chameleon.core.method.MethodHeader;
import chameleon.core.namespace.Namespace;
import chameleon.core.namespacepart.NamespacePart;
import chameleon.core.validation.BasicProblem;
import chameleon.core.validation.Valid;
import chameleon.core.validation.VerificationResult;
import chameleon.core.variable.FormalParameter;

public class SimpleNameMethodHeader<E extends SimpleNameMethodHeader, P extends Method, S extends SimpleNameMethodSignature> extends MethodHeader<E, P, S>{

  public SimpleNameMethodHeader(String name) {
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
	public E cloneThis() {
		return (E) new SimpleNameMethodHeader(getName());
	}

	@Override
	public S signature() {
		SimpleNameMethodSignature result =  new SimpleNameMethodSignature(getName());
		result.setUniParent(parent());
		for(FormalParameter param: formalParameters()) {
			result.add(param.getTypeReference().clone());
		}
		return (S) result;
	}

	@Override
	public VerificationResult verifyThis() {
		VerificationResult result = Valid.create();
		if(getName() == null) {
			result = result.and(new BasicProblem(this, "The method has no name"));
		}
		return result;
	}

}
