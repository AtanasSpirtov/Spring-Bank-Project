package com.example.demo.controller;

import com.example.demo.fileConverter.ExcelConverter;
import com.example.demo.fileConverter.PdfConverter;
import com.example.demo.fileConverter._BaseConverter;
import com.itextpdf.text.DocumentException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Controller
@RequestMapping("/ViewInFile")
@PreAuthorize("hasAuthority('makeAll')")
public class ListInFilesController extends _BaseController {

    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    @PostMapping(value = "/ExcelReport", produces = "application/zip")
    public void excelReport(HttpServletResponse response,
                            @RequestParam long id,
                            @RequestParam boolean isZipped) throws IOException, DocumentException {
        if (isZipped) {
            ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
            ZipEntry entry = new ZipEntry("transactions.xlsx");
            zipOutputStream.putNextEntry(entry);
            response.setContentType("application/zip");
            response.setHeader(CONTENT_DISPOSITION, "attachment; filename=transactions.zip");
            ((_BaseConverter) new ExcelConverter(transactionService.listAllTransactions(id)))
                    .export(zipOutputStream);
            zipOutputStream.close();
        } else {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader(CONTENT_DISPOSITION, "attachment; filename=transactions.xlsx");
            ((_BaseConverter) new ExcelConverter(transactionService.listAllTransactions(id)))
                    .export(response.getOutputStream());
        }
    }

    @PostMapping(value = "/PdfReport")
    public void pdfReport(HttpServletResponse response,
                          @RequestParam long id,
                          @RequestParam boolean isZipped) throws IOException, DocumentException {
        if(isZipped)
        {
            ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
            ZipEntry entry = new ZipEntry("transactions.pdf");
            zipOutputStream.putNextEntry(entry);
            response.setContentType("application/zip");
            response.setHeader(CONTENT_DISPOSITION, "attachment; filename=transactions.zip");
            ((_BaseConverter) new PdfConverter(transactionService.listAllTransactions(id)))
                    .export(zipOutputStream);
            zipOutputStream.close();
        }
        else {
            response.setContentType("application/pdf");
            response.setHeader(CONTENT_DISPOSITION, "attachment; filename=transactions.pdf");

            ((_BaseConverter) new PdfConverter(transactionService.listAllTransactions(id))).export(response.getOutputStream());
        }
    }

}
