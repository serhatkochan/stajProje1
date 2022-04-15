package mahrek.stajProje1.api.controllers;

import mahrek.stajProje1.business.concretes.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/redis")
public class RedisCacheController {

    @Autowired
    private RedisCacheManager redisCacheManager;


    private int sayac = 0;
    @GetMapping
    public String cacheControl() throws InterruptedException{
        if(sayac == 5){
            redisCacheManager.clearCache();
            sayac = 0;
        }
        sayac ++;
        return redisCacheManager.longRunningMethod();
    }
}
