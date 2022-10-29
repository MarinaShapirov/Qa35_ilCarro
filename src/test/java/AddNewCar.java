import manager.DataProviderCar;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCar extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        logger.info("Authorization check");
        if(!app.getHelperUser().isLogged()) {
            User user = new User().withEmail("marinas@gmail.com").withPassword("Mmarina12345$");
            app.getHelperUser().login(user);
            logger.info("User login: " + user.getEmail()+ " " + user.getPassword());
        }
        else
            logger.info("Authorized.");
    }
    @Test(groups = {"smoke_group", "sanity_group"})
    public void addCarSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000)+100;
        Car car = Car.builder()
                .location("Haifa, Israel")
                .make("BMW")
                .model("M5")
                .year("2020")
                .engine("2.5")
                .fuel("Petrol")
                .gear("AT")
                .wD("AWD")
                .doors("5")
                .seats("4")
                .clasS("C")
                .fuelConsumption("6.5")
                .carRegistrationNumber("11-000-"+ i)
                .price("65")
                .distanceIncluded("800")
                .features("Type of features")
                .about("very nice car")
                .build();
        app.HelperCar().openCarForm();
        app.HelperCar().fillCarForm(car);
        logger.info("Car: " + car.toString());

        app.HelperCar().attachPhoto("C:\\QA35-Automation\\Qa35_ilCarro\\src\\main\\resources\\car-photo.jpg");
        app.HelperCar().submit();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Car added");
        logger.info("ASSERT passed: 'Car added' title message is appear ");
    }

    @Test (enabled = true, dataProvider = "carValidData",dataProviderClass = DataProviderCar.class)
    public void addCarSuccessDP(Car car){

        app.HelperCar().openCarForm();
        app.HelperCar().fillCarForm(car);
        logger.info("Car: " + car.toString());

        app.HelperCar().attachPhoto("C:\\QA35-Automation\\Qa35_ilCarro\\src\\main\\resources\\car-photo.jpg");
        app.HelperCar().submit();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Car added");
        logger.info("ASSERT passed: 'Car added' title message is appear ");
    }


    @AfterMethod(alwaysRun = true)
    public void posCondition(){
        app.HelperCar().returnToHomePage();
    }

}
