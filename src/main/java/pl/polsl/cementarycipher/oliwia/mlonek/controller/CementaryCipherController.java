/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.controller;

import java.util.Scanner;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.view.CementaryCipherView;

/**
 *
 * @author roza
 */
public class CementaryCipherController {
    private CementaryCipherModel model;
    private CementaryCipherView view;

    public CementaryCipherController (CementaryCipherModel model, CementaryCipherView view) {
        this.model = model;
        this.view = view;
    }
    
    void getInput()
    {
        while (true) {
        view.printMainMenu();
        
        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();

        switch (choice) {

            case 1:
                model.ciphereMessage(view.getEncodeText());
                break;

            case 2:
                view.getDecodeText();
                break;

            case 3:
                System.out.println("exit");
                return;
        }
        }
    }


}
