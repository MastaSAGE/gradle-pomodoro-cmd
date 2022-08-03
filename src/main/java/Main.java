import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {


    public static int test = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Введите время работы и отдыха");
        String[] cmd = new Scanner(System.in).nextLine().split(" ");

        int workTime = 50;
        int breakTime = 10;
        int sizebreak = 30;
        int sizework = 30;
        int help = 0;
        int count = 1;
        int extent = 1;

        for (int i = 0; i < cmd.length; i++) {
            switch (cmd[i]) {
                case "--help" -> {
                    System.out.println(
                            "\n\nPomodoro - сделай свое время более эффективным\n");
                    System.out.println(
                            "	-w <time>: Планируемая продолжительность работы.\n");
                    System.out.println(
                            "	-b <time>: планируемая продолжительность перерыва.\n");
                    System.out.println(
                            "	-count <count>: количество циклов.\n");
                    System.out.println(
                            "   -m <extent>: множитель рабочего времени\n");
                    System.out.println(
                            "	--help: меню помощи.\n");

                    help = 1;
                }
                case "-w" -> workTime = Integer.parseInt(cmd[++i]);
                case "-b" -> breakTime = Integer.parseInt(cmd[++i]);
                case "-count" -> count = Integer.parseInt(cmd[++i]);
                case "-m" -> extent = Integer.parseInt(cmd[++i]);
            }
        }
        if (help == 0) {
            long startTime = System.currentTimeMillis();
            for (int i = 1; i <= count; i++) {
                timer(workTime, breakTime, sizebreak, sizework);
                workTime = workTime * extent;
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Pomodoro таймер истек: " + (endTime - startTime) / (1000 * 60) + " min");
        }

    }

    public static void timer(int workTime, int breakTime, int sizebreak, int sizework) throws InterruptedException {

        printProgress("Work Progress::  ", workTime, sizework);

        printProgress("Break Progress:: ", breakTime, sizebreak);
    }

    private static void printProgress(String process, int time, int size) throws InterruptedException {
        int length;
        int rep;
        length = 60 * time / size;
        rep = 60 * time / length;
        int stretchb = size / (3 * time);
        for (int i = 1; i <= rep; i++) {
            double x = i;
            x = 1.0 / 3.0 * x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time * stretchb;
            double percent = (x / w) * 1000;
            x /= stretchb;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent + "% " + (" ").repeat(5 - (String.valueOf(percent).length())) + "[" + ("#").repeat(i) + ("-").repeat(rep - i) + "]    ( " + x + "min / " + time + "min )" + "\r");
            if (true) {
                TimeUnit.SECONDS.sleep(length);
            }
        }
        System.out.println();
    }
}
