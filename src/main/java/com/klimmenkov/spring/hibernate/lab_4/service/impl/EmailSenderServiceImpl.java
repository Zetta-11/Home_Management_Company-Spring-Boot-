package com.klimmenkov.spring.hibernate.lab_4.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.klimmenkov.spring.hibernate.lab_4.entity.Payment;
import com.klimmenkov.spring.hibernate.lab_4.service.EmailSenderService;
import com.klimmenkov.spring.hibernate.lab_4.service.PaymentService;
import com.klimmenkov.spring.hibernate.lab_4.service.UserService;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.util.List;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private UserService userService;

    public void sendEmail(String to, String userLogin) throws DocumentException, MessagingException {
        Long availableSum = paymentService.getSumOfAvailableMoney();

        // Create a MimeMessage
        MimeMessage message = javaMailSender.createMimeMessage();

        // Use MimeMessageHelper to set the properties of the email and attach the PDF file
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(new InternetAddress("klim.menkov@gmail.com"));
        helper.setTo(new InternetAddress(to));
        helper.setSubject("Квитанція з доступною сумою");
        helper.setText("Добрий день! \nДоступна сума для використання в ОСББ: "
                + availableSum + "\nЗ повагою, команда HMC\n\nБУДЬ ЛАСКА, НЕ ВІДПОВІДАЙТЕ НА ЦЕ ПОВІДОМЛЕННЯ!");

        // Get the PDF bytes and attach it to the email
        byte[] pdfBytes = generatePDF(paymentService.getAllPayments(userService.getUserByLogin(userLogin).getHouse())); // Replace this with the method that generates your PDF bytes
        ByteArrayDataSource dataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");
        helper.addAttachment("filename.pdf", dataSource);

        // Send the email
        javaMailSender.send(message);
    }


    @Override
    public void sendEmailWithAttachment(String to) {

    }

    private byte[] generatePDF(List<Payment> allPayments) throws DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 2, 1, 2, 2});

        // Add table headers
        table.addCell("ID");
        table.addCell("DATE");
        table.addCell("SUM");
        table.addCell("PAYMENT TYPE");
        table.addCell("DETAILS");

        // Add table rows
        for (Payment payment : allPayments) {
            table.addCell(String.valueOf(payment.getId()));
            table.addCell(payment.getDate().toString());
            table.addCell(String.valueOf(payment.getSum()));
            table.addCell(payment.getPaymentType());
            table.addCell("Details");
        }

        document.add(table);
        document.close();
        return baos.toByteArray();
    }
}
