package chameleon.support.tool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chameleon.core.namespace.Namespace;
import chameleon.core.namespace.NamespaceOrTypeReference;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;
import chameleon.input.ModelFactory;
import chameleon.input.ParseException;

/**
 * @author Tim Laeremans
 */
public class ArgumentParser {
  
 /*@
   @ public behavior
   @
   @ pre factory != null;
   @
   @ post getFactory() == factory;
   @*/
  public ArgumentParser(ModelFactory factory, boolean output) {
    _factory = factory;
    _output = output;
  }
  
 /*@
   @ public behavior
   @
   @ pre factory != null;
   @
   @ post getFactory() == factory;
   @ post getOutput() == true;
   @*/
  public ArgumentParser(ModelFactory factory) {
   this(factory, true);
 }
 
  private boolean _output;
  
  /**
   * Check wether or not the first argument is the output directory.
   */
  public boolean getOutput() {
    return _output;
  }
  
 /*@
   @ public behavior
   @
   @ post \result != null;
   @*/
  public ModelFactory getFactory() {
    return _factory;
  }

	private ModelFactory _factory;

	  /**
	   * The first argument is structured as follows:
	   * outputDir?
	   * inputDir+
	   * @packageName : recursive
	   * #packageName : direct
	   * %packageName : 
	   * 
	   * The extension argument is e.g. ".java"
	   */
  public Arguments parse(String[] args, String extension) throws ParseException, MalformedURLException, FileNotFoundException, IOException, Exception {
    int low = (getOutput() ? 1 : 0);
   // ArrayList al = new ArrayList();
    Set files = new HashSet();
   // Set files = new FileSet();
    for(int i = low; i < args.length;i++) {
    	if(! args[i].startsWith("@") && ! args[i].startsWith("#")&& ! args[i].startsWith("%")) {
        //files.include(new PatternPredicate(new File(args[i]), new FileNamePattern("**/*.csharp")));
    		files.addAll(getFactory().loadFiles(args[i],extension,true));
      }
    }
    System.out.println("Parsing "+files.size() +" files.");
    Namespace mm = _factory.getMetaModel(files);
    Set<Type> types = new HashSet<Type>();
    
    for(int i = low; i < args.length;i++) {
      if(args[i].startsWith("@")) {
      	NamespaceOrTypeReference ref= new NamespaceOrTypeReference(args[i].substring(1));
      	ref.setUniParent(mm);
      	Namespace ns = ref.getNamespace();
        types.addAll(ns.allDeclarations(Type.class));
      }
    }
    for(int i = low; i < args.length;i++) {
      if(args[i].startsWith("#")) {
      	NamespaceOrTypeReference ref= new NamespaceOrTypeReference(args[i].substring(1));
      	ref.setUniParent(mm);
      	Namespace ns = ref.getNamespace();
        types.addAll(ns.declarations(Type.class));
      }
    }
    for(int i = low; i < args.length;i++) {
      if(args[i].startsWith("$")) {
      	TypeReference ref= new TypeReference(args[i].substring(1));
      	ref.setUniParent(mm);
        types.add(ref.getType());
      }
    }
//    new PrimitiveTotalPredicate() {
//      public boolean eval(Object o) {
//        return (! (o instanceof ArrayType));
//      }
//    }.filter(types);
    List arguments = new ArrayList();
    for(int i = low; i < args.length;i++) {
      if(args[i].startsWith("%")) {
        arguments.add(args[i].substring(1));
      }
    }
    if(getOutput()) {
    	return new Arguments(args[0], mm, files, types, arguments);
    }else {
    	return new Arguments(null, mm, files, types, arguments);
    }
  }
  
//  static class OutputParserFactory implements ILinkageFactory{
//
//	public ILinkage createLinkage(File file) {
//		return new ILinkage(){
//
//			public IParseErrorHandler getParseErrorHandler() {
//				return null;
//			}
//
//			public String getSource() {
//				return null;
//			}
//
//			public void decoratePosition(int offset, int length, String dectype, Element el) {
//			}
//
//			public int getLineOffset(int i) {
//				return 0;
//			}
//
//			public void addCompilationUnit(CompilationUnit cu) {
//				
//			}};
//	}}
}
