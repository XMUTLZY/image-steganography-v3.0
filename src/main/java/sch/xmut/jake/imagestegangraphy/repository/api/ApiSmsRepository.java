package sch.xmut.jake.imagestegangraphy.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import sch.xmut.jake.imagestegangraphy.domain.api.ApiSmsEntity;

/**
 * Created by jake.lin on 2019/12/26
 */
public interface ApiSmsRepository extends JpaRepository<ApiSmsEntity, Integer>{
}
