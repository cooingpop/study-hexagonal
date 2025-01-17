package com.example.study_hexagonal.infrastructure.adapter.out.persistence;

import com.example.study_hexagonal.domain.account.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
    Account selectAccount(@Param("accountId") String accountId);
    void insertAccount(Account account);
    void updateAccount(Account account);
}

/**
 * @Mapper 어노테이션을 사용하는 경우 @Repository 어노테이션을 함께 사용하지 않는 주요 이유는 다음과 같습니다:
 * 중복성 제거: @Mapper 어노테이션은 MyBatis에서 제공하는 것으로, 해당 인터페이스가 SQL 매퍼임을 명시적으로 나타냅니다. 이미 @Mapper로 충분히 그 역할을 표현하고 있기 때문에 @Repository를 추가로 사용할 필요가 없습니다13.
 * 자동 빈 등록: @Mapper 어노테이션이 붙은 인터페이스는 MyBatis에 의해 자동으로 스프링 빈으로 등록됩니다. 따라서 @Repository로 별도로 빈 등록을 할 필요가 없습니다3.
 * 명확한 의도 전달: @Mapper는 MyBatis의 SQL 매퍼임을 명확히 나타내는 반면, @Repository는 일반적인 저장소 계층을 나타냅니다. @Mapper를 사용함으로써 이 인터페이스가 MyBatis와 관련된 것임을 더 명확히 표현할 수 있습니다1.
 * 불필요한 추상화 방지: @Repository는 스프링의 데이터 접근 계층을 나타내는 더 일반적인 어노테이션입니다. MyBatis를 사용하는 경우 @Mapper가 더 구체적이고 적절한 선택입니다3.
 * 간결성: 불필요한 어노테이션을 제거함으로써 코드를 더 간결하게 유지할 수 있습니다.
 * 따라서 MyBatis를 사용하는 경우 @Mapper 어노테이션만으로 충분하며, @Repository를 추가로 사용하는 것은 불필요한 중복이 될 수 있습니다
 */