package nameCheckProcessor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
//*表示支持所有的Annotation
@SupportedAnnotationTypes("*")
//只支持1.8的代码
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor{

	//private NameChecker nameChecker;
	
	/**
	 * 初始化名称检查插件
	 */
	@Override
	public void init(ProcessingEnvironment processingEnv) {
		
		super.init(processingEnv);
		//nameChecker = new NameChecker(processingEnv);
	}

	/**
	 * 对输入的语法树的各个节点进行名称检查
	 * annotations 获取要处理的注解集合
	 * roundEnv 这里可以访问到这个round中的语法树节点
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if (!roundEnv.processingOver()) {
			for (Element element : roundEnv.getRootElements()) {
				
			}
		}
		return false;
	}
	
}
