package taxi.service.impl;

import java.util.Optional;
import taxi.exception.AuthenticationException;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Driver;
import taxi.service.AuthenticationService;
import taxi.service.DriverService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Optional<Driver> driverOptional = driverService.findByLogin(login);
        if (driverOptional.isPresent()) {
            Driver driver = driverOptional.get();
            if (driver.getPassword().equals(password)) {
                return driver;
            } else {
                throw new AuthenticationException("Username or password is incorrect");
            }
        } else {
            throw new AuthenticationException("Driver not found");
        }
    }
}
