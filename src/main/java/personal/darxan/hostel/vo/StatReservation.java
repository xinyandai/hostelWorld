package personal.darxan.hostel.vo;

/**
 * Created by darxan on 2017/3/11.
 */
public class StatReservation {

    private String[] timeArray = {"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};

    private double maxValue = 250;

    private double minValue = 0;
    
    private double interval = 50;

    private double[] reservations = {2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3};

    private double[] checkIns = {2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3} ;

    private double[] payment = {2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2};


}
