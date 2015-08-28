/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2013 
 * 日    期：13-2-6
 */
package com.willow.platform.report;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.lowagie.text.pdf.BaseFont;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
public class FlyingsaucerTest {


    /**
     * 调 用方式 htmlToPDF(response.getOutputStream(), htmlstr, "C:/WINDOWS/Fonts /simfang.ttf", "FangSong_GB2312", "GB2312", "http://xxxx/dtd/xhtml1-transitional.dtd");
     * <p/>
     * 相关图片必须使用绝对路径才能显示 如果要下载生成的文件，在此方法上方加上：
     * response.setHeader("Content-disposition",
     * "attachment;filename=英文文件名.pdf");
     *
     * @param out        输出流
     * @param htmlcode   html代码。
     * @param fontName   字体名称 如：FangSong_GB2312
     * @param fontpath   windows 字体路径 如:C:/WINDOWS/Fonts/simfang.ttf
     * @param encoding   编码 如:GB2312。
     * @param htmlDTDURL 如：http://xxxx/xhtml1-transitional.dtd
     *                   version 2009.03.05
     */
    public void htmlToPDF(OutputStream out, String htmlcode, String fontpath,
                          String fontName, String encoding, String htmlDTDURL) throws Exception {
//        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        ITextRenderer renderer = new ITextRenderer();
        InputStream intream = null;
        Document doc = null;
        Document doc2 = null;
        try {
            htmlcode = this.filterHeader(htmlcode, encoding, fontName, htmlDTDURL);

            intream = new ByteArrayInputStream(htmlcode.getBytes(encoding));
//            doc = (Document) builder.parse(intream);
//            transformer.setOutputProperty("encoding", encoding);
//            transformer.transform(new DOMSource(doc), new StreamResult(bos));
//            intream = new ByteArrayInputStream(bos.toString().getBytes());
//            doc2 = (Document) builder.parse(intream);

            ITextFontResolver resolver = renderer.getFontResolver();
            resolver.addFont(fontpath, BaseFont.IDENTITY_H,
                    BaseFont.NOT_EMBEDDED);

            renderer.setDocument(htmlcode);
//            renderer.setDocument(doc2, null);
            renderer.layout();
            renderer.createPDF(out, true);
            out.flush();
            out.close();
        } catch (Exception ex) {

            throw new Exception(ex.getMessage());
        }
    }

    /**
     * 文件头
     */
    private String filterHeader(String htmlcode, String encoding, String fontName, String htmlDTDURL) {
        htmlcode = "<table style=\"font-family:" + fontName + "\" width=\"100%\" height=\"100%\" border=\"0\"><tr><td>" + htmlcode + "</td></tr></table>";
        htmlcode = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"  \"" + htmlDTDURL + "\" >\n" + htmlcode;
        htmlcode = "<?xml version=\"1.0\" encoding=\"" + encoding + "\" ?>\n"
                + htmlcode;
        return htmlcode;
    }


    @Test
    public void htmltopdfTest() throws Exception {
        String outputFile = "D:/flying.pdf";
        OutputStream os = new FileOutputStream(outputFile);
        FlyingsaucerTest pdfTest = new FlyingsaucerTest();
        String html = "first pdf ,我的第一pdf<br/><span style=\"color: red;\">好的</span>";
        pdfTest.htmlToPDF(os, html, "C:/Windows/Fonts/ARIALUNI.TTF", "Arial Unicode MS", "UTF-8", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd");

    }


    @Test
    public void ItextRendererTest() {
        ITextRenderer renderer = new ITextRenderer();
    }
}
