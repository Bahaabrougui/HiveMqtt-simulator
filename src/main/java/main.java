import mdms.mdmsConsommation;
import mdms.mdmsPrice;
import mdms.mdmsProduction;
import mdms.mdmsReduction;
import sm.smConsommation;
import sm.smPrice;
import sm.smProduction;
import sm.smReduction;

import java.io.IOException;

public class main {

    public static void main(String[] args) throws InterruptedException, IOException {

        // SM
        smConsommation smConsom = new smConsommation();
        smProduction smProd = new smProduction();
        smPrice smPrix = new smPrice();
        smReduction smReduc = new smReduction();

        // MDMS
        mdmsPrice mdsPrix = new mdmsPrice();
        mdmsReduction mdmsReduc = new mdmsReduction();
        mdmsConsommation mdmsConsom = new mdmsConsommation();
        mdmsProduction mdmsProd = new mdmsProduction();


        Thread flow11 = new Thread(smConsom);
        Thread flow12 = new Thread(smProd);
        Thread flow21 = new Thread(smPrix);
        Thread flow22 = new Thread(smReduc);

        Thread flow31 = new Thread(mdsPrix);
        Thread flow32 = new Thread(mdmsReduc);
        Thread flow41 = new Thread(mdmsConsom);
        Thread flow42 = new Thread(mdmsProd);

        flow11.start();
        flow12.start();
        flow21.start();
        flow22.start();
        flow31.start();
        flow32.start();
        flow41.start();
        flow42.start();
    }
}
