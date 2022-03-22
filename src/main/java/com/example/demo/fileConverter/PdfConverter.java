package com.example.demo.fileConverter;

import com.example.demo.model.Transaction;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.OutputStream;
import java.util.List;

public class PdfConverter extends _BaseConverter{

    public PdfConverter(List<Transaction> listTransaction) {
        super(listTransaction);
    }

    @Override
    public void export(OutputStream out) throws DocumentException {
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, out);

        document.open();
        Paragraph paragraph = new Paragraph("List All Transaction", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        document.add(new Paragraph("source => recipient : amount : TransDate : Type" , font));
        document.add(new Paragraph("----------------------------------------------------------" , font));
        for (Transaction transaction : listTransaction) {
            document.add(new Chunk(transaction.transactionToString(), font));
        }

        document.close();
    }
}
