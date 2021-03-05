package com.nikola.pharaonsailmake.PDF;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.nikola.pharaonsailmake.domain.DeliverieCloth;

import java.util.ArrayList;


public class PDFDocumentDeliverieCloth {

    public void buildPdfDocument(
            Iterable<DeliverieCloth> deliverieCloths,
            Document document) throws Exception {
    //utf-8 not supported
        String[] names = {"ID", "Number Lot", "Length roll m.", "Price", "Data", "Cloth", "Dealer", "Storage"};
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);

        ArrayList<PdfPCell> headers = new ArrayList<>();

        for (String name : names) {
            headers.add(new PdfPCell(new Phrase(name)));
        }

        for (PdfPCell header : headers) {
            header.setHorizontalAlignment(Element.ALIGN_LEFT);
        }

        for (PdfPCell header : headers) {
            table.addCell(header);
        }

        for (DeliverieCloth deliverieCloth : deliverieCloths) {
           table.addCell(String.valueOf(deliverieCloth.getId()));
           table.addCell(String.valueOf(deliverieCloth.getLotNumber()));
           table.addCell(String.valueOf(deliverieCloth.getRollLength()));
           table.addCell(String.valueOf(deliverieCloth.getPrice()));
           table.addCell(deliverieCloth.getData());
           table.addCell(String.valueOf(deliverieCloth.getCloth_id().getName()));
           table.addCell(deliverieCloth.getDealer_id().getName());
           table.addCell(deliverieCloth.getStorageUnit_id().getTown()+deliverieCloth.getStorageUnit_id().getAddress());
        }

        document.add(table);
    }
}
