package tool;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass; // Wrapper for Class <?>
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtNewConstructor;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.stackmap.TypeData.ClassName;

public class ClassTransformer {

	private static Object className;

	public static Class<?> transform() throws NotFoundException, IOException, CannotCompileException {
		
		//Get the class manipulation tool = pool
		ClassPool pool = ClassPool.getDefault();
		pool.appendClassPath("C:\\Users\\Славик\\Desktop\\Vadim\\workspace\\CodeTransform\\bin");
		//pool.appendClassPath("bin");
		// .appendClassPath - in case of errors
		className = ((String) className).replace("/", ".");
		// load the desired class
//			Object className1 = ((String) className1).replace("/", ".");
		CtClass cc = pool.get("original.Box");
		String packageName = ((StringBuffer) className).substring(0,((StringBuffer) className).indexOf("."));
		String classNamePackage = ((StringBuffer) className).substring(((StringBuffer) className).indexOf(".") + 1);
	    // find the default constructor
			CtConstructor ccon= cc.getDeclaredConstructor(null);
			
			if ( cc.hasAnnotation("AddConstructor") && !cc.isInterface() && !Modifier.
					isAbstract(cc.getModifiers() ) ) {
				
			
				
				StringBuffer args = new StringBuffer("public " + "(");
				StringBuffer body = new StringBuffer("{\n");
		
//	    // remove it
//			cc.removeConstructor(ccon);

	    // create a new default constructor
//			CtConstructor cstructor = CtNewConstructor.defaultConstructor( cc	) ;
			for (CtField cTfield : cc.getDeclaredFields()) {
			
				
				String type = cTfield.getType().getName();
				String name = cTfield.getName();
				
				args.append(type).append(" ");
				args.append(name).append(", ");
				
				body.append("this."+name).append("="+name);
				
				}
			
			cc.addConstructor(CtNewConstructor.make(args.append(body).toString(),cc));

//			cc.addConstructor(CtNewConstructor.make(args.append(body).toString(),cc));
	    // modify the constructor behavior
//			cstructor.setBody( "this.id = 100;");
//			// add the constructor to the class
//			cc.addConstructor(cstructor);
//	    // return the modified class
			return cc.toClass();
		
		
		
		
		
			}
		
		
		
			
			return null;
		
		
			
		
		
//		CtClass cc = pool.get("original.Box");
//		//cc.setSuperclass(pool.get("test.Point")); ->from documentation of javassist
//		
//		CtConstructor cscostructor = CtNewConstructor.make(
//				"public" + "Box" + "{" +
//						"id = 100;"
//					+"}", cc);
//		// add some default logic
//		cc.addConstructor(cscostructor);
//		
//		//save changed class
//		cc.writeFile();
	}
}
