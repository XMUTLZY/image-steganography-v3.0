package sch.xmut.jake.imagestegangraphy.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import sch.xmut.jake.imagestegangraphy.domain.api.ApiOssEntity;

/**
 * Created by jake.lin on 2019/12/30
 */
public interface ApiOssRepository extends JpaRepository<ApiOssEntity, Integer> {
}
