package nameCheckProcessor;

import javax.annotation.processing.Messager;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic.Kind;

/**
 * 程序名称规范的编译器插件
 * 如果程序命名不合规范, 将会输出一个编译器的warning信息
 * @author Administrator
 *
 */
public class NameChecker {
	
	private final Messager messager = new Messager() {
		
		@Override
		public void printMessage(Kind kind, CharSequence msg, Element e, AnnotationMirror a, AnnotationValue v) {
		}
		
		@Override
		public void printMessage(Kind kind, CharSequence msg, Element e, AnnotationMirror a) {
		}
		
		@Override
		public void printMessage(Kind kind, CharSequence msg, Element e) {
		}
		
		@Override
		public void printMessage(Kind kind, CharSequence msg) {
		}
	};
	
	
	
	/**
	 * 名称检查器
	 * 将会以Visitor模式访问抽象语法树中的元素
	 * @author Administrator
	 *
	 */
	class NameCheckScanner extends ElementScanner8<Void, Void>{
		/**
		 * 此方法用于检查Java类
		 */
		@Override
		public Void visitType(TypeElement e, Void p) {
			scan(e.getTypeParameters(), p);
			
			return super.visitType(e, p);
		}
		
		private void checkCamerCase(Element e,boolean initialCaps){
			String name = e.getSimpleName().toString();
			boolean previousUpper = false;
			boolean convertional = true;
			int firstCodePoint = name.codePointAt(0);
		}
	}



}
