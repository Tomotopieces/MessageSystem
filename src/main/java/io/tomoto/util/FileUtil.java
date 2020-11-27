package io.tomoto.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Util class for web file download and upload.
 *
 * @author Tomoto
 * <p>
 * 2020/11/27 15:16
 */
public class FileUtil {

    public static void download(HttpServletRequest request, HttpServletResponse response, String filePath) throws IOException {
        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);

        response.setContentType("image/png");

        response.setHeader("Content-Disposition", "attachment; fileName=" + fileName);
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
             ServletOutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
                out.flush();
            }
        } // out will close automatically
    }

    public static void upload(HttpServletRequest request, HttpServletResponse response, String filePath) throws Exception {
        if (ServletFileUpload.isMultipartContent(request)) {
            ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> items = servletFileUpload.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) { // file item
                    File file = new File(filePath);
                    if (file.exists()) {
                        file.delete();
                    }
                    item.write(file);
                }
            }
        }
    }
}
