package com.example.demo.fileConverter;

import com.example.demo.model.Transaction;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
public class ExcelConverter extends _BaseConverter{

    public ExcelConverter(List<Transaction> listTransaction) {
        super(listTransaction);
    }

    @Override
    public void export(OutputStream out) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("transactions.xlsx");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Id");
            headerRow.createCell(1).setCellValue("Source");
            headerRow.createCell(2).setCellValue("Recipient");
            headerRow.createCell(3).setCellValue("Amount");
            headerRow.createCell(4).setCellValue("TransDate");
            headerRow.createCell(5).setCellValue("Type");
            int rowIdx = 1;
            for (Transaction transaction : listTransaction) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(transaction.getSourceAccount().getId());
                row.createCell(1).setCellValue(transaction.getSourceAccount().getName());
                row.createCell(2).setCellValue(transaction.getRecipientAccount().getName());
                row.createCell(3).setCellValue(transaction.getTransactionAmount().toString());
                row.createCell(4).setCellValue(transaction.getTransactionTime().toString());
                row.createCell(5).setCellValue(transaction.getTransactionType().toString());
            }
            workbook.write(out);
        }
    }
}
