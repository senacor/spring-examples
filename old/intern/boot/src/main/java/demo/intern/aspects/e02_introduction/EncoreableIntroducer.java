package demo.intern.aspects.e02_introduction;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * Created by fsubasi on 06.01.2016.
 * Introduction Demo: Here we show how beans can be enhanced with new methods using
 * introduction. With introduction, a bean"s implementation is
 * effectively divided into multiple classes.
 */
@Aspect
public class EncoreableIntroducer {
    // static property that is annotated by @DeclareParents specifies the interface that's to be introduced
    @DeclareParents(
            value="demo.intern.aspects.Performance+", // identifies the kinds of beans that should be introduced with the interface
            defaultImpl=DefaultEncoreable.class) // class that provides the implementation for the introduction
    public static Encoreable encoreable;
}
