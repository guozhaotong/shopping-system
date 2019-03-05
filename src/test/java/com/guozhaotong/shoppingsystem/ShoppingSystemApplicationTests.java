package com.guozhaotong.shoppingsystem;

import com.guozhaotong.shoppingsystem.entity.Commodity;
import com.guozhaotong.shoppingsystem.entity.ResultEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;

import java.util.LinkedHashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoppingSystemApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;


    @Test
    public void testAddNewCommodity() {
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("brief", "111简介brief1");
        map.add("intro", "intro111 ductio\n n");
        map.add("price", 12.02f);
        map.add("sellerId", 2);
        map.add("title", "商品111名称");
        Assert.isTrue((boolean) this.restTemplate.postForEntity("/addNewCommodity", map, ResultEntity.class).getBody().getData(), "插入错误");

        LinkedMultiValueMap<String, Object> map1 = new LinkedMultiValueMap<>();
        map1.add("brief", "简介b2222rief1");
        map1.add("intro", "intro 2222ductio\n n");
        map1.add("price", 12.02f);
        map1.add("sellerId", 2);
        map1.add("title", "2222商品名称法发丰fsfsaf");
        Assert.isTrue((boolean) this.restTemplate.postForEntity("/addNewCommodity", map1, ResultEntity.class).getBody().getData(), "插入错误");

        LinkedMultiValueMap<String, Object> map4 = new LinkedMultiValueMap<>();
        map4.add("brief", "33简介brief3333");
        map4.add("intro", "intro3333 ductio\n n");
        map4.add("price", 3.02f);
        map4.add("sellerId", 2);
        map4.add("title", "商品333333名称");
        Assert.isTrue((boolean) this.restTemplate.postForEntity("/addNewCommodity", map4, ResultEntity.class).getBody().getData(), "插入错误");
        //testGetCommodityList
        Assert.isTrue(((LinkedHashMap<Commodity, Integer>) this.restTemplate.getForEntity("/getCommodityList", ResultEntity.class).getBody().getData()).size() == 3, "数量不符");

        //updateCommodity
        LinkedMultiValueMap<String, Object> map2 = new LinkedMultiValueMap<>();
        map2.add("id", 1);
        map2.add("brief", "简11介brief1");
        map2.add("intro", "int11ro ducn");
        map2.add("price", 1.000f);
        map2.add("sellerId", 2);
        map2.add("title", "商111品名称");
        Assert.isTrue((boolean) this.restTemplate.postForEntity("/updateCommodity", map2, ResultEntity.class).getBody().getData(), "更新错误");

//        System.err.println(this.restTemplate.getForEntity("/getCommodity?commodityId={commodityId}", ResultEntity.class, 1).getBody().getData());
//        System.err.println(this.restTemplate.getForEntity("/getCommodity?commodityId={commodityId}", ResultEntity.class, 1).getBody().getData().getClass());

        Assert.isTrue("int11ro ducn".
                equals(
                        (
                                (LinkedHashMap) this.restTemplate.getForEntity("/getCommodity?commodityId={commodityId}", ResultEntity.class, 1)
                                        .getBody().getData()
                        ).get("intro")
                ), "intro更新错误");

//        System.err.println(this.restTemplate.getForEntity("/getShoppingCartList?buyerId={buyerId}", ResultEntity.class, 1).getBody().getData());


        LinkedMultiValueMap<String, Object> map6 = new LinkedMultiValueMap<>();
        map6.add("buyerId", 1);
        map6.add("commodityId", 1);
        map6.add("num", 11);
        Assert.isTrue((boolean) this.restTemplate.postForEntity("/addCommodityToShoppingCart", map6, ResultEntity.class).getBody().getData(), "插入购物车错误");

//        System.err.println(this.restTemplate.getForEntity("/getShoppingCartList?buyerId={buyerId}", ResultEntity.class, 1).getBody().getData());


        LinkedMultiValueMap<String, Object> map7 = new LinkedMultiValueMap<>();
        map7.add("buyerId", 1);
        map7.add("commodityId", 2);
        map7.add("num", 22);
        Assert.isTrue((boolean) this.restTemplate.postForEntity("/addCommodityToShoppingCart", map7, ResultEntity.class).getBody().getData(), "插入购物车错误");

//        System.err.println(this.restTemplate.getForEntity("/getShoppingCartList?buyerId={buyerId}", ResultEntity.class, 1).getBody().getData());

        LinkedMultiValueMap<String, Object> map8 = new LinkedMultiValueMap<>();
        map8.add("buyerId", 1);
        map8.add("commodityId", 3);
        map8.add("num", 33);
        Assert.isTrue((boolean) this.restTemplate.postForEntity("/addCommodityToShoppingCart", map8, ResultEntity.class).getBody().getData(), "插入购物车错误");

//        System.err.println(this.restTemplate.getForEntity("/getShoppingCartList?buyerId={buyerId}", ResultEntity.class, 1).getBody().getData());

        Assert.isTrue(((LinkedHashMap<Commodity, Integer>) this.restTemplate.getForEntity("/getShoppingCartList?buyerId={buyerId}", ResultEntity.class, 1).getBody().getData()).size() == 3, "数量不符");

        //deleteShoppingCartOneRecord
        LinkedMultiValueMap<String, Object> map9 = new LinkedMultiValueMap<>();
        map9.add("buyerId", 1);
        map9.add("commodityId", 3);
        Assert.isTrue((boolean) this.restTemplate.postForEntity("/deleteShoppingCartOneRecord", map9, ResultEntity.class).getBody().getData(), "删除错误");

        Assert.isTrue(((LinkedHashMap<Commodity, Integer>) this.restTemplate.getForEntity("/getShoppingCartList?buyerId={buyerId}", ResultEntity.class, 1).getBody().getData()).size() == 2, "数量不符");

        System.err.println(this.restTemplate.getForEntity("/getSumPriceOfShoppingCart?buyerId={buyerId}", ResultEntity.class, 1).getBody().getData());

        LinkedMultiValueMap<String, Object> map3 = new LinkedMultiValueMap<>();
        map3.add("buyerId", 1);
        this.restTemplate.postForEntity("/buyAllShoppingCart", map3, ResultEntity.class);

        Assert.isTrue(((LinkedHashMap<Commodity, Integer>) this.restTemplate.getForEntity("/getShoppingCartList?buyerId={buyerId}", ResultEntity.class, 1).getBody().getData()).size() == 0, "数量不符");


        System.err.println(this.restTemplate.getForEntity("/sumPrice?buyerId={buyerId}", ResultEntity.class, 1));

    }

    @Test
    public void testGetCommodity() {
        ResponseEntity<ResultEntity> response = this.restTemplate.getForEntity(
                "/getCommodity?commodityId={commodityId}", ResultEntity.class, 1);
        System.out.println(String.format("测试结果为：%s", response.getBody()));
    }

    @Test
    public void testGetCommodityList() {
        System.out.println("全部商品列表：" + this.restTemplate.getForEntity("/getCommodityList", ResultEntity.class).getBody());
    }
}
