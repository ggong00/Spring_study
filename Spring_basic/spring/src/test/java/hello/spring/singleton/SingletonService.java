package hello.spring.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {
    // 싱글톤 패턴 적용
    private static final SingletonService instance = new SingletonService();

    private SingletonService() {
    }

    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱클톤 객체 로직 호출");
    }

    @Test
    @DisplayName("싱글톤 패턴 테스트")
    void singleTonServiceTest() {
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        Assertions.assertThat(instance1).isSameAs(instance2);
    }


}
