package com.willow.platform.common.helper;

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author 朱贤俊
 *         功能说明:
 */
public class StreamHelper {

    public static final int BUFFER_SIZE = 1024 * 8;

    public static String copyToString(Reader in) throws IOException {
        StringWriter out = new StringWriter();
        copy(in, out);
        return out.toString();
    }

    public static int copy(Reader in, Writer out) throws IOException {
        Preconditions.checkNotNull(in, "No Reader specified");
        Preconditions.checkNotNull(out, "No Writer specified");
        try {
            int byteCount = 0;
            char[] buffer = new char[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                byteCount += bytesRead;
            }
            out.flush();
            return byteCount;
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                // do nothing
            }
            try {
                out.close();
            } catch (IOException ex) {
                // do nothing
            }
        }
    }

}
