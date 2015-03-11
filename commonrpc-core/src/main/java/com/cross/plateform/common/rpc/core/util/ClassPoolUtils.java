/**
 * 
 */
package com.cross.plateform.common.rpc.core.util;

import java.lang.reflect.Modifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

/**
 * @author liubing1
 *
 */
public class ClassPoolUtils {
	
	private static final Log LOGGER = LogFactory.getLog(ClassPoolUtils.class);

	/**
	 * 获取方法的参数变量名称
	 * @param classname
	 * @param methodname
	 * @return
	 */
	public static String[] getMethodVariableName(String classname,String methodname){
		 try{
			 ClassPool pool = ClassPool.getDefault();  
	         CtClass cc = pool.get(classname);  
	         CtMethod cm = cc.getDeclaredMethod(methodname);
	         MethodInfo methodInfo = cm.getMethodInfo();  
             CodeAttribute codeAttribute = methodInfo.getCodeAttribute();  
             String[] paramNames = new String[cm.getParameterTypes().length];
             LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);  
             if (attr != null)  {
                 int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;  
                 for (int i = 0; i < paramNames.length; i++){
                	 paramNames[i] = attr.variableName(i + pos); 
                 }
                 return paramNames;
             }
		 }catch(Exception e){
			 LOGGER.error("getMethodVariableName fail ",e);
		 }
		 return null;
	}
}
