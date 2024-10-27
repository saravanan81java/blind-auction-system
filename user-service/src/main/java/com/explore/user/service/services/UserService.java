package com.explore.user.service.services;

import com.explore.user.service.entity.User;

public interface UserService {

	User validateToken(String token);

}
