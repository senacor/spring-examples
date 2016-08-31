package com.senacor.tecco.ilms.katas.views.e03_viewresolver.e04_xls;

/**
 * Created by fsubasi on 22.02.2016.
 */
import com.senacor.tecco.ilms.katas.views.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String errorMessage = "Model should include only 1 object of type User";
        if (map.values().size() != 2) {
            throw new IllegalArgumentException(errorMessage + ": " + map.values().size());
        }

        User user = null;
        Iterator iterator = map.values().iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object instanceof User) {
                user = (User) object;
            }
        }

        if (user == null) {
            throw new IllegalArgumentException(errorMessage);
        }

        //create a wordsheet
        Sheet sheet = workbook.createSheet("User Sheet");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Key");
        header.createCell(1).setCellValue("Value");

        Map<String, Object> userMap = mapProperties(user);

        int rowNum = 1;
        for (Map.Entry<String, Object> entry : userMap.entrySet()) {
            //create the row data
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue().toString());
        }
    }

    public static Map<String, Object> mapProperties(Object bean) throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers())
                    && method.getParameterTypes().length == 0
                    && method.getReturnType() != void.class
                    && method.getName().matches("^(get|is).+")
                    ) {
                String name = method.getName().replaceAll("^(get|is)", "");
                name = Character.toLowerCase(name.charAt(0)) + (name.length() > 1 ? name.substring(1) : "");
                Object value = method.invoke(bean);
                properties.put(name, value);
            }
        }
        return properties;
    }
}