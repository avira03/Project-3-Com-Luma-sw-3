package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WomenTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/ ";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }



    @Test
    public void verifyTheSortByProductNameFilter() throws InterruptedException {

        String expectedText = "success";
        String actualText = "fail";

        //Mouse Hover on Women Menu
        mouseHoverToElement(By.xpath("//a[@id='ui-id-4']"));
        Thread.sleep(1000);
        //Mouse Hover on Tops
        mouseHoverToElement(By.xpath("//a[@id='ui-id-9']"));
        Thread.sleep(1000);
        //Click on Jackets
        clickOnElement(By.cssSelector("a[id='ui-id-11'] span"));
        Thread.sleep(1000);
        //Select Sort By filter “Product Name”
        selectByValueFromDropDown(By.xpath("//div[@class='page-wrapper']//div[2]//div[3]//select[1]"), "name");
        //Verify the products name display in alphabetical order
        Thread.sleep(1000);
        //Verify the products name display in alphabetical order
        List<WebElement> prods = driver.findElements(By.cssSelector("ol.products.list.items.product-items>li>div>div>strong>a.product-item-link"));

        System.out.println(" total products: "+prods.size());

        // check sorting
        List<String> productNames = new ArrayList<>();
        for (WebElement product : prods) {
            productNames.add(product.getText());
        }


        List<String> sortedProductNames = new ArrayList<>(productNames);
        sortedProductNames.sort(String::compareTo);

        if (productNames.equals(sortedProductNames)) {
            System.out.println(" Products are sorted in alphabetical order");
            actualText = "success";
        } else {
            System.out.println(" Products are not sorted in alphabetical order");
        }
        Assert.assertEquals("", expectedText, actualText);
    }


    @Test
    public void verifyTheSortByPriceFilter() throws InterruptedException {
        String expectedText = "success";
        String actualText = "fail";

        //Mouse Hover on Women Menu
        mouseHoverToElement(By.xpath("//a[@id='ui-id-4']"));
        Thread.sleep(1000);
        //Mouse Hover on Tops
        mouseHoverToElement(By.xpath("//a[@id='ui-id-9']"));
        Thread.sleep(1000);
        //Click on Jackets
        clickOnElement(By.cssSelector("a[id='ui-id-11'] span"));
        Thread.sleep(1000);

        // Sort By Price filter
        selectByVisibleTextFromDropDown(By.id("sorter"), "Price");


        // find list of prices
        List<WebElement> prices = driver.findElements(By.className("price-wrapper"));
        System.out.println("total No of Prices:  " + prices.size());

        // check sorting
        List<Double> productPrice = new ArrayList<>();
        for (WebElement price : prices) {
            productPrice.add(Double.parseDouble(price.getText().replace("$", "")));
        }

        List<Double> sortedPrices = new ArrayList<>(productPrice);
        sortedPrices.sort(Double::compareTo);

        if (productPrice.equals(sortedPrices)) {
            System.out.println(" Products are sorted by Price");
            actualText = "success";
        } else {
            System.out.println(" Products are not sorted  by Price");
        }
        Assert.assertEquals("", expectedText, actualText);
    }


    @After
    public void tearDown() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //closeBrowser();
    }
}
