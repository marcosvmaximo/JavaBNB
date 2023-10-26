package repositories;

import models.Host;
import org.apache.poi.ss.usermodel.*;
import org.apache.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HostRepository {
  private final String arquivoExcel = "host.xlsx";

  public void adicionarPessoa(Host host) {
    try (FileInputStream fis = new FileInputStream(arquivoExcel);
        Workbook workbook = new XSSFWorkbook(fis)) {
      Sheet sheet = workbook.getSheetAt(0);
      int rowCount = sheet.getPhysicalNumberOfRows();
      Row row = sheet.createRow(rowCount);

      row.createCell(0).setCellValue(host.getId());
      row.createCell(1).setCellValue(host.getName());
      row.createCell(2).setCellValue(host.getAge());   
      row.createCell(3).setCellValue(host.getCpf());
      row.createCell(4).setCellValue(host.getContact());
      row.createCell(5).setCellValue(host.getBirthDate());
      row.createCell(6).setCellValue(host.getMaxReservationNumber());


      try (FileOutputStream fos = new FileOutputStream(arquivoExcel)) {
        workbook.write(fos);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Host> listHost() {
    List<Host> hosts = new ArrayList<>();
    try (FileInputStream fis = new FileInputStream(arquivoExcel);
        Workbook workbook = new XSSFWorkbook(fis)) {
      Sheet sheet = workbook.getSheetAt(0);
      for (Row row : sheet) {
        int id = (int) row.getCell(0).getNumericCellValue();
        String nome = row.getCell(1).getStringCellValue();
        int idade = (int) row.getCell(2).getNumericCellValue();
        int cpf = row.getCell(3).getNumericCellValue();
        int contato = row.getCell(4).getNumericCellValue();
        int aniversario = (Date) row.getCell(5).getNumericCellValue();
        int numeroMaximoReserva = (int) row.getCell(6).getNumericCellValue();
        
        Contact contact = new Contact(contato);

        hosts.add(new Host(nome, cpf, aniversario, contact));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return pessoas;
  }
}