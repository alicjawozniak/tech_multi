package com.springapp.mvc;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class FilterController implements ServletContextAware {

    private ServletContext servletContext;

    private static final Logger logger = LoggerFactory
            .getLogger(FilterController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView filters(ModelMap model) {

        return new ModelAndView("filter", "command", new Filter());
    }


    @RequestMapping(value = "/addFilter", method = RequestMethod.POST)
    public void addFilter(@ModelAttribute("SpringWeb") Filter filter,
                          ModelMap model, HttpServletResponse httpServletResponse) throws Exception {


        MultipartFile file = filter.getImage();
        File photoFile = null;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                String rootPath = System.getProperty("catalina.home");
                File dir = new File("/home/alicja/Desktop/projekty_src/tech_multi/FiltrObrazow");

                photoFile = new File(dir.getAbsolutePath()
                        + File.separator + "new_" + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(photoFile));
                stream.write(bytes);
                stream.close();

                logger.info("Server File Location="
                        + photoFile.getAbsolutePath());


                FiltersImplementation filtersImplementation = new FiltersImplementation(filter, photoFile);

                byte[] imgByte = FileUtils.readFileToByteArray(filtersImplementation.getOutputFile());

                httpServletResponse.setHeader("Cache-Control", "no-store");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("image/jpeg");
                ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
                responseOutputStream.write(imgByte);
                responseOutputStream.flush();
                responseOutputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


}