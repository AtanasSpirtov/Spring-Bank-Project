package com.example.demo.fileConverter;

import com.example.demo.model.Transaction;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public abstract class _BaseConverter {
    protected final List<Transaction> listTransaction;

    protected _BaseConverter(List<Transaction> listTransaction) {
        this.listTransaction = listTransaction;
    }
    public abstract void export(OutputStream out) throws DocumentException, IOException;
}
