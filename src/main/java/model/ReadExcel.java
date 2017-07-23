package model;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Micka on 03/07/2017.
 */
public class ReadExcel {
    private ArrayList<User> data;

    public ReadExcel(){

    }

    public void ReadExcel() throws IOException {
        data = new ArrayList<>();
        File fileWorkbook = new File("resources/plan_de_tri_interpac.xls");

        Workbook workbook;
        WorkbookSettings set = new WorkbookSettings();
        set.setEncoding("cp1252");

        try {
            workbook = Workbook.getWorkbook(fileWorkbook, set);
            Sheet[] sheets = workbook.getSheets();

            for(Sheet sheet : sheets){
                for(int i = 0; i < sheet.getRows(); i++){
                    if (i == 0 && sheets[0] != sheet){ //Retirer les 1eres lignes des feuilles
                        i++;
                    }

                    if (! sheet.getCell(0, i).getContents().equals("")){ //Retirer les lignes vides

                        /*Cell hour = sheet.getCell(0, i);
                        if (i > 0){
                            Date excelHour = format.parse(hour.getContents());
                            Date check = format.parse("23:59");

                            ArrayList<String> dataLine = new ArrayList<>();
                            for(int j = 0; j < sheet.getColumns(); j++){
                                Cell cell = sheet.getCell(j, i);
                                dataLine.add(cell.getContents());
                            }
                            data.add(dataLine);
                            //}
                        } else {
                            ArrayList<String> dataLine = new ArrayList<>();
                            for(int j = 0; j < sheet.getColumns(); j++){
                                Cell cell = sheet.getCell(j, i);
                                dataLine.add(cell.getContents());
                            }
                            data.add(dataLine);
                        }*/
                    }

                }
            }
        }  catch (BiffException e) {
            e.printStackTrace();
        }  /*catch (ParseException e) {
            e.printStackTrace();
        }*/
    }
}
