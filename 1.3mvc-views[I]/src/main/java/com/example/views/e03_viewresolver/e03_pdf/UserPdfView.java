package com.example.views.e03_viewresolver.e03_pdf;

import com.example.views.User;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by fsubasi on 22.02.2016.
 * This is a simple PDF View that creates a table with User's properties
 */
public class UserPdfView implements View {
    @Override
    public String getContentType() {
        return "application/pdf";
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String errorMessage = "Model should include only 1 object of type User";
        if(map.values().size() != 2){
            throw new IllegalArgumentException(errorMessage + ": " + map.values().size());
        }

        User user = null;
        Iterator iterator = map.values().iterator();
        while(iterator.hasNext()){
            Object object = iterator.next();
            if(object instanceof User){
                user = (User) object;
            }
        }

        if(user == null){
            throw new IllegalArgumentException(errorMessage);
        }

        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage( page );

        PDPageContentStream contentStream = new PDPageContentStream(doc, page);

        String[][] content = {{"First name",user.getFirstName()},
                {"Last name",user.getLastName()},
                {"Email",user.getEmail()}} ;

        drawTable(page, contentStream, 700, 100, content);
        contentStream.close();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        doc.save(baos);
        doc.close();
        response.setContentType("application/pdf");
        // response.setHeader("Content-Disposition", "attachment; filename=\"user.pdf\"");
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
    }

    /**
     * @param page
     * @param contentStream
     * @param y the y-coordinate of the first row
     * @param margin the padding on left and right of table
     * @param content a 2d array containing the table data
     * @throws IOException
     */
    public static void drawTable(PDPage page, PDPageContentStream contentStream,
                                 float y, float margin,
                                 String[][] content) throws IOException {
        final int rows = content.length;
        final int cols = content[0].length;
        final float rowHeight = 20f;
        final float tableWidth = page.findMediaBox().getWidth() - margin - margin;
        final float tableHeight = rowHeight * rows;
        final float colWidth = tableWidth/(float)cols;
        final float cellMargin = 5f;

        //draw the rows
        float nexty = y ;
        for (int i = 0; i <= rows; i++) {
            contentStream.drawLine(margin, nexty, margin + tableWidth, nexty);
            nexty -= rowHeight;
        }

        //draw the columns
        float nextx = margin;
        for (int i = 0; i <= cols; i++) {
            contentStream.drawLine(nextx, y, nextx, y-tableHeight);
            nextx += colWidth;
        }

        //now add the text
        contentStream.setFont(PDType1Font.HELVETICA , 12);

        float textx = margin+cellMargin;
        float texty = y - 15;
        for(int i = 0; i < content.length; i++){
            for(int j = 0 ; j < content[i].length; j++){
                String text = content[i][j];
                contentStream.beginText();
                contentStream.moveTextPositionByAmount(textx,texty);
                contentStream.drawString(text);
                contentStream.endText();
                textx += colWidth;
            }
            texty -= rowHeight;
            textx = margin+cellMargin;
        }
    }
}
