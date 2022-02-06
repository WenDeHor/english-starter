package com.example.englishstarter.service;

import com.example.englishstarter.forms.UserForm;
import com.example.englishstarter.model.Role;
import com.example.englishstarter.model.User;
import com.example.englishstarter.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.Locale;
import java.util.Optional;


@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(UserForm userForm) {
        Locale defaultLocale = Locale.getDefault();
        Currency currency = Currency.getInstance(defaultLocale);
        System.out.println("Currency Code: " + currency.getCurrencyCode());
        System.out.println("Default Fraction Digits: " + currency.getDefaultFractionDigits());
        String hashPassword = passwordEncoder.encode(userForm.getPassword());
        Optional<User> adminPresent = usersRepository.findOneByRole(Role.ADMIN);

        if (!adminPresent.isPresent()) {
            User user = User.builder()
                    .login(userForm.getLogin())
                    .password(hashPassword)
                    .role(Role.ADMIN)
                    .build();
            System.out.println(user.toString());
            usersRepository.save(user);
        } else {
            User user = User.builder()
                    .login(userForm.getLogin())
                    .password(hashPassword)
                    .role(Role.USER)
                    .build();
            System.out.println(user.toString());
            usersRepository.save(user);
        }
    }
}
