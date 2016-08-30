package de.postbank.fsl.service.halloWelt;

import de.postbank.fsl.common.annotations.FSLService;
import de.postbank.fsl.common.components.CommonFSLComponents;
import de.postbank.fsl.common.util.FSLDefaultMBeanInitialization;
import de.postbank.fsl.common.util.ProfileHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@FSLService
@ComponentScan(basePackageClasses = {Application.class,CommonFSLComponents.class})
public class Application {


    public static void main(String[] args) {
        org.apache.commons.logging.LogFactory.getLog(Application.class).error("commons logging tut");
        
        SpringApplication app = new SpringApplication(Application.class);
        // Wenn das Flag true ist, werden nur valide Profile akzeptiert, nur zu Test/Experimentierzwecken auf false umstellen!
        app.addListeners(new ProfileHelper(true));
        app.addListeners(new FSLDefaultMBeanInitialization());
        app.run(args);
    }
}
