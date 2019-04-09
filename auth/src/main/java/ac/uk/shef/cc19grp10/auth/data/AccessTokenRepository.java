package ac.uk.shef.cc19grp10.auth.data;

import org.springframework.data.repository.CrudRepository;

public interface AccessTokenRepository extends CrudRepository<AccessToken, Long> {
	AccessToken findByAccessToken(byte[] accessToken);
}
