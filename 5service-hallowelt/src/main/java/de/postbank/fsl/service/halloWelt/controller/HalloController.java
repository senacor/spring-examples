package de.postbank.fsl.service.halloWelt.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import de.postbank.fsl.service.halloWelt.model.Hallo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import de.postbank.fsl.common.components.filter.MBeansFilter;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
@RestController
public class HalloController {
    Log log = LogFactory.getLog(HalloController.class);
    @RequestMapping(value = "/hallo/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "antwortet dir mit mit einer netten Begruessung'", notes = "Ein einfacher Testendpunkt, der antwortet wenn man mit ihm spricht")
    public ResponseEntity<Hallo> hallo_name_get(
            @PathVariable
            @ApiParam(value = "Der Name der in der Begrüßung auftauchen soll") String name ) {
        log.info( "in hallo-welt angekommen mit " + name );
        log.debug( "in hallo-welt angekommen mit " + name );
        log.error( "in hallo-welt angekommen mit " + name );
        if( "throwme".equals( name ) )
            throw new RuntimeException( "throwme geworfen" );
        return new ResponseEntity<>(new Hallo(name), HttpStatus.OK);
    }
}
