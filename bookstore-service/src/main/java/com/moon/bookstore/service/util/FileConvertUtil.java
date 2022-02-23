package com.moon.bookstore.service.util;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;

import java.io.*;

/**
 * @author yujiangtao
 * @date 2021/11/2 下午4:43
 */
public class FileConvertUtil {

    private static final String DEFAULT_SUFFIX = "pdf";

    private static final Integer OPENOFFICE_PORT = 8100;

    public static InputStream convertLocaleFile(String sourcePath, String suffix) {
        try {
            File inputFile = new File(sourcePath);
            InputStream inputStream = new FileInputStream(inputFile);
            return convertCommonByStream(inputStream, suffix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream convertCommonByStream(InputStream inputStream, String suffix) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(OPENOFFICE_PORT);
        connection.connect();
        DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
        DefaultDocumentFormatRegistry formatRegistry = new DefaultDocumentFormatRegistry();
        DocumentFormat targetFormat = formatRegistry.getFormatByFileExtension(DEFAULT_SUFFIX);
        DocumentFormat sourceFormat = formatRegistry.getFormatByFileExtension(suffix);
        converter.convert(inputStream, sourceFormat, out, targetFormat);
        connection.disconnect();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
