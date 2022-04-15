package mahrek.stajProje1.business.concretes;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheManager {


    @Cacheable(cacheNames = "mySpecialCache")
    public String longRunningMethod() throws InterruptedException {

        Thread.sleep(5000L);
        return "method çalıştı";
    }

    @CacheEvict(cacheNames = "mySpecialCache") // cache kill // şu isimdeki cachei kill et
    public void clearCache(){
        System.out.println("Cache Temizlendi");
    }

}
