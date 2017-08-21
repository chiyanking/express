package com.dagemen.Utils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class PdfUtil {


    /**
     * 生成pdf模板
     * @param jsonObject
     * @param inPdfPath
     * @return
     */
    public static byte[] creatPdf(Object jsonObject, String inPdfPath) {

        Map map = (Map)jsonObject;
        // 需要生成后的PDF
        byte[] bytes=null;
        PdfStamper stamp = null;
        PdfReader reader = null;
        ByteOutputStream byteOutputStream = null;
        try {
            byteOutputStream = new ByteOutputStream();
            reader = new PdfReader(inPdfPath);
            stamp = new PdfStamper(reader, byteOutputStream);
            AcroFields form = stamp.getAcroFields();
            insertText(form, map);
//            insertImage(form,map,stamp);
            // 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamp.setFormFlattening(true);
            bytes = byteOutputStream.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != stamp) {
                try {
                    stamp.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != reader) {
                reader.close();
            }
            byteOutputStream.close();
        }
        return bytes;
    }

    /**
     * 向pdf中插入文字
     * @param form pdf的表单
     * @param map 要嵌入的值
     */
    public static void insertText(AcroFields form, Map<String, String> map) throws IOException, DocumentException {
        Iterator<String> it = form.getFields().keySet().iterator();
        while (it.hasNext()) {
            String name = it.next().toString();
            if (null != map.get(name) && !name.contains("img")) {
                form.setField(name, map.get(name));
            }
        }
    }

//    public void insertImage(AcroFields form, Map<String, String> map, PdfStamper stamp) throws Exception {
//        if(map.size() > 0 && null != map){
//            for(Map.Entry<String, String> entry : map.entrySet()){
//                if(entry.getKey().contains("img")){
//                    insertPictureToPdf(form, stamp, entry.getKey(), entry.getValue());
//                }
//            }
//        }
//    }


//    /**
//     * 向pdf中插入图片
//     * @param form
//     * @param pdfStamper
//     * @param field 选择的区域
//     * @param url 值
//     * @throws Exception
//     */
//    private static void insertPictureToPdf(AcroFields form, PdfStamper pdfStamper, String field, String url) throws Exception {
//
//        List<AcroFields.FieldPosition> fieldPositions = form.getFieldPositions(field);
//        if (null == fieldPositions || fieldPositions.size() <= 0) {
//            return;
//        }
//        // 获取插入图片位置所在页数
//        int pageNo = fieldPositions.get(0).page;
//        Rectangle signRect = form.getFieldPositions(field).get(0).position;
//        // 获得图片位置在当前页的坐标
//        float x = signRect.getLeft();
//        float y = signRect.getBottom();
//        PdfContentByte over = pdfStamper.getOverContent(pageNo);// 设置在第几页打印印章
//
//        Image img = Image.getInstance(base64ToImage(url));
//        img.setAlignment(Image.ALIGN_LEFT);// 设置图片对齐方式
//        float height = img.getHeight();
//        float width = img.getWidth();
//        img.scaleAbsolute(baseSealWidth, height * baseSealWidth / width);// 控制图片大小
//        img.setAbsolutePosition(x, y);// 控制图片位置
//
//        over.addImage(img);
//    }


    /**
     * pdf转图片
     * @param filePath
     * @param filename
     * @throws IOException
     */
    public int pdfToImage(String filePath, String filename, Integer startPage, Integer endPage)
            throws IOException {
        String fullPath = filePath + File.separator + filename;
        File pdfFile = new File(fullPath);
        PDDocument document = null;
        document = PDDocument.load(pdfFile);
        PDFRenderer renderer = new PDFRenderer(document);
        int numPages = document.getNumberOfPages();
        int count = 1;
        if (null == startPage || null == endPage) {
            for (int i = 0; i < numPages; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 150);
                File imageFile = new File(filePath + File.separator
                        + FilenameUtils.getBaseName(filename) + File.separator + FilenameUtils.getBaseName(filename) + "_"
                        + count++
                        + ".jpg");
                imageFile.mkdirs();
                ImageIO.write(image, "jpg", imageFile);
            }
            return numPages;
        }
        for (int i = startPage; i < endPage + 1; i++) {
            BufferedImage image = renderer.renderImageWithDPI(i, 150);
            File imageFile = new File(filePath + File.separator
                    + FilenameUtils.getBaseName(filename) + File.separator
                    + count++
                    + ".jpg");
            imageFile.mkdirs();
            ImageIO.write(image, "jpg", imageFile);
        }
        return numPages;
    }

    /**
     * 转换时间
     * @param data
     * @param type
     * @return
     */
    public static String formatDate(Date data, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        return sdf.format(data);
    }

    public static Image base64ToImage(String base64String) {
        byte[] bytes= com.itextpdf.text.pdf.codec.Base64.decode(base64String);
        Image img = null;
        try {
            img = Image.getInstance(bytes);
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}
